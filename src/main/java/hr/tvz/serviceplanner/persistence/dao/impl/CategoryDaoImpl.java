package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CategoryDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Group;

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

}
