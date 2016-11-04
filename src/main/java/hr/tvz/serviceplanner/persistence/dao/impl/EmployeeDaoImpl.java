package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EmployeeDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
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
	public Employee getEmployee(Long employeeId) {
		Employee e = findOne(employeeId);
		if(e!=null && !e.isDeleted()){
			return e;
		}
		return null;
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
	public boolean deleteEmployee(Long venueId, Long employeeId) {
		Employee employee = findOne(employeeId);
		if (employee.getVenue().getId() == venueId) {
			employee.setDeleted(true);
			update(employee);
			return true;
		}
		return false;
	}

	@Override
	public SortedSet<Employee> getEmployeesForVenue(Long venueId) {
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		if (venue != null) {
			Criteria crit = getCurrentSession().createCriteria(Employee.class);
			crit.add(Restrictions.eq("deleted", false));
			crit.add(Restrictions.eq("venue", venue));
			List<Employee> results = crit.list();
			if (results != null) {
				SortedSet<Employee> list = new TreeSet<Employee>(results);
				return list;
			}
		}
		return null;
	}
}
