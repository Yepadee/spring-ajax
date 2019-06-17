package uk.james_hawkins.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.model.user.Student;
import uk.james_hawkins.repository.user.StudentRepository;

@Service
public class StudentService extends BaseUserService<Student> {
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}
} 