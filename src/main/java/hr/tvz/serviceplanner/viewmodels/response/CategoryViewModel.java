package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Category;

public class CategoryViewModel {

	private Long id;
	private String name;
	private String description;
	private String color;

	public CategoryViewModel() {
		super();
	}

	public CategoryViewModel(Long id, String name, String description, String color) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
	}

	public static CategoryViewModel fromCategory(Category category) {
		if (category != null) {
			return new CategoryViewModel(category.getId(), category.getName(), category.getDescription(),
					category.getColor());
		}
		return null;
	}

	public static List<CategoryViewModel> fromCategory(List<Category> categories) {
		if (categories != null) {
			return categories.stream().map(u -> CategoryViewModel.fromCategory(u)).collect(Collectors.toList());
		}
		return null;
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
