package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.viewmodels.ProductViewModel;

public class ProductViewModelLarge implements ProductViewModel {

	private Long id;
	private Integer maxCustomers;
	private String name;
	private String shortName;
	private String description;
	private List<PriceViewModel> prices = new ArrayList<>();

	public ProductViewModelLarge() {
		super();
	}

	public ProductViewModelLarge(Long id, Integer maxCustomers, String name, String shortName, String description,
			List<PriceViewModel> prices) {
		super();
		this.id = id;
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
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

	public List<PriceViewModel> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceViewModel> prices) {
		this.prices = prices;
	}

}
