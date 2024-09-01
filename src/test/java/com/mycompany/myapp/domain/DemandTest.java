package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DemandTestSamples.*;
import static com.mycompany.myapp.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DemandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demand.class);
        Demand demand1 = getDemandSample1();
        Demand demand2 = new Demand();
        assertThat(demand1).isNotEqualTo(demand2);

        demand2.setId(demand1.getId());
        assertThat(demand1).isEqualTo(demand2);

        demand2 = getDemandSample2();
        assertThat(demand1).isNotEqualTo(demand2);
    }

    @Test
    void nameTest() {
        Demand demand = getDemandRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        demand.setName(productBack);
        assertThat(demand.getName()).isEqualTo(productBack);

        demand.name(null);
        assertThat(demand.getName()).isNull();
    }
}
