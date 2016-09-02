package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface EmployeeService extends Operations<Employee> {

	public EmployeeViewModel getEmployee(Long id, ViewModelType type);

	public IdViewModel createEmployee(Long id, CreateEmployeeViewModel model);

	public boolean updateEmployee(Long id, UpdateEmployeeViewModel model);

	public List<EmployeeViewModel> getEmployeesForVenue(Long venueId, ViewModelType type);
}
