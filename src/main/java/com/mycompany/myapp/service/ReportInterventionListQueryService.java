package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ReportInterventionList;
import com.mycompany.myapp.repository.ReportInterventionListRepository;
import com.mycompany.myapp.repository.search.ReportInterventionListSearchRepository;
import com.mycompany.myapp.service.criteria.ReportInterventionListCriteria;
import com.mycompany.myapp.service.dto.ReportInterventionListDTO;
import com.mycompany.myapp.service.mapper.ReportInterventionListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ReportInterventionList} entities in the database.
 * The main input is a {@link ReportInterventionListCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link ReportInterventionListDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReportInterventionListQueryService extends QueryService<ReportInterventionList> {

    private static final Logger log = LoggerFactory.getLogger(ReportInterventionListQueryService.class);

    protected final ReportInterventionListRepository reportInterventionListRepository;

    protected final ReportInterventionListMapper reportInterventionListMapper;

    protected final ReportInterventionListSearchRepository reportInterventionListSearchRepository;

    public ReportInterventionListQueryService(
        ReportInterventionListRepository reportInterventionListRepository,
        ReportInterventionListMapper reportInterventionListMapper,
        ReportInterventionListSearchRepository reportInterventionListSearchRepository
    ) {
        this.reportInterventionListRepository = reportInterventionListRepository;
        this.reportInterventionListMapper = reportInterventionListMapper;
        this.reportInterventionListSearchRepository = reportInterventionListSearchRepository;
    }

    /**
     * Return a {@link Page} of {@link ReportInterventionListDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportInterventionListDTO> findByCriteria(ReportInterventionListCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ReportInterventionList> specification = createSpecification(criteria);
        return reportInterventionListRepository.findAll(specification, page).map(reportInterventionListMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReportInterventionListCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ReportInterventionList> specification = createSpecification(criteria);
        return reportInterventionListRepository.count(specification);
    }

    /**
     * Function to convert {@link ReportInterventionListCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ReportInterventionList> createSpecification(ReportInterventionListCriteria criteria) {
        Specification<ReportInterventionList> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ReportInterventionList_.id));
            }
            if (criteria.getSite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSite(), ReportInterventionList_.site));
            }
            if (criteria.getCodeAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgence(), ReportInterventionList_.codeAgence));
            }
            if (criteria.getAffaireNumber() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAffaireNumber(), ReportInterventionList_.affaireNumber)
                );
            }
            if (criteria.getContractNumber() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getContractNumber(), ReportInterventionList_.contractNumber)
                );
            }
            if (criteria.getInstallationAdress() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getInstallationAdress(), ReportInterventionList_.installationAdress)
                );
            }
            if (criteria.getInterlocuteurIntervation() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getInterlocuteurIntervation(), ReportInterventionList_.interlocuteurIntervation)
                );
            }
            if (criteria.getTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTel(), ReportInterventionList_.tel));
            }
            if (criteria.getInstallationSousContract() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getInstallationSousContract(), ReportInterventionList_.installationSousContract)
                );
            }
            if (criteria.getInstallationSousGarantie() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getInstallationSousGarantie(), ReportInterventionList_.installationSousGarantie)
                );
            }
            if (criteria.getAdressFacturation() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAdressFacturation(), ReportInterventionList_.adressFacturation)
                );
            }
            if (criteria.getInterlocuteurFacturation() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getInterlocuteurFacturation(), ReportInterventionList_.interlocuteurFacturation)
                );
            }
            if (criteria.getConditionDePayementCheque() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getConditionDePayementCheque(), ReportInterventionList_.conditionDePayementCheque)
                );
            }
            if (criteria.getConditionPayementAutre() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getConditionPayementAutre(), ReportInterventionList_.conditionPayementAutre)
                );
            }
            if (criteria.getConditionPayementComment() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getConditionPayementComment(), ReportInterventionList_.conditionPayementComment)
                );
            }
            if (criteria.getMiseEnServiceDefinitvie() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getMiseEnServiceDefinitvie(), ReportInterventionList_.miseEnServiceDefinitvie)
                );
            }
            if (criteria.getMiseEnServicePartielle() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getMiseEnServicePartielle(), ReportInterventionList_.miseEnServicePartielle)
                );
            }
            if (criteria.getMaintenancePreventive() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getMaintenancePreventive(), ReportInterventionList_.maintenancePreventive)
                );
            }
            if (criteria.getMaintenanceCorrective() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getMaintenanceCorrective(), ReportInterventionList_.maintenanceCorrective)
                );
            }
            if (criteria.getbT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getbT(), ReportInterventionList_.bT));
            }
            if (criteria.getAnomalieSignalee() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAnomalieSignalee(), ReportInterventionList_.anomalieSignalee)
                );
            }
            if (criteria.getInterventionDate() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getInterventionDate(), ReportInterventionList_.interventionDate)
                );
            }
            if (criteria.getInterventionStartDate() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getInterventionStartDate(), ReportInterventionList_.interventionStartDate)
                );
            }
            if (criteria.getRemiseServiceDate() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getRemiseServiceDate(), ReportInterventionList_.remiseServiceDate)
                );
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), ReportInterventionList_.endDate));
            }
            if (criteria.getCauseExterieurInstallation() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getCauseExterieurInstallation(), ReportInterventionList_.causeExterieurInstallation)
                );
            }
            if (criteria.getInstallationFonctionnelleApresInervention() != null) {
                specification = specification.and(
                    buildSpecification(
                        criteria.getInstallationFonctionnelleApresInervention(),
                        ReportInterventionList_.installationFonctionnelleApresInervention
                    )
                );
            }
            if (criteria.getAutreInterventionsAPrevoir() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getAutreInterventionsAPrevoir(), ReportInterventionList_.autreInterventionsAPrevoir)
                );
            }
            if (criteria.getClientApreciation() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getClientApreciation(), ReportInterventionList_.clientApreciation)
                );
            }
            if (criteria.getRespectDelais() != null) {
                specification = specification.and(buildSpecification(criteria.getRespectDelais(), ReportInterventionList_.respectDelais));
            }
            if (criteria.getQualiteIntervention() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getQualiteIntervention(), ReportInterventionList_.qualiteIntervention)
                );
            }
            if (criteria.getQualiteDevoirConseil() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getQualiteDevoirConseil(), ReportInterventionList_.qualiteDevoirConseil)
                );
            }
            if (criteria.getPrestationsAchevees() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getPrestationsAchevees(), ReportInterventionList_.prestationsAchevees)
                );
            }
            if (criteria.getDevisComplentaire() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getDevisComplentaire(), ReportInterventionList_.devisComplentaire)
                );
            }
            if (criteria.getTechnicienName() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getTechnicienName(), ReportInterventionList_.technicienName)
                );
            }
            if (criteria.getCodeTechnicien() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getCodeTechnicien(), ReportInterventionList_.codeTechnicien)
                );
            }
            if (criteria.getValidationClientName() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getValidationClientName(), ReportInterventionList_.validationClientName)
                );
            }
            if (criteria.getValidationNameFunction() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getValidationNameFunction(), ReportInterventionList_.validationNameFunction)
                );
            }
            if (criteria.getValidationClientDate() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getValidationClientDate(), ReportInterventionList_.validationClientDate)
                );
            }
            if (criteria.getBonPourCommand() != null) {
                specification = specification.and(buildSpecification(criteria.getBonPourCommand(), ReportInterventionList_.bonPourCommand));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), ReportInterventionList_.createdBy));
            }
            if (criteria.getValidation() != null) {
                specification = specification.and(buildSpecification(criteria.getValidation(), ReportInterventionList_.validation));
            }
        }
        return specification;
    }
}
