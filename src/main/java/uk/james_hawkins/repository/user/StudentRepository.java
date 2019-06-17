package uk.james_hawkins.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import uk.james_hawkins.model.user.Student;

public interface StudentRepository extends BaseUserRepository<Student> {
	@Query("SELECT u FROM User u WHERE perms = 'student'")
    public List<Student> getAllStudents();
}
