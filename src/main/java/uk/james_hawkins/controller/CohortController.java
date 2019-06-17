package uk.james_hawkins.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.Unit;
import uk.james_hawkins.model.user.Staff;
import uk.james_hawkins.model.user.Student;
import uk.james_hawkins.model.user.User;
import uk.james_hawkins.service.CohortService;
import uk.james_hawkins.service.UnitService;
import uk.james_hawkins.service.user.StaffService;
import uk.james_hawkins.service.user.StudentService;
import uk.james_hawkins.service.user.UserService;

@Controller
@RequestMapping("cohorts")
public class CohortController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CohortService cohortService;
	
	@Autowired
	private UnitService unitService;
	
	
	private String newCohortPage = "new_cohort";
	private String newCohortFormFragment = newCohortPage + " :: form";
	
	@GetMapping("add")
	public String newCohort(Model model) {
		Cohort cohort = new Cohort();
		model.addAttribute("cohort", cohort);
		return newCohortPage;
	}
	
	@GetMapping("edit/{cohortId}")
	public String editCohort(@PathVariable Long cohortId, Model model) {
		model.addAttribute("cohort", cohortService.getCohort(cohortId));
		return newCohortPage;
	}
	
	@PostMapping
	public String saveCohort(@Valid @ModelAttribute Cohort cohort, BindingResult result, Model model) {
		if (cohortService.exists(cohort) && cohort.getCohortId() == null) {
			result.rejectValue("cohortYear", "error.cohortYear", "Cohort already exists for this year");
		}
		
		//Can return different fragment if successful
		if (result.hasErrors()) {
			return newCohortFormFragment;
	    } else {
	    	cohortService.addCohort(cohort);
	    	return newCohortFormFragment;
	    }
		
	}
	
	@ModelAttribute("unitList")
	public List<Unit> getUnitList() {
		return unitService.getAllUnits();
	}
	
	@ModelAttribute("studentList")
	public List<Student> getStudentList() {
		return studentService.getAllStudents();
	}
	
	@ModelAttribute("staffList")
	public List<Staff> getStaffList() {
		return staffService.getAllStaff();
	}

}
