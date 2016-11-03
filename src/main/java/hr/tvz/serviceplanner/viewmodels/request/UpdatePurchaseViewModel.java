package hr.tvz.serviceplanner.viewmodels.request;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class UpdatePurchaseViewModel {

	@Length(min = 1, max = 5, message = "Currency length should be between {min} and {max} characters")
	private String currency;

	@Range(min = 0, message = "Value in smallest currency can not be less than {min}")
	private Long valueInSmallestCurrency;

	private Date purchaseDate;

	private Date paymentDate;

	private Long productId;

	private Long customerId;

	private Long priceId;
	
	public UpdatePurchaseViewModel() {
		super();
	}

	public UpdatePurchaseViewModel(String currency, Long valueInSmallestCurrency, Date purchaseDate, Date paymentDate,
			Long productId, Long customerId, Long priceId) {
		super();
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
		this.productId = productId;
		this.customerId = customerId;
		this.priceId = priceId;
	}

	public static Purchase toPurchase(UpdatePurchaseViewModel model) {
		if (model != null) {
			return new Purchase(model.currency, model.valueInSmallestCurrency, null, model.purchaseDate, model.paymentDate,
					new Product(model.productId), new Customer(model.customerId), new Price(model.priceId));
		}
		return null;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

}
