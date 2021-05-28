package com.tw.skillapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tw.skillapp.web.rest.TestUtil;

public class DomainDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomainDTO.class);
        DomainDTO domainDTO1 = new DomainDTO();
        domainDTO1.setId(1L);
        DomainDTO domainDTO2 = new DomainDTO();
        assertThat(domainDTO1).isNotEqualTo(domainDTO2);
        domainDTO2.setId(domainDTO1.getId());
        assertThat(domainDTO1).isEqualTo(domainDTO2);
        domainDTO2.setId(2L);
        assertThat(domainDTO1).isNotEqualTo(domainDTO2);
        domainDTO1.setId(null);
        assertThat(domainDTO1).isNotEqualTo(domainDTO2);
    }
}
