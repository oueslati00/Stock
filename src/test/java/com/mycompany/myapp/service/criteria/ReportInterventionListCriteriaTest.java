package com.mycompany.myapp.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ReportInterventionListCriteriaTest {

    @Test
    void newReportInterventionListCriteriaHasAllFiltersNullTest() {
        var reportInterventionListCriteria = new ReportInterventionListCriteria();
        assertThat(reportInterventionListCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void reportInterventionListCriteriaFluentMethodsCreatesFiltersTest() {
        var reportInterventionListCriteria = new ReportInterventionListCriteria();

        setAllFilters(reportInterventionListCriteria);

        assertThat(reportInterventionListCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void reportInterventionListCriteriaCopyCreatesNullFilterTest() {
        var reportInterventionListCriteria = new ReportInterventionListCriteria();
        var copy = reportInterventionListCriteria.copy();

        assertThat(reportInterventionListCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(reportInterventionListCriteria)
        );
    }

    @Test
    void reportInterventionListCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var reportInterventionListCriteria = new ReportInterventionListCriteria();
        setAllFilters(reportInterventionListCriteria);

        var copy = reportInterventionListCriteria.copy();

        assertThat(reportInterventionListCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(reportInterventionListCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var reportInterventionListCriteria = new ReportInterventionListCriteria();

        assertThat(reportInterventionListCriteria).hasToString("ReportInterventionListCriteria{}");
    }

    private static void setAllFilters(ReportInterventionListCriteria reportInterventionListCriteria) {
        reportInterventionListCriteria.id();
        reportInterventionListCriteria.site();
        reportInterventionListCriteria.codeAgence();
        reportInterventionListCriteria.affaireNumber();
        reportInterventionListCriteria.contractNumber();
        reportInterventionListCriteria.installationAdress();
        reportInterventionListCriteria.interlocuteurIntervation();
        reportInterventionListCriteria.tel();
        reportInterventionListCriteria.installationSousContract();
        reportInterventionListCriteria.installationSousGarantie();
        reportInterventionListCriteria.adressFacturation();
        reportInterventionListCriteria.interlocuteurFacturation();
        reportInterventionListCriteria.conditionDePayementCheque();
        reportInterventionListCriteria.conditionPayementAutre();
        reportInterventionListCriteria.conditionPayementComment();
        reportInterventionListCriteria.miseEnServiceDefinitvie();
        reportInterventionListCriteria.miseEnServicePartielle();
        reportInterventionListCriteria.maintenancePreventive();
        reportInterventionListCriteria.maintenanceCorrective();
        reportInterventionListCriteria.bT();
        reportInterventionListCriteria.anomalieSignalee();
        reportInterventionListCriteria.interventionDate();
        reportInterventionListCriteria.interventionStartDate();
        reportInterventionListCriteria.remiseServiceDate();
        reportInterventionListCriteria.endDate();
        reportInterventionListCriteria.causeExterieurInstallation();
        reportInterventionListCriteria.installationFonctionnelleApresInervention();
        reportInterventionListCriteria.autreInterventionsAPrevoir();
        reportInterventionListCriteria.clientApreciation();
        reportInterventionListCriteria.respectDelais();
        reportInterventionListCriteria.qualiteIntervention();
        reportInterventionListCriteria.qualiteDevoirConseil();
        reportInterventionListCriteria.prestationsAchevees();
        reportInterventionListCriteria.devisComplentaire();
        reportInterventionListCriteria.technicienName();
        reportInterventionListCriteria.codeTechnicien();
        reportInterventionListCriteria.validationClientName();
        reportInterventionListCriteria.validationNameFunction();
        reportInterventionListCriteria.validationClientDate();
        reportInterventionListCriteria.bonPourCommand();
        reportInterventionListCriteria.createdBy();
        reportInterventionListCriteria.validation();
        reportInterventionListCriteria.distinct();
    }

    private static Condition<ReportInterventionListCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getSite()) &&
                condition.apply(criteria.getCodeAgence()) &&
                condition.apply(criteria.getAffaireNumber()) &&
                condition.apply(criteria.getContractNumber()) &&
                condition.apply(criteria.getInstallationAdress()) &&
                condition.apply(criteria.getInterlocuteurIntervation()) &&
                condition.apply(criteria.getTel()) &&
                condition.apply(criteria.getInstallationSousContract()) &&
                condition.apply(criteria.getInstallationSousGarantie()) &&
                condition.apply(criteria.getAdressFacturation()) &&
                condition.apply(criteria.getInterlocuteurFacturation()) &&
                condition.apply(criteria.getConditionDePayementCheque()) &&
                condition.apply(criteria.getConditionPayementAutre()) &&
                condition.apply(criteria.getConditionPayementComment()) &&
                condition.apply(criteria.getMiseEnServiceDefinitvie()) &&
                condition.apply(criteria.getMiseEnServicePartielle()) &&
                condition.apply(criteria.getMaintenancePreventive()) &&
                condition.apply(criteria.getMaintenanceCorrective()) &&
                condition.apply(criteria.getbT()) &&
                condition.apply(criteria.getAnomalieSignalee()) &&
                condition.apply(criteria.getInterventionDate()) &&
                condition.apply(criteria.getInterventionStartDate()) &&
                condition.apply(criteria.getRemiseServiceDate()) &&
                condition.apply(criteria.getEndDate()) &&
                condition.apply(criteria.getCauseExterieurInstallation()) &&
                condition.apply(criteria.getInstallationFonctionnelleApresInervention()) &&
                condition.apply(criteria.getAutreInterventionsAPrevoir()) &&
                condition.apply(criteria.getClientApreciation()) &&
                condition.apply(criteria.getRespectDelais()) &&
                condition.apply(criteria.getQualiteIntervention()) &&
                condition.apply(criteria.getQualiteDevoirConseil()) &&
                condition.apply(criteria.getPrestationsAchevees()) &&
                condition.apply(criteria.getDevisComplentaire()) &&
                condition.apply(criteria.getTechnicienName()) &&
                condition.apply(criteria.getCodeTechnicien()) &&
                condition.apply(criteria.getValidationClientName()) &&
                condition.apply(criteria.getValidationNameFunction()) &&
                condition.apply(criteria.getValidationClientDate()) &&
                condition.apply(criteria.getBonPourCommand()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getValidation()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ReportInterventionListCriteria> copyFiltersAre(
        ReportInterventionListCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getSite(), copy.getSite()) &&
                condition.apply(criteria.getCodeAgence(), copy.getCodeAgence()) &&
                condition.apply(criteria.getAffaireNumber(), copy.getAffaireNumber()) &&
                condition.apply(criteria.getContractNumber(), copy.getContractNumber()) &&
                condition.apply(criteria.getInstallationAdress(), copy.getInstallationAdress()) &&
                condition.apply(criteria.getInterlocuteurIntervation(), copy.getInterlocuteurIntervation()) &&
                condition.apply(criteria.getTel(), copy.getTel()) &&
                condition.apply(criteria.getInstallationSousContract(), copy.getInstallationSousContract()) &&
                condition.apply(criteria.getInstallationSousGarantie(), copy.getInstallationSousGarantie()) &&
                condition.apply(criteria.getAdressFacturation(), copy.getAdressFacturation()) &&
                condition.apply(criteria.getInterlocuteurFacturation(), copy.getInterlocuteurFacturation()) &&
                condition.apply(criteria.getConditionDePayementCheque(), copy.getConditionDePayementCheque()) &&
                condition.apply(criteria.getConditionPayementAutre(), copy.getConditionPayementAutre()) &&
                condition.apply(criteria.getConditionPayementComment(), copy.getConditionPayementComment()) &&
                condition.apply(criteria.getMiseEnServiceDefinitvie(), copy.getMiseEnServiceDefinitvie()) &&
                condition.apply(criteria.getMiseEnServicePartielle(), copy.getMiseEnServicePartielle()) &&
                condition.apply(criteria.getMaintenancePreventive(), copy.getMaintenancePreventive()) &&
                condition.apply(criteria.getMaintenanceCorrective(), copy.getMaintenanceCorrective()) &&
                condition.apply(criteria.getbT(), copy.getbT()) &&
                condition.apply(criteria.getAnomalieSignalee(), copy.getAnomalieSignalee()) &&
                condition.apply(criteria.getInterventionDate(), copy.getInterventionDate()) &&
                condition.apply(criteria.getInterventionStartDate(), copy.getInterventionStartDate()) &&
                condition.apply(criteria.getRemiseServiceDate(), copy.getRemiseServiceDate()) &&
                condition.apply(criteria.getEndDate(), copy.getEndDate()) &&
                condition.apply(criteria.getCauseExterieurInstallation(), copy.getCauseExterieurInstallation()) &&
                condition.apply(
                    criteria.getInstallationFonctionnelleApresInervention(),
                    copy.getInstallationFonctionnelleApresInervention()
                ) &&
                condition.apply(criteria.getAutreInterventionsAPrevoir(), copy.getAutreInterventionsAPrevoir()) &&
                condition.apply(criteria.getClientApreciation(), copy.getClientApreciation()) &&
                condition.apply(criteria.getRespectDelais(), copy.getRespectDelais()) &&
                condition.apply(criteria.getQualiteIntervention(), copy.getQualiteIntervention()) &&
                condition.apply(criteria.getQualiteDevoirConseil(), copy.getQualiteDevoirConseil()) &&
                condition.apply(criteria.getPrestationsAchevees(), copy.getPrestationsAchevees()) &&
                condition.apply(criteria.getDevisComplentaire(), copy.getDevisComplentaire()) &&
                condition.apply(criteria.getTechnicienName(), copy.getTechnicienName()) &&
                condition.apply(criteria.getCodeTechnicien(), copy.getCodeTechnicien()) &&
                condition.apply(criteria.getValidationClientName(), copy.getValidationClientName()) &&
                condition.apply(criteria.getValidationNameFunction(), copy.getValidationNameFunction()) &&
                condition.apply(criteria.getValidationClientDate(), copy.getValidationClientDate()) &&
                condition.apply(criteria.getBonPourCommand(), copy.getBonPourCommand()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getValidation(), copy.getValidation()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
