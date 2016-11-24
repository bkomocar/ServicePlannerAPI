package hr.tvz.serviceplanner.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.CategoryDtoExtended;
import hr.tvz.serviceplanner.dtos.response.CategoryDtoLarge;
import hr.tvz.serviceplanner.dtos.response.CategoryDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Product;

public class CategoryDtoFactory {
	public static CategoryDto toCategoryDto(Category category, DtoType type) {
		if (category != null) {
			if (type == null || type == DtoType.large || type == DtoType.medium) {
				List<Product> products = new ArrayList<>(category.getProducts());
				return new CategoryDtoLarge(category.getId(), category.getName(), category.getDescription(),
						category.getColor(), ProductDtoFactory.toProductDto(products, DtoType.large));
			} else if (type == DtoType.extended) {
				List<Product> products = new ArrayList<>(category.getProducts());
				return new CategoryDtoLarge(category.getId(), category.getName(), category.getDescription(),
						category.getColor(), ProductDtoFactory.toProductDto(products, DtoType.large));
			} else {
				List<Product> products = new ArrayList<>(category.getProducts());
				return new CategoryDtoLarge(category.getId(), category.getName(), category.getDescription(),
						category.getColor(), ProductDtoFactory.toProductDto(products, DtoType.large));
			}
		}
		return null;
	}

	public static List<CategoryDto> toCategoryDto(List<Category> categories, DtoType type) {
		if (categories != null) {
			return categories.stream().map(v -> CategoryDtoFactory.toCategoryDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
