package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.CategoryDtoFactory;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.EmployeeDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateByIdDto;
import hr.tvz.serviceplanner.dtos.request.CreateCategoryDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCategoryDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CategoryDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CategoryService;

@Service
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {

	@Autowired
	private CategoryDao dao;

	@Override
	public CategoryDto getCategory(Long id, DtoType type) {
		Category category = dao.findOne(id);
		if (category != null) {
			return CategoryDtoFactory.toCategoryDto(category, type);
		}
		return null;
	}

	@Override
	public IdDto createCategory(Long id, CreateCategoryDto model) {
		Long categoryId = dao.createCategory(id, CreateCategoryDto.toCategory(model));
		if (categoryId != null) {
			return new IdDto(categoryId);
		}
		return null;
	}

	@Override
	public boolean updateCategory(Long id, UpdateCategoryDto model) {
		if (model != null) {
			return dao.updateCategory(id, UpdateCategoryDto.toCategory(model));
		}
		return false;
	}

	@Override
	protected Operations<Category> getDao() {
		return dao;
	}

	@Override
	public boolean addEmployee(Long categoryId, CreateByIdDto model) {
		if (categoryId != null && model != null && model.getId() != null) {
			return dao.addEmployee(categoryId, model.getId());
		}
		return false;
	}

	@Override
	public boolean removeEmployee(Long categoryId, Long employeeId) {
		if (categoryId != null && employeeId != null) {
			return dao.removeEmployee(categoryId, employeeId);
		}
		return false;
	}

	@Override
	public List<EmployeeDto> getEmployees(Long venueId, Long categoryId, DtoType type) {
		SortedSet<Employee> employees = dao.getEmployees(venueId, categoryId);
		if (employees != null) {
			return EmployeeDtoFactory.toEmployeeDto(new ArrayList<Employee>(employees), type);
		}
		return null;
	}

}
