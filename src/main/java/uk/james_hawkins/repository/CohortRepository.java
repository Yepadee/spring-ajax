package uk.james_hawkins.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.Unit;

public interface CohortRepository extends CrudRepository<Cohort, Long> {
	@Query("SELECT COUNT(c) > 0 FROM Cohort c WHERE c.cohortUnit = :unit AND c.cohortYear = :cohortYear")
    public boolean exists(Unit unit, Integer cohortYear);
}
