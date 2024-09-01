package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DemandAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDemandAllPropertiesEquals(Demand expected, Demand actual) {
        assertDemandAutoGeneratedPropertiesEquals(expected, actual);
        assertDemandAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDemandAllUpdatablePropertiesEquals(Demand expected, Demand actual) {
        assertDemandUpdatableFieldsEquals(expected, actual);
        assertDemandUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDemandAutoGeneratedPropertiesEquals(Demand expected, Demand actual) {
        assertThat(expected)
            .as("Verify Demand auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDemandUpdatableFieldsEquals(Demand expected, Demand actual) {
        assertThat(expected)
            .as("Verify Demand relevant properties")
            .satisfies(e -> assertThat(e.getqT()).as("check qT").isEqualTo(actual.getqT()))
            .satisfies(e -> assertThat(e.getDemandBy()).as("check demandBy").isEqualTo(actual.getDemandBy()))
            .satisfies(e -> assertThat(e.getDemandDate()).as("check demandDate").isEqualTo(actual.getDemandDate()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getValidate()).as("check validate").isEqualTo(actual.getValidate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDemandUpdatableRelationshipsEquals(Demand expected, Demand actual) {
        assertThat(expected)
            .as("Verify Demand relationships")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }
}
