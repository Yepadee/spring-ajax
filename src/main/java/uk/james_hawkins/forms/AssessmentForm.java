package uk.james_hawkins.forms;

import javax.validation.constraints.Size;

import uk.james_hawkins.model.project.Assessment;

public class AssessmentForm {
	private Long assessmentId;
	
	@Size(min = 1, message = "Assessment name cannot be blank")
	private String assessmentName;
	private String assessmentDescription;
	
	public AssessmentForm() {}
	
	public AssessmentForm(Assessment assessment) {
		this.assessmentId = assessment.getAssessmentId();
		this.assessmentName = assessment.getAssessmentName();
		this.assessmentDescription = assessment.getAssessmentDescription();
		
	}
	
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
	
	public String getAssessmentDescription() {
		return assessmentDescription;
	}
	
	public void setAssessmentDescription(String assessmentDescription) {
		this.assessmentDescription = assessmentDescription;
	}

	public void applyChanges(Assessment oldAssessment) {
		oldAssessment.setAssessmentName(assessmentName);
		oldAssessment.setAssessmentDescription(assessmentDescription);
	}
}
