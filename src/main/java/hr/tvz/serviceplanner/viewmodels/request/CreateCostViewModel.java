package hr.tvz.serviceplanner.viewmodels.request;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Cost;

public class CreateCostViewModel {

	@NotBlank(message = "Currency field can not be empty")
	@Length(max = 5, message = "Currency can not be longer than {max} characters")
	private String currency;

	@NotNull(message = "Value in smallest currency is a required field")
	@Range(min = 0, message = "Value in smallest currency can not be less than {min}")
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
