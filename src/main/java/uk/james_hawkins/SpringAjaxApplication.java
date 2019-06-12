package uk.james_hawkins;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import uk.james_hawkins.model.Cohort;
import uk.james_hawkins.model.Unit;
import uk.james_hawkins.service.CohortService;
import uk.james_hawkins.service.UnitService;

@SpringBootApplication
public class SpringAjaxApplication {
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private CohortService cohortService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAjaxApplication.class, args);
	}
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		Unit unit = new Unit();
		unit.setUnitCode("COMS20805");
		unit.setUnitName("Software Product Engineering");
		
		Unit unit1 = new Unit();
		unit1.setUnitCode("COMS22201");
		unit1.setUnitName("Language Engineering");
		
		unitService.addUnit(unit);
		unitService.addUnit(unit1);
		
		Cohort cohort = new Cohort();
		cohort.setCohortYear(2019);
		
		Cohort cohort1 = new Cohort();
		cohort1.setCohortYear(2019);
		
		unit.addUnitCohort(cohort);
		unit1.addUnitCohort(cohort1);
		
		cohortService.addCohort(cohort);
		cohortService.addCohort(cohort1);
		
		
		System.out.println(unitService.getAllUnits());
		
	}

}
