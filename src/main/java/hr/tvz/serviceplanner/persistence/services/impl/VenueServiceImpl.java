package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.VenueDao;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;
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
	public IdViewModel saveVenue(Venue venue, Long userId) {
		Venue daoVenue = this.dao.saveVenue(venue, userId);
		if (daoVenue != null) {
			return new IdViewModel(daoVenue.getId());
		}
		return null;
	}

	@Override
	public boolean addUser(Long venueId, String userName) {
		if (venueId == null || userName == null) {
			return false;
		}
		return dao.addUser(venueId, userName);
	}

	@Override
	public List<Venue> getVenuesForUser(Long userId) {
		SortedSet<Venue> venues = dao.getVenuesForUser(userId);
		if (venues != null) {
			return new ArrayList<Venue>(venues);
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

}
