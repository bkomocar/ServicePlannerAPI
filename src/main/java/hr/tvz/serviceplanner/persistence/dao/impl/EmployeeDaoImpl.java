package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.SortedSet;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EmployeeDao;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class EmployeeDaoImpl extends AbstractHibernateDao<Employee> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super();
		setClazz(Employee.class);
	}

	@Override
	public boolean updateEmployee(Long id, Employee employee) {
		Employee originalEmployee = findOne(id);
		if (originalEmployee != null) {
			if (employee.getUsername() != null) {
				originalEmployee.setUsername(employee.getUsername());
			}
			if (employee.getFirstName() != null) {
				originalEmployee.setFirstName(employee.getFirstName());
			}
			if (employee.getLastName() != null) {
				originalEmployee.setLastName(employee.getLastName());
			}
			if (employee.getColor() != null) {
				originalEmployee.setColor(employee.getColor());
			}
			if (employee.getEmail() != null) {
				originalEmployee.setEmail(employee.getEmail());
			}
			if (employee.getPhone() != null) {
				originalEmployee.setPhone(employee.getPhone());
			}
			if (employee.getComment() != null) {
				originalEmployee.setComment(employee.getComment());
			}
			update(originalEmployee);
			return true;
		}
		return false;
	}

	@Override
	public Long createEmployee(Long id, Employee employee) {
		Venue venue = getCurrentSession().get(Venue.class, id);
		if (venue != null) {
			employee.setVenue(venue);
			create(employee);
			return employee.getId();
		}
		return null;
	}

	@Override
	public SortedSet<Employee> getEmployeesForVenue(Long venueId) {
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		return venue.getEmployees();
	}
}
