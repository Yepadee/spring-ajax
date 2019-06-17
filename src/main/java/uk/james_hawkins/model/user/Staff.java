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

@DiscriminatorValue("staff")
@Entity
public class Staff extends Student {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cohortUnitDirector") @JsonIgnore
	private List<Cohort> unitDirectorCohorts = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "cohortStaff") @JsonIgnore
	private List<Cohort> staffCohorts = new ArrayList<>();
	
	public List<Cohort> getUnitDirectorCohorts() {
		return unitDirectorCohorts;
	}

	public void setUnitDirectorCohorts(List<Cohort> unitDirectorCohorts) {
		this.unitDirectorCohorts = unitDirectorCohorts;
	}

	public List<Cohort> getStaffCohorts() {
		return staffCohorts;
	}

	public void setStaffCohorts(List<Cohort> staffCohorts) {
		this.staffCohorts = staffCohorts;
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
