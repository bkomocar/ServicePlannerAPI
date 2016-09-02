package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.VenueDao;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateByNameViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateVenueViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class VenueServiceImpl extends AbstractService<Venue> implements VenueService {

	@Autowired
	private VenueDao dao;

	@Override
	protected Operations<Venue> getDao() {
		return dao;
	}

	@Override
	public IdViewModel saveVenue(CreateVenueViewModel model, Long userId) {
		Venue daoVenue = this.dao.saveVenue(CreateVenueViewModel.toVenue(model), userId);
		if (daoVenue != null) {
			return new IdViewModel(daoVenue.getId());
		}
		return null;
	}

	@Override
	public boolean addUser(Long venueId, CreateByNameViewModel model) {
		if (venueId != null && model != null && model.getName() != null) {
			return dao.addUser(venueId, model.getName());
		}
		return false;
	}

	@Override
	public List<VenueViewModel> getVenuesForUser(Long userId, ViewModelType type) {
		SortedSet<Venue> venues = dao.getVenuesForUser(userId);
		if (venues != null) {
			return VenueViewModel.toVenueViewModel(new ArrayList<Venue>(venues), type);
		}
		return null;
	}

	@Override
	public boolean updateVenue(Long id, UpdateVenueViewModel venue) {
		if (venue != null) {
			return dao.updateVenue(id, UpdateVenueViewModel.toVenue(venue));
		}
		return false;
	}

	@Override
	public boolean removeUser(Long venueId, Long userId) {
		if (venueId != null && userId != null) {
			return dao.removeUser(venueId, userId);
		}
		return false;
	}

	@Override
	public GroupViewModel getGroup(Long venueId, String name, ViewModelType type) {
		Group group = dao.getGroup(venueId, name);
		if (group != null) {
			return GroupViewModel.toGroupViewModel(group, type);
		}
		return null;
	}

}
