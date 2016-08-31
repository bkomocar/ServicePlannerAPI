package hr.tvz.serviceplanner.viewmodels.request;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Product;

public class UpdateProductViewModel {

	private Integer maxCustomers;

	@Length(max = 255)
	private String name;

	@Length(max = 10)
	private String shortName;

	@Length(max = 500)
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
			if(model.maxCustomers != null && model.maxCustomers < 1){
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
