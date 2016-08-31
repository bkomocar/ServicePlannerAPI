package hr.tvz.serviceplanner.persistence.models;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.Serializable;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

import hr.tvz.serviceplanner.enums.UserType;

@Entity
@Table(name = "users")
public class User implements Serializable, Comparable<User> {

	// The entity fields (private)

	private static final long serialVersionUID = 121234535L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String email;

	@NotNull
	@Column(columnDefinition = "varchar(20)", unique = true)
	private String name;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String password;

	@Column
	private Date lastPasswordReset;

	@Column
	private String authorities;

	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "usersVenues", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "venueId") })
	private SortedSet<Venue> venues = new TreeSet<>();
	
	// Public methods
	
	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(Long id, String email, String username, String password, Date lastPasswordReset, String authorities) {
		this.id = id;
		this.email = email;
		this.name = username;
		this.password = password;
		this.lastPasswordReset = lastPasswordReset;
		this.authorities = authorities;
	}
	
	public User(String email, String username, String password) {
		this.email = email;
		this.name = username;
		this.password = password;
		this.lastPasswordReset = new Date();
		this.authorities = UserType.USER.name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public SortedSet<Venue> getVenues() {
		return venues;
	}

	public void setVenues(SortedSet<Venue> venues) {
		this.venues = venues;
	}

	@Override
	public int compareTo(User o) {
		Long id = o.getId();

		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}
		
}
