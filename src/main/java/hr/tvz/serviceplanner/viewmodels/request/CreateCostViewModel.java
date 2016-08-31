package hr.tvz.serviceplanner.viewmodels.request;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import hr.tvz.serviceplanner.persistence.models.Cost;

public class CreateCostViewModel {

	@NotNull
	private String currency;

	@NotNull
	@Min(0)
	private Long valueInSmallestCurrency;

	public CreateCostViewModel(String currency, Long valueInSmallestCurrency) {
		super();
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public static Cost toCost(CreateCostViewModel model) {
		if (model != null) {
			return new Cost(model.currency, model.valueInSmallestCurrency);
		}
		return null;
	}

	public static List<Cost> toCost(List<CreateCostViewModel> models) {
		if (models != null) {
			return models.stream().map(u -> CreateCostViewModel.toCost(u)).collect(Collectors.toList());
		}
		return null;
	}

	public CreateCostViewModel() {
		super();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getValueInSmallestCurrency() {
		return valueInSmallestCurrency;
	}

	public void setValueInSmallestCurrency(Long valueInSmallestCurrency) {
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

}
