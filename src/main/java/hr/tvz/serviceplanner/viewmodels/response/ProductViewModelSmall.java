package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Product;

public class ProductViewModelSmall {

	private Long id;
	private Integer maxCustomers;
	private String name;
	private String shortName;
	private String description;

	public ProductViewModelSmall() {
		super();
	}

	public ProductViewModelSmall(Long id, Integer maxCustomers, String name, String shortName, String description) {
		super();
		this.id = id;
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
	}

	public static ProductViewModelSmall fromProduct(Product product) {
		if (product != null) {
			return new ProductViewModelSmall(product.getId(), product.getMaxCustomers(), product.getName(),
					product.getShortName(), product.getDescription());
		}
		return null;
	}

	public static List<ProductViewModelSmall> fromProduct(List<Product> products) {
		if (products != null) {
			return products.stream().map(u -> ProductViewModelSmall.fromProduct(u)).collect(Collectors.toList());
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxCustomers() {
		return maxCustomers;
	}

	public void setMaxCustomers(Integer maxCustomers) {
		this.maxCustomers = maxCustomers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
