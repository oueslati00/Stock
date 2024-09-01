package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.DemandAsserts.*;
import static com.mycompany.myapp.domain.DemandTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemandMapperTest {

    private DemandMapper demandMapper;

    @BeforeEach
    void setUp() {
        demandMapper = new DemandMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDemandSample1();
        var actual = demandMapper.toEntity(demandMapper.toDto(expected));
        assertDemandAllPropertiesEquals(expected, actual);
    }
}
