package uk.james_hawkins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uk.james_hawkins.model.Project;
import uk.james_hawkins.service.ProjectService;

@Controller
public class MainController {
	private String projectPage = "projects";
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public String main() {
		return "redirect:/new-project";
	}
	
	@GetMapping("/projects")
	public String getProjects(Model model) {
		List<Project> projects = projectService.getAllProjects();
		model.addAttribute("projects", projects);
		return projectPage;
	}
}
