package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CategoryDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CategoryService;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {

	@Autowired
	private CategoryDao dao;

	@Override
	public CategoryViewModel getCategory(Long id) {
		Category category = dao.findOne(id);
		if (category != null) {
			return CategoryViewModel.fromCategory(category);
		}
		return null;
	}

	@Override
	public IdViewModel createCategory(Long id, Category category) {
		Long categoryId = dao.createCategory(id, category);
		if (categoryId != null) {
			return new IdViewModel(categoryId);
		}
		return null;
	}

	@Override
	public boolean updateCategory(Long id, UpdateCategoryViewModel model) {
		if (model != null) {
			return dao.updateCategory(id, UpdateCategoryViewModel.toCategory(model));
		}
		return false;
	}

	@Override
	protected Operations<Category> getDao() {
		return dao;
	}

}
