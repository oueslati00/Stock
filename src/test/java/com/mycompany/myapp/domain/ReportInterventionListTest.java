package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ReportInterventionListTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReportInterventionListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportInterventionList.class);
        ReportInterventionList reportInterventionList1 = getReportInterventionListSample1();
        ReportInterventionList reportInterventionList2 = new ReportInterventionList();
        assertThat(reportInterventionList1).isNotEqualTo(reportInterventionList2);

        reportInterventionList2.setId(reportInterventionList1.getId());
        assertThat(reportInterventionList1).isEqualTo(reportInterventionList2);

        reportInterventionList2 = getReportInterventionListSample2();
        assertThat(reportInterventionList1).isNotEqualTo(reportInterventionList2);
    }
}
