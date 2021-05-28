package com.tw.skillapp.web.rest.models;

import com.tw.skillapp.domain.Skill;
import com.tw.skillapp.service.dto.SkillDTO;

import java.util.ArrayList;
import java.util.List;

public class SkillMatrix {

    private List<SkillDTO> skills = new ArrayList<>();

    private String firstName;

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "SkillMatrix{" +
            "skills=" + skills +
            ", firstName='" + firstName + '\'' +
            '}';
    }
}
