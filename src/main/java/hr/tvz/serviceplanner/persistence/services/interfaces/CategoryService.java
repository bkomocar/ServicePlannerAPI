package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.viewmodels.request.CreateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateByIdViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface CategoryService extends Operations<Category> {

	public CategoryViewModel getCategory(Long id);

	public IdViewModel createCategory(Long id, CreateCategoryViewModel model);

	public boolean updateCategory(Long id, UpdateCategoryViewModel model);
	
	public boolean addEmployee(Long categoryId, CreateByIdViewModel model);
	
	public boolean removeEmployee(Long categoryId, Long employeeId);
}
