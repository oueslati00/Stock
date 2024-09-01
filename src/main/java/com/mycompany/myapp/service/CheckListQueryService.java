package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.CheckList;
import com.mycompany.myapp.repository.CheckListRepository;
import com.mycompany.myapp.repository.search.CheckListSearchRepository;
import com.mycompany.myapp.service.criteria.CheckListCriteria;
import com.mycompany.myapp.service.dto.CheckListDTO;
import com.mycompany.myapp.service.mapper.CheckListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CheckList} entities in the database.
 * The main input is a {@link CheckListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link CheckListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CheckListQueryService extends QueryService<CheckList> {

    private static final Logger log = LoggerFactory.getLogger(CheckListQueryService.class);

    protected final CheckListRepository checkListRepository;

    protected final CheckListMapper checkListMapper;

    protected final CheckListSearchRepository checkListSearchRepository;

    public CheckListQueryService(
        CheckListRepository checkListRepository,
        CheckListMapper checkListMapper,
        CheckListSearchRepository checkListSearchRepository
    ) {
        this.checkListRepository = checkListRepository;
        this.checkListMapper = checkListMapper;
        this.checkListSearchRepository = checkListSearchRepository;
    }

    /**
     * Return a {@link Page} of {@link CheckListDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CheckListDTO> findByCriteria(CheckListCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CheckList> specification = createSpecification(criteria);
        return checkListRepository.findAll(specification, page).map(checkListMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CheckListCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CheckList> specification = createSpecification(criteria);
        return checkListRepository.count(specification);
    }

    /**
     * Function to convert {@link CheckListCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CheckList> createSpecification(CheckListCriteria criteria) {
        Specification<CheckList> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CheckList_.id));
            }
            if (criteria.getClient() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClient(), CheckList_.client));
            }
            if (criteria.getContractNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractNumber(), CheckList_.contractNumber));
            }
            if (criteria.getAdress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdress(), CheckList_.adress));
            }
            if (criteria.getTechnicienDef() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTechnicienDef(), CheckList_.technicienDef));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), CheckList_.date));
            }
            if (criteria.getTableDetectionTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTableDetectionTaskStatus(), CheckList_.tableDetectionTaskStatus)
                );
            }
            if (criteria.getDiDMTaskStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getDiDMTaskStatus(), CheckList_.diDMTaskStatus));
            }
            if (criteria.getDetecteursPonctuelsTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDetecteursPonctuelsTaskStatus(), CheckList_.detecteursPonctuelsTaskStatus)
                );
            }
            if (criteria.getDeclencheurManuelsTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDeclencheurManuelsTaskStatus(), CheckList_.declencheurManuelsTaskStatus)
                );
            }
            if (criteria.getTableMiseSecurityIncendieTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTableMiseSecurityIncendieTaskStatus(), CheckList_.tableMiseSecurityIncendieTaskStatus)
                );
            }
            if (criteria.getAlimentationSecoursTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getAlimentationSecoursTaskStatus(), CheckList_.alimentationSecoursTaskStatus)
                );
            }
            if (criteria.getEquipementAlarmeTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getEquipementAlarmeTaskStatus(), CheckList_.equipementAlarmeTaskStatus)
                );
            }
            if (criteria.getDasTaskStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getDasTaskStatus(), CheckList_.dasTaskStatus));
            }
            if (criteria.getArretTechniqueTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getArretTechniqueTaskStatus(), CheckList_.arretTechniqueTaskStatus)
                );
            }
            if (criteria.getBaiePcsTaskStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getBaiePcsTaskStatus(), CheckList_.baiePcsTaskStatus));
            }
            if (criteria.getSuperviseurTaskStatus() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getSuperviseurTaskStatus(), CheckList_.superviseurTaskStatus)
                );
            }
            if (criteria.getValidate() != null) {
                specification = specification.and(buildSpecification(criteria.getValidate(), CheckList_.validate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), CheckList_.createdBy));
            }
        }
        return specification;
    }
}
