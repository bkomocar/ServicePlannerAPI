package hr.tvz.serviceplanner.viewmodels;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.VenueViewModelSmall;

public class VenueViewModelFactory {

	public static VenueViewModel toVenueViewModel(Venue venue, ViewModelType type) {
		if (venue != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium
					|| type == ViewModelType.extended) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				return new VenueViewModelLarge(venue.getId(), venue.getName(), venue.getDescription(), venue.getOwner(),
						sdf.format(venue.getOpenTime()), sdf.format(venue.getCloseTime()), venue.getType());
			} else {
				return new VenueViewModelSmall(venue.getId(), venue.getName(), venue.getType());
			}
		}
		return null;
	}

	public static List<VenueViewModel> toVenueViewModel(List<Venue> venues, ViewModelType type) {
		if (venues != null) {
			return venues.stream().map(v -> VenueViewModelFactory.toVenueViewModel(v, type))
					.collect(Collectors.toList());
		}
		return null;
	}
}
