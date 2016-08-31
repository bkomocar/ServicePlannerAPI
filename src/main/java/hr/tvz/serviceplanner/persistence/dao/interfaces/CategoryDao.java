package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Category;

public interface CategoryDao extends Operations<Category> {

	public boolean updateCategory(Long id, Category category);

	public Long createCategory(Long id, Category category);
}
