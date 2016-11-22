package hr.tvz.serviceplanner.dtos.request;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

public class CreatePriceDto {

	@Length(max = 255, message = "Name can not be longer than {max} characters")
	private String name;

	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;

	@Range(min = 0, message = "Duration in minutes can not be less than {min}")
	private Long durationInMin;

	@Range(min = 1, message = "Items count can not be less than {min}")
	private Long itemsCount;

	@NotEmpty
	public List<CreateCostDto> costs = new ArrayList<>();

	public CreatePriceDto() {
		super();
	}

	public CreatePriceDto(String name, String description, Long durationInMin, Long itemsCount,
			ArrayList<CreateCostDto> costs) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}

	public static Price toPrice(CreatePriceDto model) {
		if (model != null) {
			if (model.durationInMin == null) {
				model.durationInMin = 0L;
			}
			if (model.itemsCount == null) {
				model.itemsCount = 1L;
			}
			SortedSet<Cost> costs = new TreeSet<>(CreateCostDto.toCost(model.costs));
			return new Price(model.name, model.description, model.durationInMin, model.itemsCount, costs);
		}
		return null;
	}

	public static List<Price> toPrice(List<CreatePriceDto> models) {
		if (models != null) {
			return models.stream().map(u -> CreatePriceDto.toPrice(u)).collect(Collectors.toList());
		}
		return null;
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

	public List<CreateCostDto> getCosts() {
		return costs;
	}

	public void setCosts(List<CreateCostDto> costs) {
		this.costs = costs;
	}

}
