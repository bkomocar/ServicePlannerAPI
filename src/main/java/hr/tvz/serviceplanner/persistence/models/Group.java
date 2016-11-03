package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

import hr.tvz.serviceplanner.enums.GroupType;

@Entity
@Table(name = "groups")
public class Group implements Serializable, Comparable<Group> {

	private static final long serialVersionUID = 5541135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private GroupType type;

	@SortNatural
	@OneToMany(cascade= CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "group")
	private SortedSet<Category> categories = new TreeSet<>();;

	@ManyToOne
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;
	
	@SortNatural
	@OneToMany(cascade= CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "group")
	private SortedSet<Purchase> purchases = new TreeSet<>();

	@SortNatural
	@OneToMany(cascade= CascadeType.REFRESH,fetch = FetchType.LAZY, mappedBy = "group")
	private SortedSet<Event> events = new TreeSet<>();

	public Group() {
		super();
	}

	public Group(String name, GroupType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public Group(Long id, String name, GroupType type, SortedSet<Category> categories, Venue venue) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.categories = categories;
		this.venue = venue;
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

	public SortedSet<Category> getCategories() {
		return categories;
	}

	public void setCategories(SortedSet<Category> categories) {
		this.categories = categories;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Group o) {
		Long id = o.getId();

		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}

}
