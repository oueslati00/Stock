package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckListDTO.class);
        CheckListDTO checkListDTO1 = new CheckListDTO();
        checkListDTO1.setId(1L);
        CheckListDTO checkListDTO2 = new CheckListDTO();
        assertThat(checkListDTO1).isNotEqualTo(checkListDTO2);
        checkListDTO2.setId(checkListDTO1.getId());
        assertThat(checkListDTO1).isEqualTo(checkListDTO2);
        checkListDTO2.setId(2L);
        assertThat(checkListDTO1).isNotEqualTo(checkListDTO2);
        checkListDTO1.setId(null);
        assertThat(checkListDTO1).isNotEqualTo(checkListDTO2);
    }
}
