package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Product;

public class CreateProductViewModel {

	private Integer maxCustomers;

	@NotNull
	@Length(max = 255)
	private String name;

	@NotNull
	@Length(max = 10)
	private String shortName;

	@Length(max = 500)
	private String description;

	public CreateProductViewModel() {
		super();
	}

	public CreateProductViewModel(Integer maxCustomers, String name, String shortName, String description) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
	}

	public static Product toProduct(CreateProductViewModel model) {
		if (model != null) {
			if(model.maxCustomers == null || model.maxCustomers < 1){
				model.maxCustomers = 1;
			} 
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
