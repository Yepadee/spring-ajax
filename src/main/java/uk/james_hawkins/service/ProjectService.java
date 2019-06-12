package uk.james_hawkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.model.Project;
import uk.james_hawkins.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		projectRepository.findAll().forEach(projects::add);
		return projects;
	}
		
	public Long addProject(Project project) {
		return projectRepository.save(project).getProjectId();
	}
	
}
