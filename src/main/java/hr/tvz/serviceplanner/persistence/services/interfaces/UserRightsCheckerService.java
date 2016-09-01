package hr.tvz.serviceplanner.persistence.services.interfaces;

public interface UserRightsCheckerService {

	public boolean hasUserRightsOnVenue(Long userId, Long venueId);

}
