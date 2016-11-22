package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.VenueDto;
import hr.tvz.serviceplanner.dtos.request.CreateByNameDto;
import hr.tvz.serviceplanner.dtos.request.CreateVenueDto;
import hr.tvz.serviceplanner.dtos.request.UpdateVenueDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Venue;

public interface VenueService extends Operations<Venue> {

	public VenueDto getVenue(Long venueId, DtoType type);

	public VenueDto saveVenue(CreateVenueDto model, Long userId);

	public boolean addUser(Long venueId, CreateByNameDto model);

	public boolean removeUser(Long venueId, Long userId);

	public List<VenueDto> getVenuesForUser(Long userId, DtoType type);

	public boolean updateVenue(Long id, UpdateVenueDto venue);

	public GroupDto getGroup(Long venueId, String name, DtoType type);

}
