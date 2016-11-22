package hr.tvz.serviceplanner.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.dtos.response.GroupDtoExtended;
import hr.tvz.serviceplanner.dtos.response.GroupDtoLarge;
import hr.tvz.serviceplanner.dtos.response.GroupDtoSmall;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Group;

public class GroupDtoFactory {

	public static GroupDto toGroupDto(Group group, DtoType type) {
		if (group != null) {
			if (type == null || type == DtoType.large || type == DtoType.medium) {
				return new GroupDtoLarge(group.getId(), group.getName(), group.getType());
			} else if (type == DtoType.extended) {
				List<Category> categories = new ArrayList<>(group.getCategories());
				return new GroupDtoExtended(group.getId(), group.getName(),
						CategoryDtoFactory.toCategoryDto(categories, DtoType.extended));
			} else {
				return new GroupDtoSmall(group.getId(), group.getName());
			}
		}
		return null;
	}

	public static List<GroupDto> toGroupDto(List<Group> groups, DtoType type) {
		if (groups != null) {
			return groups.stream().map(v -> GroupDtoFactory.toGroupDto(v, type)).collect(Collectors.toList());
		}
		return null;
	}
}
