package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateByNameViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;

public interface VenueService extends Operations<Venue> {

	public VenueViewModel getVenue(Long venueId, ViewModelType type);

	public VenueViewModel saveVenue(CreateVenueViewModel model, Long userId);

	public boolean addUser(Long venueId, CreateByNameViewModel model);

	public boolean removeUser(Long venueId, Long userId);

	public List<VenueViewModel> getVenuesForUser(Long userId, ViewModelType type);

	public boolean updateVenue(Long id, UpdateVenueViewModel venue);

	public GroupViewModel getGroup(Long venueId, String name, ViewModelType type);

}
