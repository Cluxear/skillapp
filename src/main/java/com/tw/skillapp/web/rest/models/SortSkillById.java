package com.tw.skillapp.web.rest.models;

import com.tw.skillapp.service.dto.SkillDTO;

import java.util.Comparator;

public class SortSkillById implements Comparator<SkillDTO> {


    public int compare(SkillDTO a, SkillDTO b)
    {
        return a.getName().compareTo(b.getName());
    }
}
