package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.request.CreateProductDto;
import hr.tvz.serviceplanner.dtos.request.UpdateProductDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Product;

public interface ProductService extends Operations<Product> {

	public ProductDto getProduct(Long id, DtoType type);

	public IdDto createProduct(Long id, CreateProductDto model);

	public boolean updateProduct(Long id, UpdateProductDto model);

	public boolean deleteProduct(Long productId);
}
