package com.tw.skillapp.web.rest;

import com.tw.skillapp.service.SkillService;
import com.tw.skillapp.service.dto.UserDTO;
import com.tw.skillapp.web.rest.errors.BadRequestAlertException;
import com.tw.skillapp.service.dto.SkillDTO;

import com.tw.skillapp.web.rest.feignClients.DataappService;
import com.tw.skillapp.web.rest.feignClients.UserAppService;
import com.tw.skillapp.web.rest.models.*;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.tw.skillapp.domain.Skill}.
 */
@RestController
@RequestMapping("/api")
public class SkillResource {

    private final Logger log = LoggerFactory.getLogger(SkillResource.class);

    private static final String ENTITY_NAME = "skillappSkill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillService skillService;

    private final DataappService dataappService;

    private final UserAppService userAppService;

    public SkillResource(SkillService skillService, DataappService dataappService, UserAppService userAppService) {
        this.skillService = skillService;
        this.dataappService = dataappService;
        this.userAppService = userAppService;
    }

    /**
     * {@code POST  /skills} : Create a new skill.
     *
     * @param skillDTO the skillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillDTO, or with status {@code 400 (Bad Request)} if the skill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skills")
    public ResponseEntity<SkillDTO> createSkill(@RequestBody SkillDTO skillDTO) throws URISyntaxException {
        log.debug("REST request to save Skill : {}", skillDTO);
        if (skillDTO.getId() != null) {
            throw new BadRequestAlertException("A new skill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillDTO result = skillService.save(skillDTO);
        return ResponseEntity.created(new URI("/api/skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skills} : Updates an existing skill.
     *
     * @param skillDTO the skillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillDTO,
     * or with status {@code 400 (Bad Request)} if the skillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skills")
    public ResponseEntity<SkillDTO> updateSkill(@RequestBody SkillDTO skillDTO) throws URISyntaxException {
        log.debug("REST request to update Skill : {}", skillDTO);
        if (skillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillDTO result = skillService.save(skillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, skillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skills} : get all the skills.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skills in body.
     */
    @GetMapping("/skills")
    public List<SkillDTO> getAllSkills() {
        log.debug("REST request to get all Skills");

        return skillService.findAll();
    }

    @GetMapping("/skills/userId/{userId}")
    public List<SkillDTO> getUserSkills(@PathVariable String userId) {
        log.debug("REST request to get all Skills for user");
        UserSkills userSkills = dataappService.getListOfUserSkills(userId);
        List<SkillDTO> skills = new ArrayList<>();
        for( UserSkillDTO userSkill: userSkills.getUserSkills()) {


            Optional<SkillDTO> skill = skillService.findOne(userSkill.getSkillId());
            if(skill.isPresent()){
                skill.get().setSkillLevel(userSkill.getSkillLevel());
                skill.ifPresent(skills::add);
            }

        }
        return skills;
    }
    @GetMapping("/skills/jobpostid/{jobPostId}")
    public List<SkillDTO> getjobPostSkills(@PathVariable Long jobPostId) {
        log.debug("REST request to get all Skills for user");
        List<SkillJobPostDTO> jobPostSkillsIds = dataappService.getListOfJobPostSkills(jobPostId);
        List<SkillDTO> jobPostSkillData = new ArrayList<>();
        for(SkillJobPostDTO skillJobPostDTO: jobPostSkillsIds) {

            jobPostSkillData.add(getSkill(skillJobPostDTO.getSkillId()).getBody());
        }
        Collections.sort(jobPostSkillData, new SortSkillById());

        return jobPostSkillData;
    }

    @GetMapping("/skills/matrix/jobpostId/{jobPostId}")
    public List<SkillMatrix> getCandidateSkillsForJobPost(@PathVariable Long jobPostId) {

        log.debug("REST request to get all users for jobPost and their skills");
        // get the list of users with that jobpostId
        UserApplications userApplications = dataappService.getListOfUserApplicationsForJobPost(jobPostId);
        List<SkillDTO> skills = new ArrayList<>();
        List<SkillDTO> jobPostSkillData = getjobPostSkills(jobPostId);
        List<SkillMatrix> userSkills = new ArrayList<SkillMatrix>();


        for(UserApplicationDTO userApplication: userApplications.getUserApplications())
        {
            // get Skills for every user;
                skills = getUserSkills(userApplication.getUserId());
            // Filter user skills so that only skills needed for jobpost are left
                skills = skills.stream()
                                .filter(skill ->
                                        jobPostSkillData.stream().anyMatch(jpSkill ->
                                            jpSkill.getId().equals(skill.getId())
                                            )  ).collect(Collectors.toList());

                // check if skill exists for candidate if not add it
                // TODO: add skillLevel 0 for that unfound skill
                for(SkillDTO jobPostSkills: jobPostSkillData) {
                 Optional<SkillDTO> skDTO =   skills.stream().filter(v -> v.getId().equals(jobPostSkills.getId())).findAny();
                 if(!skDTO.isPresent()) {
                     SkillDTO newSkill = new SkillDTO();
                     newSkill.setId(jobPostSkills.getId());
                     newSkill.setName(jobPostSkills.getName());
                     newSkill.setSkillLevel(SkillLevel.NOVICE);
                     skills.add(newSkill);
                 }

                }
                //sort the user skills
                Collections.sort(skills, new SortSkillById());
                // Add the user skills to the UserSkillMatrix and append the userName

                // TODO: Get the userName instead of his id;
                SkillMatrix userSkillMatrix = new SkillMatrix();
                userSkillMatrix.setSkills(skills);
                // get user from id;
                 UserDTO result = userAppService.getUserById(userApplication.getUserId()).getBody();

                userSkillMatrix.setFirstName(result.getFirstName() + " " + result.getLastName());
                userSkills.add(userSkillMatrix);
        }


            // if candidate skill doesnt exist in skills required delete it,
            // if a candidate doesn't have a required skill than add an instance of it.
            //

            return userSkills;
    }

    /**
     * {@code GET  /skills/:id} : get the "id" skill.
     *
     * @param id the id of the skillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skills/{id}")
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id) {
        log.debug("REST request to get Skill : {}", id);
        Optional<SkillDTO> skillDTO = skillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(skillDTO);
    }

    /**
     * {@code DELETE  /skills/:id} : delete the "id" skill.
     *
     * @param id the id of the skillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        log.debug("REST request to delete Skill : {}", id);
        skillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
