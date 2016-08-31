package hr.tvz.serviceplanner.viewmodels.request;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Price;

public class UpdatePriceViewModel {

	@NotNull
	@Length(max = 255)
	private String name;
	
	@Length(max = 500)
	private String description;
	
	private Long durationInMin;
	
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
			if(model.durationInMin != null && model.durationInMin < 0){
				model.durationInMin = 0L;
			} 
			if(model.itemsCount != null && model.itemsCount < 1){
				model.itemsCount = 1L;
			}
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
