package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;

public class CategoryViewModelExtended implements CategoryViewModel {

	private Long id;
	private String name;
	private List<ProductViewModel> products = new ArrayList<>();

	public CategoryViewModelExtended() {
		super();
	}

	public CategoryViewModelExtended(Long id, String name, List<ProductViewModel> products) {
		super();
		this.id = id;
		this.name = name;
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

	public List<ProductViewModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductViewModel> products) {
		this.products = products;
	}

}
