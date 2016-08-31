package hr.tvz.serviceplanner.viewmodels.response;

import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.models.Group;

public class GroupViewModel {

	private Long id;

	private String name;

	private GroupType type;

	public GroupViewModel() {
		super();
	}

	public static GroupViewModel fromGroup(Group group) {
		if (group != null) {
			return new GroupViewModel(group.getId(), group.getName(), group.getType());
		}
		return null;
	}

	public static List<GroupViewModel> fromService(List<Group> groups) {
		if (groups != null) {
			return groups.stream().map(u -> GroupViewModel.fromGroup(u)).collect(Collectors.toList());
		}
		return null;
	}

	public GroupViewModel(Long id, String name, GroupType type) {
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
