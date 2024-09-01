package com.mycompany.myapp.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.mycompany.myapp.domain.CheckList;
import com.mycompany.myapp.repository.CheckListRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link CheckList} entity.
 */
public interface CheckListSearchRepository extends ElasticsearchRepository<CheckList, Long>, CheckListSearchRepositoryInternal {}

interface CheckListSearchRepositoryInternal {
    Page<CheckList> search(String query, Pageable pageable);

    Page<CheckList> search(Query query);

    @Async
    void index(CheckList entity);

    @Async
    void deleteFromIndexById(Long id);
}

class CheckListSearchRepositoryInternalImpl implements CheckListSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final CheckListRepository repository;

    CheckListSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, CheckListRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Page<CheckList> search(String query, Pageable pageable) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery.setPageable(pageable));
    }

    @Override
    public Page<CheckList> search(Query query) {
        SearchHits<CheckList> searchHits = elasticsearchTemplate.search(query, CheckList.class);
        List<CheckList> hits = searchHits.map(SearchHit::getContent).stream().toList();
        return new PageImpl<>(hits, query.getPageable(), searchHits.getTotalHits());
    }

    @Override
    public void index(CheckList entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), CheckList.class);
    }
}
