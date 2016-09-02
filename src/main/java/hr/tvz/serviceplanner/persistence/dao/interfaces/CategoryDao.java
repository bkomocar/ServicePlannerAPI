package hr.tvz.serviceplanner.persistence.dao.interfaces;

import java.util.SortedSet;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Employee;

public interface CategoryDao extends Operations<Category> {

	public boolean updateCategory(Long categoryId, Category category);

	public Long createCategory(Long groupId, Category category);

	public boolean addEmployee(Long categoryId, Long employeeId);

	public boolean removeEmployee(Long categoryId, Long employeeId);

	public SortedSet<Employee> getEmployees(Long venueId, Long categoryId);
}
