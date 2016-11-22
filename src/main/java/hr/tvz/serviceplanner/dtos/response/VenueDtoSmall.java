package hr.tvz.serviceplanner.dtos.response;

import hr.tvz.serviceplanner.dtos.VenueDto;
import hr.tvz.serviceplanner.enums.VenueType;

public class VenueDtoSmall implements VenueDto {

	private Long id;
	private String name;
	private VenueType type;

	public VenueDtoSmall() {
		super();
	}

	public VenueDtoSmall(Long id, String name, VenueType type) {
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

	public VenueType getType() {
		return type;
	}

	public void setType(VenueType type) {
		this.type = type;
	}

}
