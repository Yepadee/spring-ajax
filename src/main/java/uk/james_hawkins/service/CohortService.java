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
		List<Cohort> cohorts = new ArrayList<>();
		cohortRepository.findAll().forEach(cohorts::add);
		return cohorts;
	}
		
	public void addCohort(Cohort cohort) {
		cohortRepository.save(cohort);
	}
	
	public Boolean exists(Cohort cohort) {
		return cohortRepository.exists(cohort.getCohortUnit(), cohort.getCohortYear());
	}

}
