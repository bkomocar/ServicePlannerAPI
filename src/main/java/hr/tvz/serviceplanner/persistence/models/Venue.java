package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "venues")
public class Venue implements Serializable, Comparable<Venue> {

	private static final long serialVersionUID = 1232435L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String name;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String owner;

	@Column(columnDefinition = "varchar(500)")
	private String description;

	@Column
	private Time openTime;

	@Column
	private Time closeTime;

	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "usersVenues", joinColumns = { @JoinColumn(name = "venueId") }, inverseJoinColumns = {
			@JoinColumn(name = "userId") })
	private SortedSet<User> users;

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Employee> employees = new TreeSet<>();

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Customer> customers = new TreeSet<>();

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Group> groups = new TreeSet<>();

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Purchase> purchases = new TreeSet<>();

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Event> events = new TreeSet<>();
	
	public Venue() {
		super();
	}

	public Venue(Long id) {
		super();
		this.id = id;
	}

	public Venue(String name, String description, String owner, Time openTime, Time closeTime) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public Venue(String name, String description, String owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public SortedSet<User> getUsers() {
		return users;
	}

	public void setUsers(SortedSet<User> users) {
		this.users = users;
	}

	public SortedSet<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(SortedSet<Employee> employees) {
		this.employees = employees;
	}

	public SortedSet<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(SortedSet<Customer> customers) {
		this.customers = customers;
	}

	public SortedSet<Group> getServices() {
		return groups;
	}

	public void setServices(SortedSet<Group> groups) {
		this.groups = groups;
	}

	public SortedSet<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(SortedSet<Purchase> purchases) {
		this.purchases = purchases;
	}

	public SortedSet<Event> getGroups() {
		return events;
	}

	public void setGroups(SortedSet<Event> events) {
		this.events = events;
	}

	@Override
	public int compareTo(Venue o) {
		Long id = o.getId();

		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}

}
