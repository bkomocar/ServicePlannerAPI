package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.viewmodels.ProductViewModel;

public class ProductViewModelExtended extends ProductViewModel {

	private Long id;
	private String name;
	private List<PriceViewModel> prices = new ArrayList<>();

	public ProductViewModelExtended() {
		super();
	}

	public ProductViewModelExtended(Long id, String name, List<PriceViewModel> prices) {
		super();
		this.id = id;
		this.name = name;
		this.prices = prices;
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

	public List<PriceViewModel> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceViewModel> prices) {
		this.prices = prices;
	}

}
