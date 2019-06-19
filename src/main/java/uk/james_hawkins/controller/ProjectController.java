package uk.james_hawkins.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.james_hawkins.forms.AssessmentForm;
import uk.james_hawkins.forms.ProjectForm;
import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.project.Project;
import uk.james_hawkins.service.CohortService;
import uk.james_hawkins.service.ProjectService;

@Controller
@RequestMapping("projects")
public class ProjectController {
	private String newProjectPage = "new_project";
	private String newProjectFormFragment = newProjectPage + " :: form";
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CohortService cohortService;

	@GetMapping("add")
	public String newProject(Model model) {
		ProjectForm projectForm = new ProjectForm();
		model.addAttribute("projectForm", projectForm);
		return newProjectPage;
	}
	
	@GetMapping("edit/{projectId}")
	public String newProject(@PathVariable Long projectId, Model model) {
		model.addAttribute("projectForm", new ProjectForm(projectService.getProject(projectId)));
		return newProjectPage;
	}

	@PostMapping
	public String saveProject(@Valid @ModelAttribute ProjectForm projectForm, BindingResult result, Model model) {
		//Can return different fragment if successful
		if (result.hasErrors()) {
			return newProjectFormFragment;
	    } else {
	    	projectService.addProject(projectForm);
	    	return newProjectFormFragment;
	    }
		
	}
	
	@PostMapping(params={"addAssessment"})
	public String addAssessment(@ModelAttribute ProjectForm projectForm) {
		projectForm.getProjectAssessments().add(new AssessmentForm());
		return newProjectFormFragment;
	}
	
	@PostMapping(params={"removeAssessment"})
	public String removeAssessment(@ModelAttribute ProjectForm projectForm, @RequestParam("removeAssessment") int index) {
		projectForm.getProjectAssessments().remove(index);
	    return newProjectFormFragment;
	}
	
	@PostMapping(params={"setCohort"})
	public String setCohort(@ModelAttribute ProjectForm projectForm) {
	    return newProjectFormFragment;
	}
	
	//TODO: refine list via unit/date selection
	@ModelAttribute("cohortList")
	public List<Cohort> getCohortList() {
		return cohortService.getAllCohorts();
	}
	
}
