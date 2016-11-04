package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CategoryDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class CategoryDaoImpl extends AbstractHibernateDao<Category> implements CategoryDao {

	public CategoryDaoImpl() {
		super();
		setClazz(Category.class);
	}

	@Override
	public boolean updateCategory(Long categoryId, Category category) {
		Category originalCategory = findOne(categoryId);
		if (originalCategory != null) {
			if (category.getName() != null) {
				originalCategory.setName(category.getName());
			}
			if (category.getDescription() != null) {
				originalCategory.setDescription(category.getDescription());
			}
			if (category.getColor() != null) {
				originalCategory.setColor(category.getColor());
			}
			update(originalCategory);
			return true;
		}
		return false;
	}

	@Override
	public Long createCategory(Long groupId, Category category) {
		Group group = getCurrentSession().get(Group.class, groupId);
		if (group != null) {
			category.setGroup(group);
			create(category);
			return category.getId();
		}
		return null;
	}

	@Override
	public boolean addEmployee(Long categoryId, Long employeeId) {
		Preconditions.checkNotNull(categoryId);
		Preconditions.checkNotNull(employeeId);
		Category category = findOne(categoryId);
		Employee employee = getCurrentSession().get(Employee.class, employeeId);
		if (category != null && employee != null) {
			Venue categoryVenue = category.getGroup().getVenue();
			Venue employeeVenue = employee.getVenue();
			if (categoryVenue.equals(employeeVenue)) {
				SortedSet<Employee> employees = category.getEmployees();
				employees.add(employee);
				category.setEmployees(employees);
				update(category);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeEmployee(Long categoryId, Long employeeId) {
		Preconditions.checkNotNull(categoryId);
		Preconditions.checkNotNull(employeeId);
		Category category = findOne(categoryId);
		Employee employee = getCurrentSession().get(Employee.class, employeeId);
		SortedSet<Employee> employees = category.getEmployees();
		if (employees != null && !employees.isEmpty() && employees.contains(employee)) {
			employees.remove(employee);
			category.setEmployees(employees);
			update(category);
			return true;
		}
		return false;
	}

	@Override
	public SortedSet<Employee> getEmployees(Long venueId, Long categoryId) {
		Category category = findOne(categoryId);
		if (category != null && category.getGroup() != null && category.getGroup().getVenue() != null
				&& category.getGroup().getVenue().getId() != null
				&& category.getGroup().getVenue().getId() == venueId) {
			List<Employee> employees = category.getEmployees().stream() 	
					.filter(employee -> !employee.isDeleted())	
					.collect(Collectors.toList());
			return new TreeSet<Employee>(employees);
		}
		return null;
	}
}
