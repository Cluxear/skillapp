package com.tw.skillapp.web.rest.models;

import java.util.ArrayList;
import java.util.List;

public class UserSkills {



	List<UserSkillDTO> userSkills = new ArrayList<>();

	public List<UserSkillDTO> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkillDTO> userSkills) {
		this.userSkills = userSkills;
	}






}
