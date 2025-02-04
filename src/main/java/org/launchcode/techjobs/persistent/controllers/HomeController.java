package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());// index page shows al the job
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());// creating new object job
        model.addAttribute("employers", employerRepository.findAll());// displaying all employers in new job adding page
        model.addAttribute("skills", skillRepository.findAll());// // displaying all skills in new job adding page
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
            Optional<Employer> optemp = employerRepository.findById(employerId);//
            if (optemp.isEmpty()) {
                model.addAttribute("title", "Invalid EmployerId");
            } else {
                Employer employer = optemp.get();
                model.addAttribute("employerId", employer);
                newJob.setEmployer(employer);
                jobRepository.save(newJob);// adding job with selected employer
                model.addAttribute("jobs", jobRepository.findAll());
                return "index";
            }
            List<Skill> skillObj = (List<Skill>) skillRepository.findAllById(skills);//passing list of skills id
            newJob.setSkills(skillObj);// adding the skills to the job creating
            jobRepository.save(newJob); // saving the new job to the repo with employer and skills
            model.addAttribute("skills", skillObj);
        }
        return "index";

    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob = jobRepository.findById(jobId);// finding a job with particular id
        if (optJob.isPresent()) {// checking job object for not null
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);// passing the job of particular idto see
            return "view";
        } else {
            return "redirect:../";
        }

    }
}


