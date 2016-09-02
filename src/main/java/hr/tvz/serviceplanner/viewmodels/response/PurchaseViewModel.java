package hr.tvz.serviceplanner.viewmodels.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;

public class PurchaseViewModel {

	private Long id;
	private String currency;
	private Long valueInSmallestCurrency;
	private Date purchaseDate;
	private Date paymentDate;
	private ProductViewModel product;
	private CustomerViewModel customer;
	private PriceViewModel price;
	private Long totalDurationInMinutes;

	public PurchaseViewModel() {
		super();
	}

	public PurchaseViewModel(Long id, String currency, Long valueInSmallestCurrency, Date purchaseDate,
			Date paymentDate, ProductViewModel product, CustomerViewModel customer, PriceViewModel price,
			Long totalDurationInMinutes) {
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
	}

	public static PurchaseViewModel fromPurchase(Purchase purchase) {
		if (purchase != null) {
			return new PurchaseViewModel(purchase.getId(), purchase.getCurrency(),
					purchase.getValueInSmallestCurrency(), purchase.getPurchaseDate(), purchase.getPaymentDate(),
					ProductViewModel.toProductViewModel(purchase.getProduct(), ViewModelType.large),
					CustomerViewModel.fromCustomer(purchase.getCustomer()),
					PriceViewModel.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes());
		}
		return null;
	}

	public static List<PurchaseViewModel> fromPurchase(List<Purchase> purchases) {
		if (purchases != null) {
			return purchases.stream().map(u -> PurchaseViewModel.fromPurchase(u)).collect(Collectors.toList());
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

}
