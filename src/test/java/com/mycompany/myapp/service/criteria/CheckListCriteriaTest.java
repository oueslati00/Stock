package com.mycompany.myapp.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CheckListCriteriaTest {

    @Test
    void newCheckListCriteriaHasAllFiltersNullTest() {
        var checkListCriteria = new CheckListCriteria();
        assertThat(checkListCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void checkListCriteriaFluentMethodsCreatesFiltersTest() {
        var checkListCriteria = new CheckListCriteria();

        setAllFilters(checkListCriteria);

        assertThat(checkListCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void checkListCriteriaCopyCreatesNullFilterTest() {
        var checkListCriteria = new CheckListCriteria();
        var copy = checkListCriteria.copy();

        assertThat(checkListCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(checkListCriteria)
        );
    }

    @Test
    void checkListCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var checkListCriteria = new CheckListCriteria();
        setAllFilters(checkListCriteria);

        var copy = checkListCriteria.copy();

        assertThat(checkListCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(checkListCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var checkListCriteria = new CheckListCriteria();

        assertThat(checkListCriteria).hasToString("CheckListCriteria{}");
    }

    private static void setAllFilters(CheckListCriteria checkListCriteria) {
        checkListCriteria.id();
        checkListCriteria.client();
        checkListCriteria.contractNumber();
        checkListCriteria.adress();
        checkListCriteria.technicienDef();
        checkListCriteria.date();
        checkListCriteria.tableDetectionTaskStatus();
        checkListCriteria.diDMTaskStatus();
        checkListCriteria.detecteursPonctuelsTaskStatus();
        checkListCriteria.declencheurManuelsTaskStatus();
        checkListCriteria.tableMiseSecurityIncendieTaskStatus();
        checkListCriteria.alimentationSecoursTaskStatus();
        checkListCriteria.equipementAlarmeTaskStatus();
        checkListCriteria.dasTaskStatus();
        checkListCriteria.arretTechniqueTaskStatus();
        checkListCriteria.baiePcsTaskStatus();
        checkListCriteria.superviseurTaskStatus();
        checkListCriteria.validate();
        checkListCriteria.createdBy();
        checkListCriteria.distinct();
    }

    private static Condition<CheckListCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getClient()) &&
                condition.apply(criteria.getContractNumber()) &&
                condition.apply(criteria.getAdress()) &&
                condition.apply(criteria.getTechnicienDef()) &&
                condition.apply(criteria.getDate()) &&
                condition.apply(criteria.getTableDetectionTaskStatus()) &&
                condition.apply(criteria.getDiDMTaskStatus()) &&
                condition.apply(criteria.getDetecteursPonctuelsTaskStatus()) &&
                condition.apply(criteria.getDeclencheurManuelsTaskStatus()) &&
                condition.apply(criteria.getTableMiseSecurityIncendieTaskStatus()) &&
                condition.apply(criteria.getAlimentationSecoursTaskStatus()) &&
                condition.apply(criteria.getEquipementAlarmeTaskStatus()) &&
                condition.apply(criteria.getDasTaskStatus()) &&
                condition.apply(criteria.getArretTechniqueTaskStatus()) &&
                condition.apply(criteria.getBaiePcsTaskStatus()) &&
                condition.apply(criteria.getSuperviseurTaskStatus()) &&
                condition.apply(criteria.getValidate()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CheckListCriteria> copyFiltersAre(CheckListCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getClient(), copy.getClient()) &&
                condition.apply(criteria.getContractNumber(), copy.getContractNumber()) &&
                condition.apply(criteria.getAdress(), copy.getAdress()) &&
                condition.apply(criteria.getTechnicienDef(), copy.getTechnicienDef()) &&
                condition.apply(criteria.getDate(), copy.getDate()) &&
                condition.apply(criteria.getTableDetectionTaskStatus(), copy.getTableDetectionTaskStatus()) &&
                condition.apply(criteria.getDiDMTaskStatus(), copy.getDiDMTaskStatus()) &&
                condition.apply(criteria.getDetecteursPonctuelsTaskStatus(), copy.getDetecteursPonctuelsTaskStatus()) &&
                condition.apply(criteria.getDeclencheurManuelsTaskStatus(), copy.getDeclencheurManuelsTaskStatus()) &&
                condition.apply(criteria.getTableMiseSecurityIncendieTaskStatus(), copy.getTableMiseSecurityIncendieTaskStatus()) &&
                condition.apply(criteria.getAlimentationSecoursTaskStatus(), copy.getAlimentationSecoursTaskStatus()) &&
                condition.apply(criteria.getEquipementAlarmeTaskStatus(), copy.getEquipementAlarmeTaskStatus()) &&
                condition.apply(criteria.getDasTaskStatus(), copy.getDasTaskStatus()) &&
                condition.apply(criteria.getArretTechniqueTaskStatus(), copy.getArretTechniqueTaskStatus()) &&
                condition.apply(criteria.getBaiePcsTaskStatus(), copy.getBaiePcsTaskStatus()) &&
                condition.apply(criteria.getSuperviseurTaskStatus(), copy.getSuperviseurTaskStatus()) &&
                condition.apply(criteria.getValidate(), copy.getValidate()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
