package uk.james_hawkins.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.james_hawkins.model.Assessment;
import uk.james_hawkins.model.Project;
import uk.james_hawkins.service.ProjectService;

@Controller
@RequestMapping("new-project")
public class NewProjectController {
	private String newProjectPage = "project_form";
	private String newProjectFormFragment = "project_form :: content";
	
	@Autowired
	private ProjectService projectService;

	@GetMapping
	public String newProject(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return newProjectPage;
	}

	@PostMapping
	public String saveProject(@Valid @ModelAttribute Project project, BindingResult result, Model model) {
		//Can return different fragment if successful
		if (result.hasErrors()) {
			return newProjectFormFragment;
	    } else {
	    	for (Assessment a : project.getProjectAssessments()) {
	    		a.setAssessmentProject(project);
	    	}
	    	projectService.addProject(project);
	    	return newProjectFormFragment;
	    }
		
	}
	
	@PostMapping(params={"addAssessment"})
	public String addAssessment(@ModelAttribute Project project) {
		project.addAssessment(new Assessment());
		return newProjectFormFragment;
	}
	
	@PostMapping(params={"removeAssessment"})
	public String removeAssessment(@ModelAttribute Project project, @RequestParam("removeAssessment") int index) {
		project.getProjectAssessments().remove(index);
	    return newProjectFormFragment;
	}
	
}
