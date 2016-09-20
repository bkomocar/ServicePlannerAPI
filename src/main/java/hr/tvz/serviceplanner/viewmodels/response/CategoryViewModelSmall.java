package hr.tvz.serviceplanner.viewmodels.response;

import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;

public class CategoryViewModelSmall implements CategoryViewModel {

	private Long id;
	private String name;

	public CategoryViewModelSmall() {
		super();
	}

	public CategoryViewModelSmall(Long id, String name) {
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
