package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface VenueService extends Operations<Venue> {
	
	public IdViewModel saveVenue(Venue venue, Long userId);
	
	public boolean addUser(Long venueId, String userName);
	
	public List<Venue> getVenuesForUser(Long userId);
		
	public boolean updateVenue(Long id, UpdateVenueViewModel venue);
	
}

