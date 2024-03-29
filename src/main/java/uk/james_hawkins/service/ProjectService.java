package uk.james_hawkins.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.forms.ProjectForm;
import uk.james_hawkins.model.project.Project;
import uk.james_hawkins.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project getProject(Long projectId) {
		Optional<Project> maybeProject = projectRepository.findById(projectId);
		if (maybeProject.isPresent()) {
			return maybeProject.get();
		} else {
			throw new RuntimeException("No project found with id \'" + projectId + "\'.");
		}
	}
	
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		projectRepository.findAll().forEach(projects::add);
		return projects;
	}
		
	public Long addProject(Project project) {
		return projectRepository.save(project).getProjectId();
	}
	
	public Long addProject(ProjectForm projectForm) {
    	Project project;
    	if (projectForm.getProjectId() == null) {
    		project = new Project();
    	} else {
    		project = getProject(projectForm.getProjectId());
    	}
    	projectForm.applyChanges(project);
    	return addProject(project);
	}
	
}
