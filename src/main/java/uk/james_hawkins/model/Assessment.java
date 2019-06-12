package uk.james_hawkins.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Assessment {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assessment_gen")
	private Long assessmentId;
	@Size(min = 1, message = "Assessment name cannot be blank!")
	private String assessmentName;
	private String assessmentDescription;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "project_id", nullable = false)
	private Project assessmentProject;

	
	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public Project getAssessmentProject() {
		return assessmentProject;
	}

	public void setAssessmentProject(Project assessmentProject) {
		this.assessmentProject = assessmentProject;
	}

	public String getAssessmentDescription() {
		return assessmentDescription;
	}

	public void setAssessmentDescription(String assessmentDescription) {
		this.assessmentDescription = assessmentDescription;
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
