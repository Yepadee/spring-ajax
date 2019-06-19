package uk.james_hawkins.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.project.Assessment;
import uk.james_hawkins.model.project.Project;
import uk.james_hawkins.model.project.Submission;
import uk.james_hawkins.model.user.Student;

public class ProjectForm {
	
	private Long projectId;
	
	@Size(min = 1, message = "Project name cannot be blank")
	private String projectName;
	private String projectDescription;
	@NotNull(message = "You must select a cohort")
	private Cohort projectCohort;
	
	private List<AssessmentForm> projectAssessments = new ArrayList<>();
	
	public ProjectForm() {}
	
	public ProjectForm(Project project) {
		this.projectId = project.getProjectId();
		this.projectName = project.getProjectName();
		this.projectDescription = project.getProjectDescription();
		this.projectCohort = project.getProjectCohort();
		
		for (Assessment assessment : project.getProjectAssessments()) {
			this.projectAssessments.add(new AssessmentForm(assessment));
		}
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<AssessmentForm> getProjectAssessments() {
		return projectAssessments;
	}

	public void setProjectAssessments(List<AssessmentForm> projectAssessments) {
		this.projectAssessments = projectAssessments;
	}
	
	public Cohort getProjectCohort() {
		return projectCohort;
	}

	public void setProjectCohort(Cohort projectCohort) {
		this.projectCohort = projectCohort;
	}

	public void applyChanges(Project project) {
		project.setProjectName(projectName);
		project.setProjectDescription(projectDescription);
		project.setProjectCohort(projectCohort);
		
		
		List<Assessment> preservedAssessments = new ArrayList<>();
		List<Assessment> oldAssessments = new ArrayList<>(project.getProjectAssessments()); //Record the old assessments
		
		for (AssessmentForm assessmentForm : projectAssessments) {
			Assessment assessment;
			
			if (isNewAssessment(assessmentForm)) { //Add new assessment
				assessment = new Assessment();
				project.addAssessment(assessment);
				
				for (Student student : projectCohort.getCohortMembers()) {
					Submission submission = new Submission();
					submission.setSubmissionStudent(student);
					submission.setSubmissionAssessment(assessment);
					assessment.getAssessmentSubmissions().add(submission);
				}
				
			} else { //Edit existing assessment
				assessment = findExistingAssessment(project, assessmentForm);
				
				if (cohortWasChanged(project)) {
					assessment.getAssessmentSubmissions().removeAll(assessment.getAssessmentSubmissions());
					for (Student student : projectCohort.getCohortMembers()) {
						Submission submission = new Submission();
						submission.setSubmissionStudent(student);
						submission.setSubmissionAssessment(assessment);
						assessment.getAssessmentSubmissions().add(submission);
					}
				}
				
				preservedAssessments.add(assessment);
			}
			assessmentForm.applyChanges(assessment);
		}
		
		//Remove deleted assessments
		oldAssessments.removeAll(preservedAssessments);
		project.getProjectAssessments().removeAll(oldAssessments);
	}
	
	private boolean cohortWasChanged(Project oldProject) {
		return oldProject.getProjectCohort().getCohortId() != projectCohort.getCohortId();
	}
	
	private boolean isNewAssessment(AssessmentForm assessmentForm) {
		return assessmentForm.getAssessmentId() == null;
	}
	
	private Assessment findExistingAssessment(Project project, AssessmentForm assessmentForm) {
		return project.getProjectAssessments().stream().filter(a -> a.getAssessmentId().equals(assessmentForm.getAssessmentId())).findFirst().get();
	}
}
