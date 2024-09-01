package com.mycompany.myapp.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.mycompany.myapp.domain.ReportInterventionList;
import com.mycompany.myapp.repository.ReportInterventionListRepository;
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
 * Spring Data Elasticsearch repository for the {@link ReportInterventionList} entity.
 */
public interface ReportInterventionListSearchRepository
    extends ElasticsearchRepository<ReportInterventionList, Long>, ReportInterventionListSearchRepositoryInternal {}

interface ReportInterventionListSearchRepositoryInternal {
    Page<ReportInterventionList> search(String query, Pageable pageable);

    Page<ReportInterventionList> search(Query query);

    @Async
    void index(ReportInterventionList entity);

    @Async
    void deleteFromIndexById(Long id);
}

class ReportInterventionListSearchRepositoryInternalImpl implements ReportInterventionListSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final ReportInterventionListRepository repository;

    ReportInterventionListSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        ReportInterventionListRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Page<ReportInterventionList> search(String query, Pageable pageable) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery.setPageable(pageable));
    }

    @Override
    public Page<ReportInterventionList> search(Query query) {
        SearchHits<ReportInterventionList> searchHits = elasticsearchTemplate.search(query, ReportInterventionList.class);
        List<ReportInterventionList> hits = searchHits.map(SearchHit::getContent).stream().toList();
        return new PageImpl<>(hits, query.getPageable(), searchHits.getTotalHits());
    }

    @Override
    public void index(ReportInterventionList entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), ReportInterventionList.class);
    }
}
