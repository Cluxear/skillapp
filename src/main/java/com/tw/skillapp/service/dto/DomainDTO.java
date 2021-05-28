package com.tw.skillapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.tw.skillapp.domain.Domain} entity.
 */
public class DomainDTO implements Serializable {
    
    private Long id;

    private String name;


    private Long skillId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomainDTO)) {
            return false;
        }

        return id != null && id.equals(((DomainDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomainDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", skillId=" + getSkillId() +
            "}";
    }
}
