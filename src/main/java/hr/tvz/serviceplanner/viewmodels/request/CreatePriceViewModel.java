package hr.tvz.serviceplanner.viewmodels.request;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

public class CreatePriceViewModel {

	@Length(max = 255, message = "Name can not be longer than {max} characters")
	private String name;

	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;

	@Range(min = 0, message = "Duration in minutes can not be less than {min}")
	private Long durationInMin;

	@Range(min = 1, message = "Items count can not be less than {min}")
	private Long itemsCount;

	@NotEmpty
	public List<CreateCostViewModel> costs = new ArrayList<>();

	public CreatePriceViewModel() {
		super();
	}

	public CreatePriceViewModel(String name, String description, Long durationInMin, Long itemsCount, ArrayList<CreateCostViewModel> costs) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}

	public static Price toPrice(CreatePriceViewModel model) {
		if (model != null) {
			if (model.durationInMin == null) {
				model.durationInMin = 0L;
			}
			if (model.itemsCount == null) {
				model.itemsCount = 1L;
			}
			SortedSet<Cost> costs = new TreeSet<>(CreateCostViewModel.toCost(model.costs));
			return new Price(model.name, model.description, model.durationInMin, model.itemsCount, costs);
		}
		return null;
	}

	public static List<Price> toPrice(List<CreatePriceViewModel> models) {
		if (models != null) {
			return models.stream().map(u -> CreatePriceViewModel.toPrice(u)).collect(Collectors.toList());
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

	public List<CreateCostViewModel> getCosts() {
		return costs;
	}

	public void setCosts(List<CreateCostViewModel> costs) {
		this.costs = costs;
	}

}
