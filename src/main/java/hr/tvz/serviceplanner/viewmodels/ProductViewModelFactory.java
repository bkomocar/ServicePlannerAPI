package hr.tvz.serviceplanner.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.viewmodels.response.PriceViewModel;
import hr.tvz.serviceplanner.viewmodels.response.ProductViewModelExtended;
import hr.tvz.serviceplanner.viewmodels.response.ProductViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.ProductViewModelSmall;

public class ProductViewModelFactory {

	public static ProductViewModel toProductViewModel(Product product, ViewModelType type) {
		if (product != null && product.getCategory() != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium) {
				List<Price> prices = new ArrayList<>(product.getPrices());
				return new ProductViewModelLarge(product.getId(), product.getMaxCustomers(), product.getName(),
						product.getShortName(), product.getDescription(), PriceViewModel.fromPrice(prices), product.getCategory().getId());
			} else if (type == ViewModelType.extended) {
				List<Price> prices = new ArrayList<>(product.getPrices());
				return new ProductViewModelExtended(product.getId(), product.getName(),
						PriceViewModel.fromPrice(prices), product.getCategory().getId());
			} else {
				return new ProductViewModelSmall(product.getId(), product.getMaxCustomers(), product.getName(),
						product.getShortName(), product.getDescription(), product.getCategory().getId());
			}
		}
		return null;
	}

	public static List<ProductViewModel> toProductViewModel(List<Product> products, ViewModelType type) {
		if (products != null) {
			return products.stream().map(v -> ProductViewModelFactory.toProductViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}

}
