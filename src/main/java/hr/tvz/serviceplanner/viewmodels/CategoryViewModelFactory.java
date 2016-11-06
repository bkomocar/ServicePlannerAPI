package hr.tvz.serviceplanner.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModelExtended;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.CategoryViewModelSmall;

public class CategoryViewModelFactory {
	public static CategoryViewModel toCategoryViewModel(Category category, ViewModelType type) {
		if (category != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium) {
				List<Product> products = new ArrayList<>(category.getProducts());
				return new CategoryViewModelLarge(category.getId(), category.getName(), category.getDescription(),
						category.getColor(), ProductViewModelFactory.toProductViewModel(products, ViewModelType.large));
			} else if (type == ViewModelType.extended) {
				List<Product> products = new ArrayList<>(category.getProducts());
				return new CategoryViewModelExtended(category.getId(), category.getName(),
						ProductViewModelFactory.toProductViewModel(products, ViewModelType.large));
			} else {
				return new CategoryViewModelSmall(category.getId(), category.getName());
			}
		}
		return null;
	}

	public static List<CategoryViewModel> toCategoryViewModel(List<Category> categories, ViewModelType type) {
		if (categories != null) {
			return categories.stream().map(v -> CategoryViewModelFactory.toCategoryViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}
}
