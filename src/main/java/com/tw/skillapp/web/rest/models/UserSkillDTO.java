package com.tw.skillapp.web.rest.models;

public class UserSkillDTO {
    private Long id;

    private String userId;

    private Long skillId;

    private SkillLevel skillLevel;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSkillDTO)) {
            return false;
        }

        return id != null && id.equals(((UserSkillDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserSkillDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", skillId=" + getSkillId() +
            "}";
    }

}
