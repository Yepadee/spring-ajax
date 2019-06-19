package uk.james_hawkins.model.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.james_hawkins.model.user.Student;

@Entity
public class Submission {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long submissionId;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "assessment_id", nullable = false)
	private Assessment submissionAssessment;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "student_id", nullable = false)
	private Student submissionStudent;
	
	private String submissionComments;
	
//	private List<FileData> submissions;
	
	public Submission() {}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public Assessment getSubmissionAssessment() {
		return submissionAssessment;
	}

	public void setSubmissionAssessment(Assessment submissionAssessment) {
		this.submissionAssessment = submissionAssessment;
	}

	public Student getSubmissionStudent() {
		return submissionStudent;
	}

	public void setSubmissionStudent(Student submissionStudent) {
		this.submissionStudent = submissionStudent;
	}

	public String getSubmissionComments() {
		return submissionComments;
	}

	public void setSubmissionComments(String submissionComments) {
		this.submissionComments = submissionComments;
	}
	
	
}
