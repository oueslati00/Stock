package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.ReportInterventionListAsserts.*;
import static com.mycompany.myapp.domain.ReportInterventionListTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportInterventionListMapperTest {

    private ReportInterventionListMapper reportInterventionListMapper;

    @BeforeEach
    void setUp() {
        reportInterventionListMapper = new ReportInterventionListMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getReportInterventionListSample1();
        var actual = reportInterventionListMapper.toEntity(reportInterventionListMapper.toDto(expected));
        assertReportInterventionListAllPropertiesEquals(expected, actual);
    }
}
