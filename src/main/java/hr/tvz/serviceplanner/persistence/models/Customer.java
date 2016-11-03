package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "customers")
public class Customer implements Serializable, Comparable<Customer> {

	private static final long serialVersionUID = 2311135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@ColumnDefault("0")
	private boolean deleted;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String firstName;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String lastName;

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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private SortedSet<Purchase> purchases;

	public Customer() {
		super();
	}

	public Customer(Long id) {
		super();
		this.id = id;
	}
	

	public Customer(Long id, boolean deleted, String firstName, String lastName, String email, String phone,
			String comment, Venue venue, SortedSet<Purchase> purchases) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
		this.venue = venue;
		this.purchases = purchases;
	}

	public Customer(Long id, String firstName, String lastName, String email, String phone, String comment,
			Venue venue) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
		this.venue = venue;
	}

	public Customer(String firstName, String lastName, String email, String phone, String comment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public SortedSet<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(SortedSet<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public int compareTo(Customer o) {
		Long id = o.getId();

		if (id.equals(this.id))
			return 0;
		else if (id.longValue() > this.id.longValue())
			return 1;
		else
			return -1;
	}
}
