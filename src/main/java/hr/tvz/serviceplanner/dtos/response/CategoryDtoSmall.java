package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.CategoryDto;

public class CategoryDtoSmall implements CategoryDto {

	private Long id;
	private String name;

	public CategoryDtoSmall() {
		super();
	}

	public CategoryDtoSmall(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
