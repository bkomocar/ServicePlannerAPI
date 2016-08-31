package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Category;

public class UpdateCategoryViewModel {

	@Length(max = 255)
	private String name;

	@Length(max = 500)
	private String description;

	@Length(max = 20)
	private String color;

	public UpdateCategoryViewModel() {
		super();
	}

	public UpdateCategoryViewModel(String name, String description, String color) {
		super();
		this.name = name;
		this.description = description;
		this.color = color;
	}

	public static Category toCategory(UpdateCategoryViewModel model) {
		if (model != null) {
			return new Category(model.name, model.description, model.color);
		}
		return null;
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
