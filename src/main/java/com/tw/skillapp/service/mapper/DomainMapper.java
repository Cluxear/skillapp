package com.tw.skillapp.service.mapper;


import com.tw.skillapp.domain.*;
import com.tw.skillapp.service.dto.DomainDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Domain} and its DTO {@link DomainDTO}.
 */
@Mapper(componentModel = "spring", uses = {SkillMapper.class})
public interface DomainMapper extends EntityMapper<DomainDTO, Domain> {

    @Mapping(source = "skill.id", target = "skillId")
    DomainDTO toDto(Domain domain);

    @Mapping(source = "skillId", target = "skill")
    Domain toEntity(DomainDTO domainDTO);

    default Domain fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domain domain = new Domain();
        domain.setId(id);
        return domain;
    }
}
