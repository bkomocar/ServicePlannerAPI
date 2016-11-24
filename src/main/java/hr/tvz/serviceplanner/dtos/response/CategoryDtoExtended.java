package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.ProductDto;

public class CategoryDtoExtended implements CategoryDto {

	private Long id;
	private String name;
	private String description;
	private String color;
	private List<ProductDto> products = new ArrayList<>();

	public CategoryDtoExtended() {
		super();
	}

	public CategoryDtoExtended(Long id, String name, List<ProductDto> products) {
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

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

}
