package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.ProductDao;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.ProductService;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateProductViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public ProductViewModel getProduct(Long id, ViewModelType type) {
		Product product = dao.findOne(id);
		if (product != null) {
			return ProductViewModelFactory.toProductViewModel(product, type);
		}
		return null;
	}

	@Override
	public IdViewModel createProduct(Long id, CreateProductViewModel model) {
		Long productId = dao.createProduct(id, CreateProductViewModel.toProduct(model));
		if (productId != null) {
			return new IdViewModel(productId);
		}
		return null;
	}

	@Override
	public boolean updateProduct(Long id, UpdateProductViewModel model) {
		if (model != null) {
			return dao.updateProduct(id, UpdateProductViewModel.toProduct(model));
		}
		return false;
	}

	@Override
	protected Operations<Product> getDao() {
		return dao;
	}

}
