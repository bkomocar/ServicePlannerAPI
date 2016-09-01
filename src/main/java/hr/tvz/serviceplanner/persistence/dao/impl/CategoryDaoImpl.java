package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.SortedSet;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public boolean updateCategory(Long id, Category category) {
		Category originalCategory = findOne(id);
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
	public Long createCategory(Long id, Category category) {
		Group group = getCurrentSession().get(Group.class, id);
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
}
