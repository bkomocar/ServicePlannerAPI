package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;

public class CategoryViewModelLarge extends CategoryViewModel {

	private Long id;
	private String name;
	private String description;
	private String color;

	public CategoryViewModelLarge() {
		super();
	}

	public CategoryViewModelLarge(Long id, String name, String description, String color) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
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

}
