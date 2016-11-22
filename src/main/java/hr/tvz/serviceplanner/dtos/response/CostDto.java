package hr.tvz.serviceplanner.dtos.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Cost;

public class CostDto {
	private Long id;
	private String currency;
	private Long valueInSmallestCurrency;

	public CostDto(Long id, String currency, Long valueInSmallestCurrency) {
		super();
		this.id = id;
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public static CostDto fromCost(Cost cost) {
		if (cost != null) {
			return new CostDto(cost.getId(), cost.getCurrency(), cost.getValueInSmallestCurrency());
		}
		return null;
	}

	public static List<CostDto> fromCost(List<Cost> costs) {
		if (costs != null) {
			return costs.stream().map(u -> CostDto.fromCost(u)).collect(Collectors.toList());
		}
		return null;
	}

	public CostDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
