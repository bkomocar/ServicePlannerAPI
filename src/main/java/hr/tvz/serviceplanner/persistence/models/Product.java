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
@Table(name = "products")
public class Product implements Serializable, Comparable<Product> {

	private static final long serialVersionUID = 141135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column
	@ColumnDefault("1")
	private Integer maxCustomers;

	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String name;

	@NotNull
	@Column(columnDefinition = "varchar(10)")
	private String shortName;

	@Column(columnDefinition = "varchar(500)")
	private String description;

	@SortNatural
	@Cascade(value = CascadeType.DELETE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private SortedSet<Price> prices;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;

	public Product() {
		super();
	}

	public Product(Integer maxCustomers, String name, String shortName, String description) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
	}

	public Product(Integer maxCustomers, String name, String shortName, String description, SortedSet<Price> prices) {
		super();
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.prices = prices;
	}

	public Product(Long id, Integer maxCustomers, String name, String shortName, String description,
			SortedSet<Price> prices, Category category) {
		super();
		this.id = id;
		this.maxCustomers = maxCustomers;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.prices = prices;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxCustomers() {
		return maxCustomers;
	}

	public void setMaxCustomers(Integer maxCustomers) {
		this.maxCustomers = maxCustomers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SortedSet<Price> getPrices() {
		return prices;
	}

	public void setPrices(SortedSet<Price> prices) {
		this.prices = prices;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int compareTo(Product o) {
		Long id = o.getId();

		if (id == this.id)
			return 0;
		else if (id > this.id)
			return 1;
		else
			return -1;
	}

}
