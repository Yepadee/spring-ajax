package uk.james_hawkins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.Project;
import uk.james_hawkins.service.CohortService;
import uk.james_hawkins.service.ProjectService;

@Controller
public class MainController {
	private String projectPage = "projects";
	private String cohortPage = "cohorts";
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CohortService cohortService;
	
	@GetMapping
	public String main() {
		return "redirect:/projects";
	}
	
	@GetMapping("/projects")
	public String getProjects(Model model) {
		List<Project> projects = projectService.getAllProjects();
		model.addAttribute("projects", projects);
		return projectPage;
	}
	
	@GetMapping("/cohorts")
	public String getCohorts(Model model) {
		List<Cohort> cohorts = cohortService.getAllCohorts();
		model.addAttribute("cohorts", cohorts);
		return cohortPage;
	}
}
