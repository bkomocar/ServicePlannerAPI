package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.viewmodels.request.CreateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.ProductViewModel;

public interface ProductService extends Operations<Product> {

	public ProductViewModel getProduct(Long id);

	public IdViewModel createProduct(Long id, CreateProductViewModel model);

	public boolean updateProduct(Long id, UpdateProductViewModel model);
}
