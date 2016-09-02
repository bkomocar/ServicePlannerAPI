package hr.tvz.serviceplanner.viewmodels;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelSmall;

public class VenueViewModel {

	public static VenueViewModel toVenueViewModel(Venue venue, ViewModelType type) {
		if (venue != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium) {
				return new VenueViewModelLarge(venue.getId(), venue.getName(), venue.getDescription(), venue.getOwner(),
						venue.getOpenTime().toString(), venue.getCloseTime().toString());
			} else {
				return new VenueViewModelSmall(venue.getId(), venue.getName());
			}
		}
		return null;
	}

	public static List<VenueViewModel> toVenueViewModel(List<Venue> venues, ViewModelType type) {
		if (venues != null) {
			return venues.stream().map(v -> VenueViewModel.toVenueViewModel(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
