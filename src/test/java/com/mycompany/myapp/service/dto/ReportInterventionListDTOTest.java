package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportInterventionListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportInterventionListDTO.class);
        ReportInterventionListDTO reportInterventionListDTO1 = new ReportInterventionListDTO();
        reportInterventionListDTO1.setId(1L);
        ReportInterventionListDTO reportInterventionListDTO2 = new ReportInterventionListDTO();
        assertThat(reportInterventionListDTO1).isNotEqualTo(reportInterventionListDTO2);
        reportInterventionListDTO2.setId(reportInterventionListDTO1.getId());
        assertThat(reportInterventionListDTO1).isEqualTo(reportInterventionListDTO2);
        reportInterventionListDTO2.setId(2L);
        assertThat(reportInterventionListDTO1).isNotEqualTo(reportInterventionListDTO2);
        reportInterventionListDTO1.setId(null);
        assertThat(reportInterventionListDTO1).isNotEqualTo(reportInterventionListDTO2);
    }
}
