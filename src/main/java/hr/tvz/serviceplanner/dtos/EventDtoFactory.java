package hr.tvz.serviceplanner.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.EventDtoLarge;
import hr.tvz.serviceplanner.dtos.response.EventDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class EventDtoFactory {
	public static EventDto toEventDto(Event event, DtoType type) {
		if (event != null) {
			if (type == null || type == DtoType.large || type == DtoType.medium || type == DtoType.extended) {
				List<Purchase> purchases = new ArrayList<>(event.getPurchases());
				return new EventDtoLarge(event.getId(), event.getStartTime(), event.getEndTime(),
						ProductDtoFactory.toProductDto(event.getProduct(), DtoType.small),
						EmployeeDtoFactory.toEmployeeDto(event.getEmployee(), DtoType.medium),
						PurchaseDtoFactory.toPurchaseDto(purchases, DtoType.small));
			} else {
				return new EventDtoSmall(event.getId(), event.getStartTime(), event.getEndTime(),
						ProductDtoFactory.toProductDto(event.getProduct(), DtoType.small),
						EmployeeDtoFactory.toEmployeeDto(event.getEmployee(), DtoType.medium));
			}
		}
		return null;
	}

	public static List<EventDto> toEventDto(List<Event> events, DtoType type) {
		if (events != null) {
			return events.stream().map(v -> EventDtoFactory.toEventDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
