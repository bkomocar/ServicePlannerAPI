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
public class Employee implements Serializable {

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;
	
	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "categoriesEmployees", joinColumns = { @JoinColumn(name = "employeeId") }, inverseJoinColumns = { @JoinColumn(name = "categoryId") })
	private SortedSet<Group> categoriesEmployees;	
}
