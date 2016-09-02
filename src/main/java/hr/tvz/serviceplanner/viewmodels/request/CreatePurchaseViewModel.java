package hr.tvz.serviceplanner.viewmodels.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class CreatePurchaseViewModel {

	@NotNull
	@Length(max = 5)
	private String currency;

	@NotNull
	private Long valueInSmallestCurrency;

	@NotNull
	private Date purchaseTime;

	@NotNull
	private Long productId;

	@NotNull
	private Long customerId;

	@NotNull
	private Long priceId;

	public CreatePurchaseViewModel() {
		super();
	}

	public CreatePurchaseViewModel(String currency, Long valueInSmallestCurrency, Date purchaseTime, Long productId,
			Long customerId, Long priceId) {
		super();
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseTime = purchaseTime;
		this.productId = productId;
		this.customerId = customerId;
		this.priceId = priceId;
	}

	public static Purchase toPurchase(CreatePurchaseViewModel model) {
		if (model != null) {
			if(model.valueInSmallestCurrency <0){
				model.valueInSmallestCurrency = 0L;
			}
			return new Purchase(model.currency, model.valueInSmallestCurrency, model.purchaseTime,
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

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
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
