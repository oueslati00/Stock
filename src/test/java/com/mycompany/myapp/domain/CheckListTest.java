package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CheckListTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckList.class);
        CheckList checkList1 = getCheckListSample1();
        CheckList checkList2 = new CheckList();
        assertThat(checkList1).isNotEqualTo(checkList2);

        checkList2.setId(checkList1.getId());
        assertThat(checkList1).isEqualTo(checkList2);

        checkList2 = getCheckListSample2();
        assertThat(checkList1).isNotEqualTo(checkList2);
    }
}
