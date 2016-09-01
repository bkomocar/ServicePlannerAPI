package hr.tvz.serviceplanner.persistence.dao.interfaces;
import java.util.SortedSet;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Venue;

public interface VenueDao extends Operations<Venue> {

	public Venue saveVenue(Venue venue, Long userId);
	
	public boolean addUser(Long venueId, String userName);
	
	public boolean removeUser(Long venueId, Long userId);
	
	public SortedSet<Venue> getVenuesForUser(Long userId);	
		
	public boolean updateVenue(Long id, Venue venue);
	
}
