package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.viewmodels.request.CreateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface CategoryService {

	public CategoryViewModel getCategory(Long id);

	public IdViewModel createCategory(Long id, CreateCategoryViewModel model);

	public boolean updateCategory(Long id, UpdateCategoryViewModel model);
}
