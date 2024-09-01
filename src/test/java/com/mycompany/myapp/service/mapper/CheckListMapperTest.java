package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.CheckListAsserts.*;
import static com.mycompany.myapp.domain.CheckListTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckListMapperTest {

    private CheckListMapper checkListMapper;

    @BeforeEach
    void setUp() {
        checkListMapper = new CheckListMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCheckListSample1();
        var actual = checkListMapper.toEntity(checkListMapper.toDto(expected));
        assertCheckListAllPropertiesEquals(expected, actual);
    }
}
