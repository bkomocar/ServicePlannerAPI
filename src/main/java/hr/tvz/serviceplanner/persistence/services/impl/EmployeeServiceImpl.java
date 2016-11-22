package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.EmployeeDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateEmployeeDto;
import hr.tvz.serviceplanner.dtos.request.UpdateEmployeeDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EmployeeDao;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.EmployeeService;

@Service
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Override
	public EmployeeDto getEmployee(Long id, DtoType type) {
		Employee employee = dao.getEmployee(id);
		if (employee != null) {
			return EmployeeDtoFactory.toEmployeeDto(employee, type);
		}
		return null;
	}

	@Override
	public IdDto createEmployee(Long id, CreateEmployeeDto model) {
		Long groupId = dao.createEmployee(id, CreateEmployeeDto.toEmployee(model));
		if (groupId != null) {
			return new IdDto(groupId);
		}
		return null;
	}

	@Override
	public boolean updateEmployee(Long id, UpdateEmployeeDto model) {
		if (model != null) {
			return dao.updateEmployee(id, UpdateEmployeeDto.toEmployee(model));
		}
		return false;
	}

	@Override
	protected Operations<Employee> getDao() {
		return dao;
	}

	@Override
	public List<EmployeeDto> getEmployeesForVenue(Long venueId, DtoType type) {
		SortedSet<Employee> employees = dao.getEmployeesForVenue(venueId);
		if (employees != null) {
			return EmployeeDtoFactory.toEmployeeDto(new ArrayList<Employee>(employees), type);
		}
		return null;
	}

	@Override
	public boolean deleteEmployee(Long venueId, Long employeeId) {
		return dao.deleteEmployee(venueId, employeeId);
	}

}
