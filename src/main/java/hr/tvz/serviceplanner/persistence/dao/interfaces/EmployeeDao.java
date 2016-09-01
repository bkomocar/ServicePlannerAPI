package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Employee;

public interface EmployeeDao extends Operations<Employee> {

	public boolean updateEmployee(Long id, Employee employee);
	
	public Long createEmployee(Long id, Employee employee);
}
