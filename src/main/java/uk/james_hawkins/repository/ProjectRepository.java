package uk.james_hawkins.repository;

import org.springframework.data.repository.CrudRepository;

import uk.james_hawkins.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {}