package uk.james_hawkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.repository.CohortRepository;

@Service
public class CohortService {
	
	@Autowired
	private CohortRepository cohortRepository;
	
	public List<Cohort> getAllCohorts() {
		List<Cohort> units = new ArrayList<>();
		cohortRepository.findAll().forEach(units::add);
		return units;
	}
		
	public Long addCohort(Cohort unit) {
		return cohortRepository.save(unit).getCohortId();
	}

}
