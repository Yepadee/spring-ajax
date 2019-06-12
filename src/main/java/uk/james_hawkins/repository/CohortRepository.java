package uk.james_hawkins.repository;

import org.springframework.data.repository.CrudRepository;

import uk.james_hawkins.model.Cohort;

public interface CohortRepository extends CrudRepository<Cohort, Long> {}
