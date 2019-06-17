package uk.james_hawkins.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Unit {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long unitId;
	
	private String unitName;
	private String unitCode;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cohortUnit")
	private List<Cohort> unitCohorts = new ArrayList<>();
	
	public Unit() {}
	
	public Long getUnitId() {
		return unitId;
	}
	
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	
	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getUnitCode() {
		return unitCode;
	}
	
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public List<Cohort> getUnitCohorts() {
		return unitCohorts;
	}

	public void setUnitCohorts(List<Cohort> unitCohorts) {
		this.unitCohorts = unitCohorts;
	}
	
	public void addUnitCohort(Cohort cohort) {
		cohort.setCohortUnit(this);
		this.unitCohorts.add(cohort);
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
