package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.GroupDto;

public class GroupDtoExtended implements GroupDto {

	private Long id;
	private String name;
	private List<CategoryDto> categories = new ArrayList<>();

	public GroupDtoExtended() {
		super();
	}

	public GroupDtoExtended(Long id, String name, List<CategoryDto> categories) {
		super();
		this.id = id;
		this.name = name;
		this.categories = categories;
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

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

}
