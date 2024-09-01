package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Demand;
import com.mycompany.myapp.repository.DemandRepository;
import com.mycompany.myapp.repository.search.DemandSearchRepository;
import com.mycompany.myapp.service.criteria.DemandCriteria;
import com.mycompany.myapp.service.dto.DemandDTO;
import com.mycompany.myapp.service.mapper.DemandMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Demand} entities in the database.
 * The main input is a {@link DemandCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link DemandDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DemandQueryService extends QueryService<Demand> {

    private static final Logger log = LoggerFactory.getLogger(DemandQueryService.class);

    protected final DemandRepository demandRepository;

    protected final DemandMapper demandMapper;

    protected final DemandSearchRepository demandSearchRepository;

    public DemandQueryService(DemandRepository demandRepository, DemandMapper demandMapper, DemandSearchRepository demandSearchRepository) {
        this.demandRepository = demandRepository;
        this.demandMapper = demandMapper;
        this.demandSearchRepository = demandSearchRepository;
    }

    /**
     * Return a {@link Page} of {@link DemandDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DemandDTO> findByCriteria(DemandCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Demand> specification = createSpecification(criteria);
        return demandRepository.findAll(specification, page).map(demandMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DemandCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Demand> specification = createSpecification(criteria);
        return demandRepository.count(specification);
    }

    /**
     * Function to convert {@link DemandCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Demand> createSpecification(DemandCriteria criteria) {
        Specification<Demand> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Demand_.id));
            }
            if (criteria.getqT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getqT(), Demand_.qT));
            }
            if (criteria.getDemandBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDemandBy(), Demand_.demandBy));
            }
            if (criteria.getDemandDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDemandDate(), Demand_.demandDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Demand_.status));
            }
            if (criteria.getValidate() != null) {
                specification = specification.and(buildSpecification(criteria.getValidate(), Demand_.validate));
            }
            if (criteria.getNameId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getNameId(), root -> root.join(Demand_.name, JoinType.LEFT).get(Product_.id))
                );
            }
        }
        return specification;
    }
}
