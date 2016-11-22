package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.ProductDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateProductDto;
import hr.tvz.serviceplanner.dtos.request.UpdateProductDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.ProductDao;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.ProductService;

@Service
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public ProductDto getProduct(Long id, DtoType type) {
		Product product = dao.findOne(id);
		if (product != null) {
			return ProductDtoFactory.toProductDto(product, type);
		}
		return null;
	}

	@Override
	public IdDto createProduct(Long id, CreateProductDto model) {
		Long productId = dao.createProduct(id, CreateProductDto.toProduct(model));
		if (productId != null) {
			return new IdDto(productId);
		}
		return null;
	}

	@Override
	public boolean updateProduct(Long id, UpdateProductDto model) {
		if (model != null) {
			return dao.updateProduct(id, UpdateProductDto.toProduct(model));
		}
		return false;
	}

	@Override
	protected Operations<Product> getDao() {
		return dao;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		return dao.deleteProduct(productId);
	}

}
