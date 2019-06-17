package uk.james_hawkins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.Unit;
import uk.james_hawkins.model.user.Staff;
import uk.james_hawkins.model.user.Student;
import uk.james_hawkins.service.CohortService;
import uk.james_hawkins.service.UnitService;
import uk.james_hawkins.service.user.StaffService;
import uk.james_hawkins.service.user.UserService;

@SpringBootApplication
public class SpringAjaxApplication {
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private CohortService cohortService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StaffService staffService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAjaxApplication.class, args);
	}
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		//User
		Staff unitDirector = new Staff();
		unitDirector.setUserName("Aydin");
		userService.addUser(unitDirector);
		
		int n = 5;
		for (int i = 1; i <= n; i ++) {
			Student student = new Student();
			student.setUserName("Student " + Integer.toString(i));
			userService.addUser(student);
		}
		
		int m = 5;
		for (int i = 1; i <= m; i ++) {
			Staff staff = new Staff();
			staff.setUserName("Staff " + Integer.toString(i));
			userService.addUser(staff);
		}
		//---
		
		//Units
		Unit unit = new Unit();
		unit.setUnitCode("COMS20805");
		unit.setUnitName("Software Product Engineering");
		
		Unit unit1 = new Unit();
		unit1.setUnitCode("COMS22201");
		unit1.setUnitName("Language Engineering");
		
		unitService.addUnit(unit);
		unitService.addUnit(unit1);
		//---
		
		
		//Cohorts
		Cohort cohort = new Cohort();
		cohort.setCohortYear(2019);
		cohort.setCohortUnitDirector(unitDirector);
		cohort.setCohortUnit(unit);
		cohort.getCohortStaff().addAll(staffService.getAllStaff());
		
		System.out.println(staffService.getAllUsers().size());
			
		Cohort cohort1 = new Cohort();
		cohort1.setCohortYear(2019);
		cohort1.setCohortUnitDirector(unitDirector);
		cohort1.setCohortUnit(unit1);
		cohort1.addCohortStaff(unitDirector);

		cohortService.addCohort(cohort);
		cohortService.addCohort(cohort1);
		///---
		
		
		System.out.println(unitService.getAllUnits());
		
	}

}
