package hr.tvz.serviceplanner.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.EventViewModelSmall;

public class EventViewModelFactory {
	public static EventViewModel toEventViewModel(Event event, ViewModelType type) {
		if (event != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium
					|| type == ViewModelType.extended) {
				List<Purchase> purchases = new ArrayList<>(event.getPurchases());
				return new EventViewModelLarge(event.getId(), event.getStartTime(), event.getEndTime(),
						ProductViewModelFactory.toProductViewModel(event.getProduct(), ViewModelType.small),
						EmployeeViewModelFactory.toEmployeeViewModel(event.getEmployee(), ViewModelType.medium),
						PurchaseViewModelFactory.toPurchaseViewModel(purchases, ViewModelType.small));
			} else {
				return new EventViewModelSmall(event.getId(), event.getStartTime(), event.getEndTime(),
						ProductViewModelFactory.toProductViewModel(event.getProduct(), ViewModelType.small),
						EmployeeViewModelFactory.toEmployeeViewModel(event.getEmployee(), ViewModelType.medium));
			}
		}
		return null;
	}

	public static List<EventViewModel> toEventViewModel(List<Event> events, ViewModelType type) {
		if (events != null) {
			return events.stream().map(v -> EventViewModelFactory.toEventViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}
}
