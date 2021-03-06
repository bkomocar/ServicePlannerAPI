package hr.tvz.serviceplanner.dtos.request;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;

public class CreateProductDto {

	@Range(min = 1, message = "Maximum customers can not be less than {min}")
	private Integer maxCustomers;

	@NotBlank(message = "Name field can not be empty")
	@Length(max = 50, message = "Name can not be longer than {max} characters")
	private String name;

	@NotBlank(message = "Short name field can not be empty")
	@Length(max = 10, message = "Short name can not be longer than {max} characters")
	private String shortName;

	@NotEmpty(message = "Price has to be set")
	public List<CreatePriceDto> prices = new ArrayList<>();

	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;

	public CreateProductDto() {
		super();
	}

	public CreateProductDto(Integer maxCustomers, String name, String shortName, String description) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
	}

	public CreateProductDto(Integer maxCustomers, String name, String shortName, List<CreatePriceDto> prices,
			String description) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.prices = prices;
		this.description = description;
	}

	public static Product toProduct(CreateProductDto model) {
		if (model != null) {
			if (model.maxCustomers == null) {
				model.maxCustomers = 1;
			}
			SortedSet<Price> prices = new TreeSet<>(CreatePriceDto.toPrice(model.prices));
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

	public List<CreatePriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<CreatePriceDto> prices) {
		this.prices = prices;
	}

}
