package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "employees")
public class Employee implements Serializable, Comparable<Employee> {

	private static final long serialVersionUID = 2332235L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(20)")
	private String username;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String firstName;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String lastName;

	@Column(columnDefinition = "varchar(20)")
	private String color;

	@Column(columnDefinition = "varchar(255)")
	private String email;

	@Column(columnDefinition = "varchar(20)")
	private String phone;

	@Column(columnDefinition = "varchar(500)")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;

	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "categoriesEmployees", joinColumns = { @JoinColumn(name = "employeeId") }, inverseJoinColumns = {
			@JoinColumn(name = "categoryId") })
	private SortedSet<Category> categoriesEmployees;

	public Employee() {
		super();
	}

	public Employee(Long id) {
		super();
		this.id = id;
	}

	public Employee(String username, String firstName, String lastName, String color, String email, String phone,
			String comment) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.color = color;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public Employee(Long id, String username, String firstName, String lastName, String color, String email,
			String phone, String comment) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.color = color;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public SortedSet<Category> getCategoriesEmployees() {
		return categoriesEmployees;
	}

	public void setCategoriesEmployees(SortedSet<Category> categoriesEmployees) {
		this.categoriesEmployees = categoriesEmployees;
	}

	@Override
	public int compareTo(Employee o) {
		Long id = o.getId();
		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}

}
