package uk.james_hawkins.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Project {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_gen")
	private Long projectId;
	@Size(min = 1, message = "Project name cannot be blank!")
	private String projectName;
	private String projectDescription;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "cohort_id", nullable = false)
	private Cohort projectCohort;
	
	@Valid @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "assessmentProject")
	private List<Assessment> projectAssessments = new ArrayList<>();

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Cohort getProjectCohort() {
		return projectCohort;
	}

	public void setProjectCohort(Cohort projectCohort) {
		this.projectCohort = projectCohort;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Assessment> getProjectAssessments() {
		return projectAssessments;
	}

	public void setProjectAssessments(List<Assessment> projectAssessments) {
		this.projectAssessments = projectAssessments;
	}
	
	public void addAssessment(Assessment assessment) {
		assessment.setAssessmentProject(this);
		this.projectAssessments.add(assessment);
	}
	
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		String str = super.toString();
		try {
			str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
