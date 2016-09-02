package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Purchase;

public class PurchaseViewModelSmall {

	private Long id;
	private ProductViewModelSmall product;
	private CustomerViewModelSmall customer;
	private Long totalDurationInMinutes;

	public PurchaseViewModelSmall() {
		super();
	}

	public PurchaseViewModelSmall(Long id, ProductViewModelSmall product, CustomerViewModelSmall customer,
			Long totalDurationInMinutes) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public static PurchaseViewModelSmall fromPurchase(Purchase purchase) {
		if (purchase != null) {
			return new PurchaseViewModelSmall(purchase.getId(),
					ProductViewModelSmall.fromProduct(purchase.getProduct()),
					CustomerViewModelSmall.fromCustomer(purchase.getCustomer()), purchase.getTotalDurationInMinutes());
		}
		return null;
	}

	public static List<PurchaseViewModelSmall> fromPurchase(List<Purchase> purchases) {
		if (purchases != null) {
			return purchases.stream().map(u -> PurchaseViewModelSmall.fromPurchase(u)).collect(Collectors.toList());
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductViewModelSmall getProduct() {
		return product;
	}

	public void setProduct(ProductViewModelSmall product) {
		this.product = product;
	}

	public CustomerViewModelSmall getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerViewModelSmall customer) {
		this.customer = customer;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

}
