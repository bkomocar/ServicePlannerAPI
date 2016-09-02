package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class PurchaseViewModelLarge {

	private Long id;
	private String currency;
	private Long valueInSmallestCurrency;
	private Date purchaseDate;
	private Date paymentDate;
	private ProductViewModel product;
	private CustomerViewModel customer;
	private PriceViewModel price;
	private Long totalDurationInMinutes;
	private List<EventViewModelSmall> events = new ArrayList<>();

	public PurchaseViewModelLarge() {
		super();
	}

	public PurchaseViewModelLarge(Long id, String currency, Long valueInSmallestCurrency, Date purchaseDate,
			Date paymentDate, ProductViewModel product, CustomerViewModel customer, PriceViewModel price,
			Long totalDurationInMinutes, List<EventViewModelSmall> events) {
		super();
		this.id = id;
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

	public static PurchaseViewModelLarge fromPurchase(Purchase purchase) {
		if (purchase != null) {
			List<Event> events = new ArrayList<>(purchase.getEvents());
			return new PurchaseViewModelLarge(purchase.getId(), purchase.getCurrency(),
					purchase.getValueInSmallestCurrency(), purchase.getPurchaseDate(), purchase.getPaymentDate(),
					ProductViewModel.fromProduct(purchase.getProduct()),
					CustomerViewModel.fromCustomer(purchase.getCustomer()),
					PriceViewModel.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes(),
					EventViewModelSmall.fromEvent(events));
		}
		return null;
	}

	public static List<PurchaseViewModelLarge> fromPurchase(List<Purchase> purchases) {
		if (purchases != null) {
			return purchases.stream().map(u -> PurchaseViewModelLarge.fromPurchase(u)).collect(Collectors.toList());
		}
		return null;
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

	public PriceViewModel getPrice() {
		return price;
	}

	public void setPrice(PriceViewModel price) {
		this.price = price;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public List<EventViewModelSmall> getEvents() {
		return events;
	}

	public void setEvents(List<EventViewModelSmall> events) {
		this.events = events;
	}

}
