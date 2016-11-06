package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;

public class CategoryViewModelLarge implements CategoryViewModel {

	private Long id;
	private String name;
	private String description;
	private String color;
	private List<ProductViewModel> products = new ArrayList<>();

	public CategoryViewModelLarge() {
		super();
	}

	public CategoryViewModelLarge(Long id, String name, String description, String color,
			List<ProductViewModel> products) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<ProductViewModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductViewModel> products) {
		this.products = products;
	}
}
