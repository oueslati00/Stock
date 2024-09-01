package com.mycompany.myapp.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.repository.DemandRepository;
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
 * Spring Data Elasticsearch repository for the {@link Demand} entity.
 */
public interface DemandSearchRepository extends ElasticsearchRepository<Demand, Long>, DemandSearchRepositoryInternal {}

interface DemandSearchRepositoryInternal {
    Page<Demand> search(String query, Pageable pageable);

    Page<Demand> search(Query query);

    @Async
    void index(Demand entity);

    @Async
    void deleteFromIndexById(Long id);
}

class DemandSearchRepositoryInternalImpl implements DemandSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final DemandRepository repository;

    DemandSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, DemandRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Page<Demand> search(String query, Pageable pageable) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery.setPageable(pageable));
    }

    @Override
    public Page<Demand> search(Query query) {
        SearchHits<Demand> searchHits = elasticsearchTemplate.search(query, Demand.class);
        List<Demand> hits = searchHits.map(SearchHit::getContent).stream().toList();
        return new PageImpl<>(hits, query.getPageable(), searchHits.getTotalHits());
    }

    @Override
    public void index(Demand entity) {
        repository.findOneWithEagerRelationships(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), Demand.class);
    }
}
