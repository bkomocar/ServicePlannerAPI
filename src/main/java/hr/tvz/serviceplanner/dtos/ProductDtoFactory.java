package hr.tvz.serviceplanner.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.PriceDto;
import hr.tvz.serviceplanner.dtos.response.ProductDtoExtended;
import hr.tvz.serviceplanner.dtos.response.ProductDtoLarge;
import hr.tvz.serviceplanner.dtos.response.ProductDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;

public class ProductDtoFactory {

	public static ProductDto toProductDto(Product product, DtoType type) {
		if (product != null && product.getCategory() != null) {
			if (type == null || type == DtoType.large || type == DtoType.medium) {
				List<Price> prices = new ArrayList<>(product.getPrices());
				return new ProductDtoLarge(product.getId(), product.getMaxCustomers(), product.getName(),
						product.getShortName(), product.getDescription(), PriceDto.fromPrice(prices),
						product.getCategory().getId());
			} else if (type == DtoType.extended) {
				List<Price> prices = new ArrayList<>(product.getPrices());
				return new ProductDtoExtended(product.getId(), product.getName(), PriceDto.fromPrice(prices),
						product.getCategory().getId());
			} else {
				return new ProductDtoSmall(product.getId(), product.getMaxCustomers(), product.getName(),
						product.getShortName(), product.getDescription(), product.getCategory().getId());
			}
		}
		return null;
	}

	public static List<ProductDto> toProductDto(List<Product> products, DtoType type) {
		if (products != null) {
			return products.stream().map(v -> ProductDtoFactory.toProductDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}

}
