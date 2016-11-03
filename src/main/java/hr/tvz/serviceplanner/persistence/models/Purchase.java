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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable, Comparable<Purchase> {

	private static final long serialVersionUID = 4125135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(5)")
	private String currency;

	@NotNull
	@Column
	private Long valueInSmallestCurrency;

	@NotNull
	@Column(columnDefinition = "DATETIME")
	private Date purchaseDate;

	@Column(columnDefinition = "DATETIME")
	private Date paymentDate;	

	@ManyToOne(optional = false)
	@JoinColumn(name = "groupId", nullable = false)
	private Group group;

	@ManyToOne(optional = false)
	@JoinColumn(name = "productId", nullable = false)
	private Product product;

	@ManyToOne(optional = true)
	@JoinColumn(name = "customerId", nullable = true)
	private Customer customer;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "priceId")
	private Price price;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "venueId")
	private Venue venue;

	@SortNatural
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "eventsPurchases", joinColumns = { @JoinColumn(name = "purchaseId") }, inverseJoinColumns = {
			@JoinColumn(name = "eventId") })
	private SortedSet<Event> events;

	@Column
	@ColumnDefault("0")
	private boolean closed;

	private Long totalDurationInMinutes;

	public Purchase() {
		super();
	}

	public Purchase(Long id) {
		super();
		this.id = id;
	}

	public Purchase(Long id, String currency, Long valueInSmallestCurrency, Date purchaseDate, Date paymentDate, Venue venue,
			Group group, Product product, Customer customer, Price price, SortedSet<Event> events, boolean closed,
			Long totalDurationInMinutes) {
		super();
		this.id = id;
		this.currency = currency;
		this.venue = venue;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
		this.group = group;
		this.product = product;
		this.customer = customer;
		this.price = price;
		this.events = events;
		this.closed = closed;
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public Purchase(String currency, Long valueInSmallestCurrency,Venue venue, Date purchaseDate, Date paymentDate) {
		super();
		this.currency = currency;
		this.venue = venue;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
	}

	public Purchase(String currency, Long valueInSmallestCurrency,Venue venue, Date purchaseDate, Date paymentDate, Product product,
			Customer customer, Price price) {
		super();
		this.currency = currency;
		this.venue = venue;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
		this.product = product;
		this.customer = customer;
		this.price = price;
	}

	public Purchase(String currency, Long valueInSmallestCurrency,Venue venue, Date purchaseDate, Date paymentDate, Product product,
			Price price) {
		super();
		this.currency = currency;
		this.venue = venue;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
		this.purchaseDate = purchaseDate;
		this.paymentDate = paymentDate;
		this.product = product;
		this.price = price;
	}
	
	

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getValueInSmallestCurrency() {
		return valueInSmallestCurrency;
	}

	public void setValueInSmallestCurrency(Long valueInSmallestCurrency) {
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public SortedSet<Event> getEvents() {
		return events;
	}

	public void setEvents(SortedSet<Event> events) {
		this.events = events;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Long getTotalDurationInMinutes() {
		return totalDurationInMinutes;
	}

	public void setTotalDurationInMinutes(Long totalDurationInMinutes) {
		this.totalDurationInMinutes = totalDurationInMinutes;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public int compareTo(Purchase o) {
		Long id = o.getId();
		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}
}
