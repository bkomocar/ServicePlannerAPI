package hr.tvz.serviceplanner.viewmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.viewmodels.response.GroupViewModelExtended;
import hr.tvz.serviceplanner.viewmodels.response.GroupViewModelLarge;
import hr.tvz.serviceplanner.viewmodels.response.GroupViewModelSmall;

public class GroupViewModelFactory {

	public static GroupViewModel toGroupViewModel(Group group, ViewModelType type) {
		if (group != null) {
			if (type == null || type == ViewModelType.large || type == ViewModelType.medium) {
				return new GroupViewModelLarge(group.getId(), group.getName(), group.getType());
			} else if (type == ViewModelType.extended) {
				List<Category> categories = new ArrayList<>(group.getCategories());
				return new GroupViewModelExtended(group.getId(), group.getName(),
						CategoryViewModelFactory.toCategoryViewModel(categories, ViewModelType.extended));
			} else {
				return new GroupViewModelSmall(group.getId(), group.getName());
			}
		}
		return null;
	}

	public static List<GroupViewModel> toGroupViewModel(List<Group> groups, ViewModelType type) {
		if (groups != null) {
			return groups.stream().map(v -> GroupViewModelFactory.toGroupViewModel(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
