package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

public class PriceDto {

	private Long id;
	private String name;
	private String description;
	private Long durationInMin;
	private Long itemsCount;
	private List<CostDto> costs = new ArrayList<>();

	public PriceDto(Long id, String name, String description, Long durationInMin, Long itemsCount,
			List<CostDto> costs) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}

	public static PriceDto fromPrice(Price price) {
		if (price != null) {
			List<Cost> costs = new ArrayList<>(price.getCosts());
			return new PriceDto(price.getId(), price.getName(), price.getDescription(), price.getDurationInMin(),
					price.getItemsCount(), CostDto.fromCost(costs));
		}
		return null;
	}

	public static List<PriceDto> fromPrice(List<Price> prices) {
		if (prices != null) {
			return prices.stream().map(u -> PriceDto.fromPrice(u)).collect(Collectors.toList());
		}
		return null;
	}

	public PriceDto() {
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

	public List<CostDto> getCosts() {
		return costs;
	}

	public void setCosts(List<CostDto> costs) {
		this.costs = costs;
	}

}
