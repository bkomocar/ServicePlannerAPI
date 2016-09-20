package hr.tvz.serviceplanner.viewmodels.request;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Price;

public class UpdatePriceViewModel {

	@Length(min = 1, max = 255, message = "Name length should be between {min} and {max} characters")
	private String name;
	
	@Length(max = 500, message = "Description can not be longer than {max} characters")
	private String description;
	
	@Range(min = 0, message = "Duration in minutes can not be less than {min}")
	private Long durationInMin;
	
	@Range(min = 1, message = "Items count can not be less than {min}")
	private Long itemsCount;
	
	public UpdatePriceViewModel() {
		super();
	}

	public UpdatePriceViewModel(String name, String description, Long durationInMin, Long itemsCount) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
	}

	public static Price toPrice(UpdatePriceViewModel model) {
		if (model != null) {
			return new Price(model.name, model.description, model.durationInMin, model.itemsCount);
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

}
