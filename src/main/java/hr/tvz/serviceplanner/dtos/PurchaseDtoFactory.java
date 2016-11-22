package hr.tvz.serviceplanner.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.PriceDto;
import hr.tvz.serviceplanner.dtos.response.PurchaseDtoLarge;
import hr.tvz.serviceplanner.dtos.response.PurchaseDtoMedium;
import hr.tvz.serviceplanner.dtos.response.PurchaseDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class PurchaseDtoFactory {

	public static PurchaseDto toPurchaseDto(Purchase purchase, DtoType type) {
		if (purchase != null) {
			if (type == null || type == DtoType.large || type == DtoType.extended) {
				List<Event> events = new ArrayList<>(purchase.getEvents());
				return new PurchaseDtoLarge(purchase.getId(), purchase.getCurrency(),
						purchase.getValueInSmallestCurrency(), purchase.getItemsCount(), purchase.getPurchaseDate(),
						purchase.getPaymentDate(), ProductDtoFactory.toProductDto(purchase.getProduct(), DtoType.large),
						CustomerDtoFactory.toCustomerDto(purchase.getCustomer(), DtoType.large),
						PriceDto.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes(),
						EventDtoFactory.toEventDto(events, DtoType.small));
			} else if (type == DtoType.medium) {
				return new PurchaseDtoMedium(purchase.getId(), purchase.getCurrency(),
						purchase.getValueInSmallestCurrency(), purchase.getItemsCount(), purchase.getPurchaseDate(),
						purchase.getPaymentDate(), ProductDtoFactory.toProductDto(purchase.getProduct(), DtoType.large),
						CustomerDtoFactory.toCustomerDto(purchase.getCustomer(), DtoType.large),
						PriceDto.fromPrice(purchase.getPrice()), purchase.getTotalDurationInMinutes());
			} else {
				return new PurchaseDtoSmall(purchase.getId(), purchase.getCurrency(), purchase.getItemsCount(),
						ProductDtoFactory.toProductDto(purchase.getProduct(), DtoType.small),
						CustomerDtoFactory.toCustomerDto(purchase.getCustomer(), DtoType.small),
						purchase.getTotalDurationInMinutes(), PriceDto.fromPrice(purchase.getPrice()));
			}
		}
		return null;
	}

	public static List<PurchaseDto> toPurchaseDto(List<Purchase> purchases, DtoType type) {
		if (purchases != null) {
			return purchases.stream().map(v -> PurchaseDtoFactory.toPurchaseDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}

}
