package uk.james_hawkins.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import uk.james_hawkins.model.user.Staff;

public interface StaffRepository extends BaseUserRepository<Staff> {
	@Query("SELECT u FROM User u WHERE perms = 'staff'")
    public List<Staff> getAllStaff();
}
