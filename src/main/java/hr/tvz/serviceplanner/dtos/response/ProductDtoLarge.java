package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.ProductDto;

public class ProductDtoLarge implements ProductDto {

	private Long id;
	private Integer maxCustomers;
	private String name;
	private String shortName;
	private String description;
	private List<PriceDto> prices = new ArrayList<>();
	private Long categoryId;

	public ProductDtoLarge() {
		super();
	}

	public ProductDtoLarge(Long id, Integer maxCustomers, String name, String shortName, String description,
			List<PriceDto> prices, Long categoryId) {
		super();
		this.id = id;
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.categoryId = categoryId;
		this.prices = prices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<PriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceDto> prices) {
		this.prices = prices;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
