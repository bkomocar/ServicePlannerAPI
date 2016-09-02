package hr.tvz.serviceplanner.controllers;

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

import hr.tvz.serviceplanner.persistence.services.interfaces.GroupService;
import hr.tvz.serviceplanner.persistence.services.interfaces.UserRightsCheckerService;
import hr.tvz.serviceplanner.util.AuthenticationFacade;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateGroupViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

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
	public ResponseEntity<IdViewModel> createGroup(@PathVariable("venueId") long id,
			@Valid @RequestBody CreateGroupViewModel model) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			IdViewModel groupId = groupService.createGroup(id, model);
			if (groupId != null) {
				return new ResponseEntity<IdViewModel>(groupId, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<IdViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<IdViewModel>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateGroup(@PathVariable("venueId") long id, @PathVariable("groupId") long groupId,
			@Valid @RequestBody UpdateGroupViewModel model) {
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
	public ResponseEntity<GroupViewModel> getGroup(@PathVariable("venueId") long id,
			@PathVariable("groupId") long groupId, @RequestParam(name = "type", required = false) ViewModelType type) {
		Long userId = authenticationFacade.getUserId();
		if (userRightsCheckerService.hasUserRightsOnVenue(userId, id)) {
			GroupViewModel model = groupService.getGroup(groupId, type);
			if (model != null) {
				return new ResponseEntity<GroupViewModel>(model, HttpStatus.OK);
			} else {
				return new ResponseEntity<GroupViewModel>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<GroupViewModel>(HttpStatus.FORBIDDEN);
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
}
