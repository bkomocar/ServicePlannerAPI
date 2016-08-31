package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

public class PriceViewModel {

	private Long id;
	private String name;
	private String description;
	private Long durationInMin;
	private Long itemsCount;
	private List<CostViewModel> costs = new ArrayList<>();
	
	public PriceViewModel(Long id, String name, String description, Long durationInMin, Long itemsCount,
			List<CostViewModel> costs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}
	
	public static PriceViewModel fromPrice(Price price) {
		if (price != null) {
			List<Cost> costs = new ArrayList<>(price.getCosts());			
			return new PriceViewModel(price.getId(), price.getName(), price.getDescription(),
					price.getDurationInMin(), price.getItemsCount(), CostViewModel.fromCost(costs));
		}
		return null;
	}

	public static List<PriceViewModel> fromPrice(List<Price> prices) {
		if (prices != null) {
			return prices.stream().map(u -> PriceViewModel.fromPrice(u)).collect(Collectors.toList());
		}
		return null;
	}
	
	public PriceViewModel() {
		super();
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
	public Long getDurationInMin() {
		return durationInMin;
	}
	public void setDurationInMin(Long durationInMin) {
		this.durationInMin = durationInMin;
	}
	public Long getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(Long itemsCount) {
		this.itemsCount = itemsCount;
	}
	public List<CostViewModel> getCosts() {
		return costs;
	}
	public void setCosts(List<CostViewModel> costs) {
		this.costs = costs;
	}
	
	

}
