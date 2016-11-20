package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.PurchaseViewModel;

public class PurchaseViewModelSmall implements PurchaseViewModel {

	private Long id;
	private ProductViewModel product;
	private CustomerViewModel customer;
	private PriceViewModel price;
	private Long totalDurationInMinutes;
	private Long itemsCount;
	private String currency;
	public PurchaseViewModelSmall() {
		super();
	}

	public PurchaseViewModelSmall(Long id, String currency, Long itemsCount, ProductViewModel product, CustomerViewModel customer,
			Long totalDurationInMinutes, PriceViewModel price) {
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

	public ProductViewModel getProduct() {
		return product;
	}

	public void setProduct(ProductViewModel product) {
		this.product = product;
	}

	public CustomerViewModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerViewModel customer) {
		this.customer = customer;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public PriceViewModel getPrice() {
		return price;
	}

	public void setPrice(PriceViewModel price) {
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
