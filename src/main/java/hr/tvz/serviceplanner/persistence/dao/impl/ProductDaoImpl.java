package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.ProductDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Product;

@Repository
public class ProductDaoImpl extends AbstractHibernateDao<Product> implements ProductDao {

	public ProductDaoImpl() {
		super();
		setClazz(Product.class);
	}

	@Override
	public boolean updateProduct(Long id, Product product) {
		Product originalProduct = findOne(id);
		if (originalProduct != null) {
			if (product.getName() != null) {
				originalProduct.setName(product.getName());
			}
			if (product.getMaxCustomers() != null) {
				originalProduct.setMaxCustomers(product.getMaxCustomers());
			}
			if (product.getDescription() != null) {
				originalProduct.setDescription(product.getDescription());
			}
			if (product.getShortName() != null) {
				originalProduct.setShortName(product.getShortName());
			}
			update(originalProduct);
			return true;
		}
		return false;
	}

	@Override
	public Long createProduct(Long id, Product product) {
		Category category = getCurrentSession().get(Category.class, id);
		if (category != null) {
			product.setCategory(category);
			create(product);
			return product.getId();
		}
		return null;
	}
}
