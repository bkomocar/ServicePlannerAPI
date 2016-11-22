package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.request.CreateByIdDto;
import hr.tvz.serviceplanner.dtos.request.CreateCategoryDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCategoryDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Category;

public interface CategoryService extends Operations<Category> {

	public CategoryDto getCategory(Long id, DtoType type);

	public IdDto createCategory(Long id, CreateCategoryDto model);

	public boolean updateCategory(Long id, UpdateCategoryDto model);

	public boolean addEmployee(Long categoryId, CreateByIdDto model);

	public boolean removeEmployee(Long categoryId, Long employeeId);

	public List<EmployeeDto> getEmployees(Long categoryId, Long employeeId, DtoType type);
}
