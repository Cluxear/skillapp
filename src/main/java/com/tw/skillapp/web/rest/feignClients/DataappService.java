package com.tw.skillapp.web.rest.feignClients;


import com.tw.skillapp.client.AuthorizedFeignClient;
import com.tw.skillapp.web.rest.models.SkillJobPostDTO;
import com.tw.skillapp.web.rest.models.UserApplications;
import com.tw.skillapp.web.rest.models.UserSkills;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AuthorizedFeignClient(name = "dataapp")
@RequestMapping(value = "/api")
public interface DataappService {

    @RequestMapping (value = "/user-skills/userId/{user_id}", method = RequestMethod.GET)
    UserSkills getListOfUserSkills(@RequestParam("user_id") String user_id);

    @RequestMapping (value = "/user-applications/jobpost/{jp_id}", method = RequestMethod.GET)
    UserApplications getListOfUserApplicationsForJobPost(@RequestParam("jp_id") Long jp_id);

    @RequestMapping (value = "/skill-job-posts/jobpost/{jp_id}", method = RequestMethod.GET)
    List<SkillJobPostDTO> getListOfJobPostSkills(@RequestParam("jp_id") Long jp_id);
}
