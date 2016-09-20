package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Product;

public class UpdateProductViewModel {

	@Range(min = 1, message = "Maximum customers can not be less than {min}")
	private Integer maxCustomers;

	@Length(min = 1, max = 50, message = "Name length should be between {min} and {max} characters")
	private String name;

	@Length(min = 1, max = 10, message = "Name length should be between {min} and {max} characters")
	private String shortName;

	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;

	public UpdateProductViewModel() {
		super();
	}

	public UpdateProductViewModel(Integer maxCustomers, String name, String shortName, String description) {
		super();
		this.name = name;
		this.maxCustomers = maxCustomers;
		this.shortName = shortName;
		this.description = description;
	}

	public static Product toProduct(UpdateProductViewModel model) {
		if (model != null) {
			return new Product(model.maxCustomers, model.name, model.shortName, model.description);
		}
		return null;
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
