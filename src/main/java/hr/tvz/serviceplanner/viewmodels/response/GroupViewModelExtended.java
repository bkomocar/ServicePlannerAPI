package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.viewmodels.CategoryViewModel;
import hr.tvz.serviceplanner.viewmodels.GroupViewModel;

public class GroupViewModelExtended implements GroupViewModel {

	private Long id;
	private String name;
	private List<CategoryViewModel> categories = new ArrayList<>();

	public GroupViewModelExtended() {
		super();
	}

	public GroupViewModelExtended(Long id, String name, List<CategoryViewModel> categories) {
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

	public List<CategoryViewModel> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryViewModel> categories) {
		this.categories = categories;
	}

}
