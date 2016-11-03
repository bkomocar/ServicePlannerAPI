package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Product;

public interface ProductDao extends Operations<Product> {

	public boolean updateProduct(Long id, Product product);

	public Long createProduct(Long id, Product product);
	
	public boolean deleteProduct(Long productId);
}
