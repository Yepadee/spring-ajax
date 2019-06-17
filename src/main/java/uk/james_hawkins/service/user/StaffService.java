package uk.james_hawkins.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.model.user.Staff;
import uk.james_hawkins.repository.user.StaffRepository;


@Service
public class StaffService extends BaseUserService<Staff> {
	@Autowired
	private StaffRepository staffRepository;
	
	public List<Staff> getAllStaff() {
		return staffRepository.getAllStaff();
	}
} 