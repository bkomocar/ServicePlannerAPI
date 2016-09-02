package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateByNameViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface VenueService extends Operations<Venue> {

	public IdViewModel saveVenue(CreateVenueViewModel model, Long userId);

	public boolean addUser(Long venueId, CreateByNameViewModel model);

	public boolean removeUser(Long venueId, Long userId);

	public List<VenueViewModel> getVenuesForUser(Long userId, ViewModelType type);

	public boolean updateVenue(Long id, UpdateVenueViewModel venue);

}
