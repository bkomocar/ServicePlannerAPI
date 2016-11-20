package hr.tvz.serviceplanner.viewmodels.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.models.Venue;

public class CreatePurchaseViewModel {

	@NotBlank(message = "Currency field can not be empty")
	@Length(max = 5, message = "Currency can not be longer than {max} characters")
	private String currency;

	@NotNull(message = "Value in smallest currency is a required field")
	@Range(min = 0, message = "Value in smallest currency can not be less than {min}")
	private Long valueInSmallestCurrency;

	@NotNull(message = "Purchase Date is a required field")
	private Date purchaseDate;

	private Date paymentDate;

	@NotNull(message = "Product id is a required field")
	private Long productId;

	private Long customerId;

	@Range(min = 1, message = "Items count can not be less than {min}")
	private Long itemsCount;
	
	@NotNull
	private Long priceId;

	public CreatePurchaseViewModel() {
		super();
	}

	public CreatePurchaseViewModel(String currency, Long valueInSmallestCurrency, Date purchaseDate, Date paymentDate,
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

	public static Purchase toPurchase(CreatePurchaseViewModel model) {
		if (model != null) {
			Purchase purchase = new Purchase(model.currency, model.valueInSmallestCurrency,model.itemsCount, null, model.purchaseDate,
					model.paymentDate,  new Product(model.productId), new Price(model.priceId));

			if (model.customerId != null) {
				purchase.setCustomer(new Customer(model.customerId));
			}

			return purchase;
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
