package com.tw.skillapp.web.rest.models;

import java.util.ArrayList;
import java.util.List;

public class UserApplications {

    private List<UserApplicationDTO> userApplications = new ArrayList<>();

    public List<UserApplicationDTO> getUserApplications() {
        return userApplications;
    }

    public void setUserApplications(List<UserApplicationDTO> userApplications) {
        this.userApplications = userApplications;
    }
}
