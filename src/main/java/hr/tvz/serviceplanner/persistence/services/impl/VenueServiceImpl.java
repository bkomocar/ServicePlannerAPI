package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.GroupDtoFactory;
import hr.tvz.serviceplanner.dtos.VenueDto;
import hr.tvz.serviceplanner.dtos.VenueDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreateByNameDto;
import hr.tvz.serviceplanner.dtos.request.CreateVenueDto;
import hr.tvz.serviceplanner.dtos.request.UpdateVenueDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.VenueDao;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Venue;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;

@Service
public class VenueServiceImpl extends AbstractService<Venue> implements VenueService {

	@Autowired
	private VenueDao dao;

	@Override
	protected Operations<Venue> getDao() {
		return dao;
	}

	@Override
	public VenueDto saveVenue(CreateVenueDto model, Long userId) {
		Venue daoVenue = this.dao.saveVenue(CreateVenueDto.toVenue(model), userId);
		if (daoVenue != null) {
			return VenueDtoFactory.toVenueDto(daoVenue, DtoType.extended);
		}
		return null;
	}

	@Override
	public boolean addUser(Long venueId, CreateByNameDto model) {
		if (venueId != null && model != null && model.getName() != null) {
			return dao.addUser(venueId, model.getName());
		}
		return false;
	}

	@Override
	public List<VenueDto> getVenuesForUser(Long userId, DtoType type) {
		SortedSet<Venue> venues = dao.getVenuesForUser(userId);
		if (venues != null) {
			return VenueDtoFactory.toVenueDto(new ArrayList<Venue>(venues), type);
		}
		return null;
	}

	@Override
	public boolean updateVenue(Long id, UpdateVenueDto venue) {
		if (venue != null) {
			return dao.updateVenue(id, UpdateVenueDto.toVenue(venue));
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
	public GroupDto getGroup(Long venueId, String name, DtoType type) {
		Group group = dao.getGroup(venueId, name);
		if (group != null) {
			return GroupDtoFactory.toGroupDto(group, type);
		}
		return null;
	}

	@Override
	public VenueDto getVenue(Long venueId, DtoType type) {
		Venue venue = dao.getVenue(venueId);
		if (venue != null) {
			return VenueDtoFactory.toVenueDto(venue, type);
		}
		return null;
	}

}
