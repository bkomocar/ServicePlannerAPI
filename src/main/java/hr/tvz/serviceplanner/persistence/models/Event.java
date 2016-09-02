package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.Date;
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

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "events")
public class Event implements Serializable, Comparable<Event> {

	private static final long serialVersionUID = 965885L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;

	@ManyToOne(optional = false)
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToOne(optional = false)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@Column(columnDefinition = "DATETIME")
	private Date startTime;

	@Column(columnDefinition = "DATETIME")
	private Date endTime;

	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "eventsPurchases", joinColumns = { @JoinColumn(name = "eventId") }, inverseJoinColumns = {
			@JoinColumn(name = "purchaseId") })
	private SortedSet<Purchase> purchases;

	public Event() {
		super();
	}

	public Event(Product product, Employee employee, Date startTime, Date endTime) {
		super();
		this.product = product;
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Event(Employee employee, Date startTime, Date endTime) {
		super();
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Event(Long id, Venue venue, Product product, Employee employee, SortedSet<Purchase> purchases) {
		super();
		this.id = id;
		this.venue = venue;
		this.product = product;
		this.employee = employee;
		this.purchases = purchases;
	}

	public Event(Venue venue, Product product, Employee employee) {
		super();
		this.venue = venue;
		this.product = product;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SortedSet<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(SortedSet<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Event o) {
		Long id = o.getId();
		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}

}
