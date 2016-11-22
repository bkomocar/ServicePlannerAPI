package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.tvz.serviceplanner.dtos.CustomerDto;
import hr.tvz.serviceplanner.dtos.EventDto;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.PurchaseDto;

public class PurchaseDtoLarge implements PurchaseDto {

	private Long id;
	private String currency;
	private Long valueInSmallestCurrency;
	private Long itemsCount;
	private Date purchaseDate;
	private Date paymentDate;
	private ProductDto product;
	private CustomerDto customer;
	private PriceDto price;
	private Long totalDurationInMinutes;
	private List<EventDto> events = new ArrayList<>();

	public PurchaseDtoLarge() {
		super();
	}

	public PurchaseDtoLarge(Long id, String currency, Long valueInSmallestCurrency, Long itemsCount, Date purchaseDate,
			Date paymentDate, ProductDto product, CustomerDto customer, PriceDto price, Long totalDurationInMinutes,
			List<EventDto> events) {
		super();
		this.id = id;
		this.itemsCount = itemsCount;
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
		this.product = product;
		this.customer = customer;
		this.price = price;
		this.totalDurationInMinutes = totalDurationInMinutes;
		this.events = events;
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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

	public PriceDto getPrice() {
		return price;
	}

	public void setPrice(PriceDto price) {
		this.price = price;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public List<EventDto> getEvents() {
		return events;
	}

	public void setEvents(List<EventDto> events) {
		this.events = events;
	}

}
