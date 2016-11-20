package hr.tvz.serviceplanner.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.viewmodels.response.PriceViewModel;
import hr.tvz.serviceplanner.viewmodels.response.PurchaseViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.PurchaseViewModelMedium;
import hr.tvz.serviceplanner.viewmodels.response.PurchaseViewModelSmall;

public class PurchaseViewModelFactory {

	public static PurchaseViewModel toPurchaseViewModel(Purchase purchase, ViewModelType type) {
		if (purchase != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.extended) {
				List<Event> events = new ArrayList<>(purchase.getEvents());
				return new PurchaseViewModelLarge(purchase.getId(), purchase.getCurrency(),
						purchase.getValueInSmallestCurrency(), purchase.getItemsCount(), purchase.getPurchaseDate(), purchase.getPaymentDate(),
						ProductViewModelFactory.toProductViewModel(purchase.getProduct(), ViewModelType.large),
						CustomerViewModelFactory.toCustomerViewModel(purchase.getCustomer(), ViewModelType.large),
						PriceViewModel.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes(),
						EventViewModelFactory.toEventViewModel(events, ViewModelType.small));
			} else if (type == ViewModelType.medium) {
				return new PurchaseViewModelMedium(purchase.getId(), purchase.getCurrency(),
						purchase.getValueInSmallestCurrency(), purchase.getItemsCount(), purchase.getPurchaseDate(), purchase.getPaymentDate(),
						ProductViewModelFactory.toProductViewModel(purchase.getProduct(), ViewModelType.large),
						CustomerViewModelFactory.toCustomerViewModel(purchase.getCustomer(), ViewModelType.large),
						PriceViewModel.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes());
			} else {
				return new PurchaseViewModelSmall(purchase.getId(), purchase.getCurrency(), purchase.getItemsCount(),
						ProductViewModelFactory.toProductViewModel(purchase.getProduct(), ViewModelType.small),
						CustomerViewModelFactory.toCustomerViewModel(purchase.getCustomer(), ViewModelType.small),
						purchase.getTotalDurationInMinutes(), PriceViewModel.fromPrice(purchase.getPrice()));
			}
		}
		return null;
	}

	public static List<PurchaseViewModel> toPurchaseViewModel(List<Purchase> purchases, ViewModelType type) {
		if (purchases != null) {
			return purchases.stream().map(v -> PurchaseViewModelFactory.toPurchaseViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}

}
