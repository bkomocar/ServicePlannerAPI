package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.SortedSet;

import javax.persistence.CascadeType;
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
@Table(name = "groups")
public class Group implements Serializable {
	
	private static final long serialVersionUID = 965885L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="employeeId")
	private Employee employee;
	
	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "groupsPurchases", joinColumns = { @JoinColumn(name = "groupId") }, inverseJoinColumns = { @JoinColumn(name = "purchaseId") })
	private SortedSet<Purchase> purchases;
}
