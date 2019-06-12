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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Cohort {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cohort_gen")
	private Long cohortId;
	
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "unit_id", nullable = false)
	private Unit cohortUnit;
	private Integer cohortYear;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projectCohort")
	private List<Project> cohortProjects = new ArrayList<>();
	//private List<Student> cohortMembers;
	
	public Cohort() {}

	public Long getCohortId() {
		return cohortId;
	}

	public void setCohortId(Long cohortId) {
		this.cohortId = cohortId;
	}

	public Unit getCohortUnit() {
		return cohortUnit;
	}

	public void setCohortUnit(Unit cohortUnit) {
		this.cohortUnit = cohortUnit;
	}

	public Integer getCohortYear() {
		return cohortYear;
	}

	public void setCohortYear(Integer cohortYear) {
		this.cohortYear = cohortYear;
	}

	public List<Project> getCohortProjects() {
		return cohortProjects;
	}

	public void setCohortProjects(List<Project> cohortProjects) {
		this.cohortProjects = cohortProjects;
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
