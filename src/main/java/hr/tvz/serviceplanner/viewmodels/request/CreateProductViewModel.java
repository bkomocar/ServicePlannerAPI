package hr.tvz.serviceplanner.viewmodels.request;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;

public class CreateProductViewModel {

	private Integer maxCustomers;

	@NotNull
	@Length(max = 255)
	private String name;

	@NotNull
	@Length(max = 10)
	private String shortName;

	@NotEmpty
	public List<CreatePriceViewModel> prices = new ArrayList<>();

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

	public CreateProductViewModel(Integer maxCustomers, String name, String shortName,
			List<CreatePriceViewModel> prices, String description) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.prices = prices;
		this.description = description;
	}

	public static Product toProduct(CreateProductViewModel model) {
		if (model != null) {
			if (model.maxCustomers == null || model.maxCustomers < 1) {
				model.maxCustomers = 1;
			}
			SortedSet<Price> prices = new TreeSet<>(CreatePriceViewModel.toPrice(model.prices));
			return new Product(model.maxCustomers, model.name, model.shortName, model.description, prices);
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

	public List<CreatePriceViewModel> getPrices() {
		return prices;
	}

	public void setPrices(List<CreatePriceViewModel> prices) {
		this.prices = prices;
	}

}
