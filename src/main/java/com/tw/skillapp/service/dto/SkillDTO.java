package com.tw.skillapp.service.dto;

import com.tw.skillapp.web.rest.models.SkillLevel;

import java.io.Serializable;

/**
 * A DTO for the {@link com.tw.skillapp.domain.Skill} entity.
 */
public class SkillDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private SkillLevel skillLevel;


    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillDTO)) {
            return false;
        }

        return id != null && id.equals(((SkillDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            " level" + getSkillLevel() +
            "}";
    }
}
