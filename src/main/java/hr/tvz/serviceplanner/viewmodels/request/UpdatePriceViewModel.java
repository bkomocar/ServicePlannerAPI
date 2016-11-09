package hr.tvz.serviceplanner.viewmodels.request;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

public class UpdatePriceViewModel {

	Long id;

	@Length(min = 1, max = 255, message = "Name length should be between {min} and {max} characters")
	private String name;
	
	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;
	
	@Range(min = 0, message = "Duration in minutes can not be less than {min}")
	private Long durationInMin;
	
	@Range(min = 1, message = "Items count can not be less than {min}")
	private Long itemsCount;
	
	public List<UpdateCostViewModel> costs = new ArrayList<>();
	
	public UpdatePriceViewModel() {
		super();
	}

	public UpdatePriceViewModel(Long id, String name, String description, Long durationInMin, Long itemsCount, ArrayList<UpdateCostViewModel> costs) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}

	public static Price toPrice(UpdatePriceViewModel model) {
		if (model != null) {
			SortedSet<Cost> costs = new TreeSet<>(UpdateCostViewModel.toCost(model.costs));
			return new Price(model.id, model.name, model.description, model.durationInMin, model.itemsCount, costs);
		}
		return null;
	}
	
	public static List<Price> toPrice(List<UpdatePriceViewModel> models) {
		if (models != null) {
			return models.stream().map(u -> UpdatePriceViewModel.toPrice(u)).collect(Collectors.toList());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
