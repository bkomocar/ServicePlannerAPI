package hr.tvz.serviceplanner.dtos;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.EmployeeDtoLarge;
import hr.tvz.serviceplanner.dtos.response.EmployeeDtoMedium;
import hr.tvz.serviceplanner.dtos.response.EmployeeDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Employee;

public class EmployeeDtoFactory {

	public static EmployeeDto toEmployeeDto(Employee employee, DtoType type) {
		if (employee != null) {
			if (type == null || type == DtoType.large || type == DtoType.extended) {
				return new EmployeeDtoLarge(employee.getId(), employee.getUsername(), employee.getFirstName(),
						employee.getLastName(), employee.getColor(), employee.getEmail(), employee.getPhone(),
						employee.getComment());
			} else if (type == DtoType.medium) {
				return new EmployeeDtoMedium(employee.getId(), employee.getUsername(), employee.getColor());
			} else {
				return new EmployeeDtoSmall(employee.getId(), employee.getUsername());
			}
		}
		return null;
	}

	public static List<EmployeeDto> toEmployeeDto(List<Employee> employees, DtoType type) {
		if (employees != null) {
			return employees.stream().map(v -> EmployeeDtoFactory.toEmployeeDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
