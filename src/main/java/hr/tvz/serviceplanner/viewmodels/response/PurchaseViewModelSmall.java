package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;

public class PurchaseViewModelSmall {

	private Long id;
	private ProductViewModel product;
	private CustomerViewModelSmall customer;
	private PriceViewModel price;
	private Long totalDurationInMinutes;

	public PurchaseViewModelSmall() {
		super();
	}

	public PurchaseViewModelSmall(Long id, ProductViewModel product, CustomerViewModelSmall customer,
			Long totalDurationInMinutes, PriceViewModel price) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
		this.price = price;
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public static PurchaseViewModelSmall fromPurchase(Purchase purchase) {
		if (purchase != null) {
			return new PurchaseViewModelSmall(purchase.getId(),
					ProductViewModel.toProductViewModel(purchase.getProduct(), ViewModelType.small),
					CustomerViewModelSmall.fromCustomer(purchase.getCustomer()), purchase.getTotalDurationInMinutes(),
					PriceViewModel.fromPrice(purchase.getPrice()));
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

	public ProductViewModel getProduct() {
		return product;
	}

	public void setProduct(ProductViewModel product) {
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

	public PriceViewModel getPrice() {
		return price;
	}

	public void setPrice(PriceViewModel price) {
		this.price = price;
	}

}
