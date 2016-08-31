package hr.tvz.serviceplanner.viewmodels.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.models.Group;

public class AddGroupToVenueViewModel {

	@NotBlank
	@Length(min = 4, max = 255)
	private String name;

	@NotNull
	private GroupType type;

	public AddGroupToVenueViewModel() {
		super();
	}

	public AddGroupToVenueViewModel(String name, GroupType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public static Group toGroup(AddGroupToVenueViewModel model) {
		if (model != null) {
		return new Group(model.name, model.type);
	} 
		return null;
	}

	public GroupType getType() {
		return type;
	}

	public void setType(GroupType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
