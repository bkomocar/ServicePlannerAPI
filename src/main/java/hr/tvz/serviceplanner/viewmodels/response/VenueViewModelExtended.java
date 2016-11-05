package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.enums.VenueType;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.VenueViewModel;

public class VenueViewModelExtended implements VenueViewModel {

	private Long id;
	private String name;
	private String description;
	private String owner;
	private String openTime;
	private String closeTime;
	private VenueType type;
	private List<GroupViewModel> groups = new ArrayList<>();
	
	public VenueViewModelExtended() {
		super();
	}

	public VenueViewModelExtended(Long id, String name, String description, String owner, String openTime,
			String closeTime, VenueType type, List<GroupViewModel> groups) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.type = type;
		this.groups = groups;
	}

	public List<GroupViewModel> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupViewModel> groups) {
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public VenueType getType() {
		return type;
	}

	public void setType(VenueType type) {
		this.type = type;
	}
	
	

}
