package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.ProductDto;

public class CategoryDtoLarge implements CategoryDto {

	private Long id;
	private String name;
	private String description;
	private String color;
	private List<ProductDto> products = new ArrayList<>();

	public CategoryDtoLarge() {
		super();
	}

	public CategoryDtoLarge(Long id, String name, String description, String color, List<ProductDto> products) {
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

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
}
