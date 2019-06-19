package uk.james_hawkins.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.project.Submission;

@DiscriminatorValue("student")
@Entity
public class Student extends User {
	//The mapped by + cascade goes on the side of the entity who is not updated directly
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "cohortMembers") @JsonIgnore
	public List<Cohort> studentCohorts = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "submissionStudent")
	public List<Submission> studentSubmissions = new ArrayList<>();

	public List<Cohort> getStudentCohorts() {
		return studentCohorts;
	}

	public void setStudentCohorts(List<Cohort> studentCohorts) {
		this.studentCohorts = studentCohorts;
	}

	public List<Submission> getStudentSubmissions() {
		return studentSubmissions;
	}

	public void setStudentSubmissions(List<Submission> studentSubmissions) {
		this.studentSubmissions = studentSubmissions;
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
