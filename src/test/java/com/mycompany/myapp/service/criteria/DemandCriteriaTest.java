package com.mycompany.myapp.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DemandCriteriaTest {

    @Test
    void newDemandCriteriaHasAllFiltersNullTest() {
        var demandCriteria = new DemandCriteria();
        assertThat(demandCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void demandCriteriaFluentMethodsCreatesFiltersTest() {
        var demandCriteria = new DemandCriteria();

        setAllFilters(demandCriteria);

        assertThat(demandCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void demandCriteriaCopyCreatesNullFilterTest() {
        var demandCriteria = new DemandCriteria();
        var copy = demandCriteria.copy();

        assertThat(demandCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(demandCriteria)
        );
    }

    @Test
    void demandCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var demandCriteria = new DemandCriteria();
        setAllFilters(demandCriteria);

        var copy = demandCriteria.copy();

        assertThat(demandCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(demandCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var demandCriteria = new DemandCriteria();

        assertThat(demandCriteria).hasToString("DemandCriteria{}");
    }

    private static void setAllFilters(DemandCriteria demandCriteria) {
        demandCriteria.id();
        demandCriteria.qT();
        demandCriteria.demandBy();
        demandCriteria.demandDate();
        demandCriteria.status();
        demandCriteria.validate();
        demandCriteria.nameId();
        demandCriteria.distinct();
    }

    private static Condition<DemandCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getqT()) &&
                condition.apply(criteria.getDemandBy()) &&
                condition.apply(criteria.getDemandDate()) &&
                condition.apply(criteria.getStatus()) &&
                condition.apply(criteria.getValidate()) &&
                condition.apply(criteria.getNameId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DemandCriteria> copyFiltersAre(DemandCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getqT(), copy.getqT()) &&
                condition.apply(criteria.getDemandBy(), copy.getDemandBy()) &&
                condition.apply(criteria.getDemandDate(), copy.getDemandDate()) &&
                condition.apply(criteria.getStatus(), copy.getStatus()) &&
                condition.apply(criteria.getValidate(), copy.getValidate()) &&
                condition.apply(criteria.getNameId(), copy.getNameId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
