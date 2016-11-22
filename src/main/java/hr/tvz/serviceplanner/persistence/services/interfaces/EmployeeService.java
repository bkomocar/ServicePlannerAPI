package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.request.CreateEmployeeDto;
import hr.tvz.serviceplanner.dtos.request.UpdateEmployeeDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Employee;

public interface EmployeeService extends Operations<Employee> {

	public EmployeeDto getEmployee(Long id, DtoType type);

	public IdDto createEmployee(Long id, CreateEmployeeDto model);

	public boolean updateEmployee(Long id, UpdateEmployeeDto model);

	public List<EmployeeDto> getEmployeesForVenue(Long venueId, DtoType type);

	public boolean deleteEmployee(Long venueId, Long employeeId);

}
