package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DemandTestSamples.*;
import static com.mycompany.myapp.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = getProductSample1();
        Product product2 = new Product();
        assertThat(product1).isNotEqualTo(product2);

        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);

        product2 = getProductSample2();
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void demandTest() {
        Product product = getProductRandomSampleGenerator();
        Demand demandBack = getDemandRandomSampleGenerator();

        product.addDemand(demandBack);
        assertThat(product.getDemands()).containsOnly(demandBack);
        assertThat(demandBack.getName()).isEqualTo(product);

        product.removeDemand(demandBack);
        assertThat(product.getDemands()).doesNotContain(demandBack);
        assertThat(demandBack.getName()).isNull();

        product.demands(new HashSet<>(Set.of(demandBack)));
        assertThat(product.getDemands()).containsOnly(demandBack);
        assertThat(demandBack.getName()).isEqualTo(product);

        product.setDemands(new HashSet<>());
        assertThat(product.getDemands()).doesNotContain(demandBack);
        assertThat(demandBack.getName()).isNull();
    }
}
