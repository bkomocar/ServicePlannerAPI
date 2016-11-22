package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.ProductDto;

public class ProductDtoExtended implements ProductDto {

	private Long id;
	private String name;
	private List<PriceDto> prices = new ArrayList<>();
	private Long categoryId;

	public ProductDtoExtended() {
		super();
	}

	public ProductDtoExtended(Long id, String name, List<PriceDto> prices, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.prices = prices;
		this.categoryId = categoryId;
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

	public List<PriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceDto> prices) {
		this.prices = prices;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
