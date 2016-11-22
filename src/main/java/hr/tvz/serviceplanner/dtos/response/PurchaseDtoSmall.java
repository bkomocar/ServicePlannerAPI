package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.CustomerDto;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.PurchaseDto;

public class PurchaseDtoSmall implements PurchaseDto {

	private Long id;
	private ProductDto product;
	private CustomerDto customer;
	private PriceDto price;
	private Long totalDurationInMinutes;
	private Long itemsCount;
	private String currency;

	public PurchaseDtoSmall() {
		super();
	}

	public PurchaseDtoSmall(Long id, String currency, Long itemsCount, ProductDto product, CustomerDto customer,
			Long totalDurationInMinutes, PriceDto price) {
		super();
		this.id = id;
		this.currency = currency;
		this.product = product;
		this.setItemsCount(itemsCount);
		this.customer = customer;
		this.price = price;
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public PriceDto getPrice() {
		return price;
	}

	public void setPrice(PriceDto price) {
		this.price = price;
	}

	public Long getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(Long itemsCount) {
		this.itemsCount = itemsCount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
