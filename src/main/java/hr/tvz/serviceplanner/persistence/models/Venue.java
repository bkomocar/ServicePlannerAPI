package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.SortedSet;

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
public class Venue implements Serializable {
	
	private static final long serialVersionUID = 1232435L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String name;
	
	@Column(columnDefinition = "varchar(255)")
	private String description;
	
	@Column
	private Time openTime;
	
	@Column
	private Time closeTime;

	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "usersVenues", joinColumns = { @JoinColumn(name = "venueId") }, inverseJoinColumns = { @JoinColumn(name = "userId") })
	private SortedSet<User> users;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Employee> employees;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Customer> customers;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Service> services;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Purchase> purchases;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
	private SortedSet<Group> groups;
	
	
}
