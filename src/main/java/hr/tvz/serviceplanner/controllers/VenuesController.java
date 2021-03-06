
package hr.tvz.serviceplanner.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.VenueDto;
import hr.tvz.serviceplanner.dtos.request.CreateByNameDto;
import hr.tvz.serviceplanner.dtos.request.CreateVenueDto;
import hr.tvz.serviceplanner.dtos.request.UpdateVenueDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.VenueDtoLarge;
import hr.tvz.serviceplanner.dtos.response.VenueDtoSmall;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.persistence.services.interfaces.VenueService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/venues")
public class VenuesController {

	@Autowired
	private VenueService venueService;

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@ApiOperation(value = "create a new venue", response = IdDto.class, notes = "Creates a Venue based on the supplied informations")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VenueDto> createVenue(@Valid @RequestBody CreateVenueDto createModel) {
		VenueDto model = venueService.saveVenue(createModel, authenticationFacade.getUserId());
		if (model == null) {
			return new ResponseEntity<VenueDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VenueDto>(model, HttpStatus.CREATED);
	}

	@ApiOperation(value = "get a venue by id", notes = "Returns a Venue based on the supplied id, and an optional type")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = VenueDtoLarge.class, message = "No type parameter provided"),
			@ApiResponse(code = 200, response = VenueDtoSmall.class, message = "Type parameter small") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VenueDto> getVenue(@PathVariable("id") long id,
			@RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			VenueDto model = venueService.getVenue(id, type);
			if (model != null) {
				return new ResponseEntity<VenueDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<VenueDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<VenueDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VenueDto>> getVenuesForUser(
			@RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		List<VenueDto> venues = venueService.getVenuesForUser(userId.longValue(), type);
		if (venues != null) {
			return new ResponseEntity<List<VenueDto>>(venues, HttpStatus.OK);
		}
		return new ResponseEntity<List<VenueDto>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}/users", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserToVenue(@PathVariable("id") long id, @Valid @RequestBody CreateByNameDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (venueService.addUser(id, model)) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateVenue(@PathVariable("id") long id, @Valid @RequestBody UpdateVenueDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (venueService.updateVenue(id, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVenue(@PathVariable("id") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			venueService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{venueId}/users/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeUser(@PathVariable("venueId") long venueId, @PathVariable("userId") long id) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, venueId)) {
			venueService.removeUser(venueId, id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{venueId}/{groupName}", method = RequestMethod.GET)
	public ResponseEntity<GroupDto> getGroup(@PathVariable("venueId") long id,
			@PathVariable("groupName") String groupName, @RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			GroupDto model = venueService.getGroup(id, groupName, type);
			if (model != null) {
				return new ResponseEntity<GroupDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<GroupDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<GroupDto>(HttpStatus.FORBIDDEN);
		}
	}

}
