package uk.james_hawkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.james_hawkins.model.Unit;
import uk.james_hawkins.repository.UnitRepository;

@Service
public class UnitService {

	@Autowired
	private UnitRepository unitRepository;
	
	public List<Unit> getAllUnits() {
		List<Unit> units = new ArrayList<>();
		unitRepository.findAll().forEach(units::add);
		return units;
	}
		
	public Long addUnit(Unit unit) {
		return unitRepository.save(unit).getUnitId();
	}
	
}
