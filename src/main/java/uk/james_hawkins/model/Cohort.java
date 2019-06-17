package uk.james_hawkins.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.james_hawkins.model.user.Staff;
import uk.james_hawkins.model.user.Student;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"unit_id", "cohortYear"})
	}) 
public class Cohort {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cohortId;
	
	@NotNull(message = "No unit selected")
	@ManyToOne @JsonIgnore
	@JoinColumn(name = "unit_id", nullable = false)
	private Unit cohortUnit;
	
	@NotNull(message = "Cohort year cannot be blank")
	@Column(nullable = false)
	private Integer cohortYear;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projectCohort")
	private List<Project> cohortProjects = new ArrayList<>();
	
	@NotNull(message = "No unit director selected")
	@ManyToOne
	@JoinColumn(name = "unit_director_id", nullable = false)
	private Staff cohortUnitDirector;
	
	@ManyToMany
	private List<Staff> cohortStaff = new ArrayList<>();
	
	@ManyToMany
	private List<Student> cohortMembers = new ArrayList<>();
	
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
	
	public Staff getCohortUnitDirector() {
		return cohortUnitDirector;
	}

	public void setCohortUnitDirector(Staff cohortUnitDirector) {
		this.cohortUnitDirector = cohortUnitDirector;
	}

	public List<Staff> getCohortStaff() {
		return cohortStaff;
	}

	public void setCohortStaff(List<Staff> cohortStaff) {
		this.cohortStaff = cohortStaff;
	}
	
	public void addCohortStaff(Staff cohortStaff) {
		this.cohortStaff.add(cohortStaff);
	}

	public List<Student> getCohortMembers() {
		return cohortMembers;
	}

	public void setCohortMembers(List<Student> cohortMembers) {
		this.cohortMembers = cohortMembers;
	}
	
	public void addCohortMember(Student cohortMember) {
		cohortMembers.add(cohortMember);
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
