package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("checked","all");
        return "search";

    }

  @PostMapping("results")
    public String displaySearchResults(@RequestParam String searchType,@RequestParam String searchTerm, Model model){
       List<Job> jobs;
        if(searchTerm.toLowerCase().equals("all")){
            jobs = JobData.findAll();
        }else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        String searchColumn;
        if(searchType.toLowerCase().equals("corecompetency")){
            searchColumn = "Skill";
        }else {
            searchColumn = searchType;
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs",jobs);
        model.addAttribute("title", "Jobs With " +searchColumn +": " +searchTerm);
        model.addAttribute("checked",searchType);
        return "search";
    }
}
