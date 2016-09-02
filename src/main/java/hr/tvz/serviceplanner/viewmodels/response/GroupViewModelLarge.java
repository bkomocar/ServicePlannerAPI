package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;

public class GroupViewModelLarge extends GroupViewModel {

	private Long id;

	private String name;

	private GroupType type;

	public GroupViewModelLarge() {
		super();
	}

	public GroupViewModelLarge(Long id, String name, GroupType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public GroupType getType() {
		return type;
	}

	public void setType(GroupType type) {
		this.type = type;
	}

}
