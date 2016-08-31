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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "prices")
public class Price implements Serializable, Comparable<Price> {

	private static final long serialVersionUID = 858135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String name;

	@Column(columnDefinition = "varchar(500)")
	private String description;

	@Column
	@ColumnDefault("0")
	private Long durationInMin;

	@Column
	@ColumnDefault("1")
	private Long itemsCount;

	@SortNatural
	@Cascade(value = CascadeType.DELETE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "price")
	private SortedSet<Cost> costs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", nullable = false)
	private Product product;

	public Price() {
		super();
	}

	public Price(String name, String description, Long durationInMin, Long itemsCount) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
	}

	public Price(String name, String description, Long durationInMin, Long itemsCount, Product product) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.product = product;
	}

	public Price(String name, String description, Long durationInMin, Long itemsCount, SortedSet<Cost> costs) {
		super();
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
	}

	public Price(Long id, String name, String description, Long durationInMin, Long itemsCount, SortedSet<Cost> costs,
			Product product) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.durationInMin = durationInMin;
		this.itemsCount = itemsCount;
		this.costs = costs;
		this.product = product;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDurationInMin() {
		return durationInMin;
	}

	public void setDurationInMin(Long durationInMin) {
		this.durationInMin = durationInMin;
	}

	public Long getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(Long itemsCount) {
		this.itemsCount = itemsCount;
	}

	public SortedSet<Cost> getCosts() {
		return costs;
	}

	public void setCosts(SortedSet<Cost> costs) {
		this.costs = costs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(Price o) {
		Long id = o.getId();

		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}
}
