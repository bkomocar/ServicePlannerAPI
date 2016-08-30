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

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {

	private static final long serialVersionUID = 4125135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "varchar(5)")
	private String currency;
	
	@Column
	private Long valueInSmallestCurrency;
		
	@Column(columnDefinition="DATETIME")
	private Date startTime;
	
	@Column(columnDefinition="DATETIME")
	private Date endTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="priceId")
	private Price price;
	
	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "groupsPurchases", joinColumns = { @JoinColumn(name = "purchaseId") }, inverseJoinColumns = { @JoinColumn(name = "groupId") })
	private SortedSet<Group> groups;
}
