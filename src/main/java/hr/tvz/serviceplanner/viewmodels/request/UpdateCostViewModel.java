package hr.tvz.serviceplanner.viewmodels.request;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Cost;

public class UpdateCostViewModel {

	private String currency;

	private Long valueInSmallestCurrency;

	public UpdateCostViewModel(String currency, Long valueInSmallestCurrency) {
		super();
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public static Cost toCost(UpdateCostViewModel model) {
		if (model != null) {
			if (model.valueInSmallestCurrency != null && model.valueInSmallestCurrency < 0) {
				model.valueInSmallestCurrency = 0L;
			}
			return new Cost(model.currency, model.valueInSmallestCurrency);
		}
		return null;
	}

	public static List<Cost> toCost(List<UpdateCostViewModel> models) {
		if (models != null) {
			return models.stream().map(u -> UpdateCostViewModel.toCost(u)).collect(Collectors.toList());
		}
		return null;
	}

	public UpdateCostViewModel() {
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
