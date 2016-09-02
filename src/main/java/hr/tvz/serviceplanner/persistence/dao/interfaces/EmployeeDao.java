package hr.tvz.serviceplanner.persistence.dao.interfaces;

import java.util.SortedSet;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Employee;

public interface EmployeeDao extends Operations<Employee> {

	public boolean updateEmployee(Long id, Employee employee);

	public Long createEmployee(Long id, Employee employee);

	public SortedSet<Employee> getEmployeesForVenue(Long venueId);
}
