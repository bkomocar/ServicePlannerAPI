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

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.request.CreateGroupDto;
import hr.tvz.serviceplanner.dtos.request.UpdateGroupDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.TimespanEventDto;
import hr.tvz.serviceplanner.persistence.services.interfaces.GroupService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;

@RestController
@RequestMapping("venues/{venueId}/groups")
public class GroupsController {

	@Autowired
	private UserRightsCheckerService userRightsCheckerService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private GroupService groupService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<IdDto> createGroup(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateGroupDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdDto groupId = groupService.createGroup(id, model);
			if (groupId != null) {
				return new ResponseEntity<IdDto>(groupId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateGroup(@PathVariable("venueId") long id, @PathVariable("groupId") long groupId,
			@Valid @RequestBody UpdateGroupDto model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			if (groupService.updateGroup(groupId, model) != false) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
	public ResponseEntity<GroupDto> getGroup(@PathVariable("venueId") long id, @PathVariable("groupId") long groupId,
			@RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			GroupDto model = groupService.getGroup(groupId, type);
			if (model != null) {
				return new ResponseEntity<GroupDto>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<GroupDto>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<GroupDto>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{groupId}/categories", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDto>> getCategoriesForGroup(@PathVariable("venueId") long id,
			@PathVariable("groupId") long groupId, @RequestParam(name = "type", required = false) DtoType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<CategoryDto> models = groupService.getCategoriesForGroup(id, groupId, type);
			if (models != null) {
				return new ResponseEntity<List<CategoryDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<CategoryDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<CategoryDto>>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGroup(@PathVariable("venueId") long id, @PathVariable("groupId") long groupId) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			groupService.deleteById(groupId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	/* date in yyyy-MM-dd */
	@RequestMapping(value = "/{groupId}/events", method = RequestMethod.GET)
	public ResponseEntity<List<TimespanEventDto>> getTimeEventsForGroupByDate(@PathVariable("venueId") long id,
			@PathVariable("groupId") long groupId, @RequestParam(name = "date", required = true) String date) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			List<TimespanEventDto> models = groupService.getTimeEventsForGroupByDate(id, groupId, date);
			if (models != null) {
				return new ResponseEntity<List<TimespanEventDto>>(models, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<TimespanEventDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<TimespanEventDto>>(HttpStatus.FORBIDDEN);
		}
	}
}
