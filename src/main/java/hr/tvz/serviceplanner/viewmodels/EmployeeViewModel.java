package hr.tvz.serviceplanner.viewmodels;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.viewmodels.response.EmployeeViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.EmployeeViewModelMedium;
import hr.tvz.serviceplanner.viewmodels.response.EmployeeViewModelSmall;

public class EmployeeViewModel {

	public static EmployeeViewModel toEmployeeViewModel(Employee employee, ViewModelType type) {
		if (employee != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.extended) {
				return new EmployeeViewModelLarge(employee.getId(), employee.getUsername(), employee.getFirstName(),
						employee.getLastName(), employee.getColor(), employee.getEmail(), employee.getPhone(),
						employee.getComment());
			} else if (type == ViewModelType.medium) {
				return new EmployeeViewModelMedium(employee.getId(), employee.getUsername(), employee.getColor());
			} else {
				return new EmployeeViewModelSmall(employee.getId(), employee.getUsername());
			}
		}
		return null;
	}

	public static List<EmployeeViewModel> toEmployeeViewModel(List<Employee> employees, ViewModelType type) {
		if (employees != null) {
			return employees.stream().map(v -> EmployeeViewModel.toEmployeeViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}
}
