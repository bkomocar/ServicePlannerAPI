package hr.tvz.serviceplanner.dtos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.VenueDtoExtended;
import hr.tvz.serviceplanner.dtos.response.VenueDtoLarge;
import hr.tvz.serviceplanner.dtos.response.VenueDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;

public class VenueDtoFactory {

	public static VenueDto toVenueDto(Venue venue, DtoType type) {
		if (venue != null) {

			if (type == null || type == DtoType.large || type == DtoType.medium) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				return new VenueDtoLarge(venue.getId(), venue.getName(), venue.getDescription(), venue.getOwner(),
						sdf.format(venue.getOpenTime()), sdf.format(venue.getCloseTime()), venue.getType());
			} else if (type == DtoType.extended) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				List<Group> groups = new ArrayList<>(venue.getGroups());
				return new VenueDtoExtended(venue.getId(), venue.getName(), venue.getDescription(), venue.getOwner(),
						sdf.format(venue.getOpenTime()), sdf.format(venue.getCloseTime()), venue.getType(),
						GroupDtoFactory.toGroupDto(groups, DtoType.large));
			} else {
				return new VenueDtoSmall(venue.getId(), venue.getName(), venue.getType());
			}
		}
		return null;
	}

	public static List<VenueDto> toVenueDto(List<Venue> venues, DtoType type) {
		if (venues != null) {
			return venues.stream().map(v -> VenueDtoFactory.toVenueDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
