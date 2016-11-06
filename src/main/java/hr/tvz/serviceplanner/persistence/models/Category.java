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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "categories")
public class Category implements Serializable, Comparable<Category> {

	private static final long serialVersionUID = 5221135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String name;

	@Column(columnDefinition = "varchar(500)")
	private String description;

	@Column(columnDefinition = "varchar(20)")
	private String color;

	@SortNatural
	@OneToMany(cascade= CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "category")
	private SortedSet<Product> products;

	@ManyToOne
	@JoinColumn(name = "groupId", nullable = false)
	private Group group;

	@SortNatural
	@ManyToMany(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "categoriesEmployees", joinColumns = { @JoinColumn(name = "categoryId") }, inverseJoinColumns = {
			@JoinColumn(name = "employeeId") })
	private SortedSet<Employee> employees;

	public Category() {
		super();
	}

	public Category(String name, String description, String color) {
		super();
		this.name = name;
		this.description = description;
		this.color = color;
	}

	public Category(Long id, String name, String description, String color, SortedSet<Product> products, Group group) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.products = products;
		this.group = group;
	}

	public Category(Long id, String name, String description, String color, SortedSet<Product> products, Group group,
			SortedSet<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.products = products;
		this.group = group;
		this.employees = employees;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public SortedSet<Product> getProducts() {
		return products;
	}

	public void setProducts(SortedSet<Product> products) {
		this.products = products;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public SortedSet<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(SortedSet<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int compareTo(Category o) {
		if (this.id != null && o.getId() != null) {
			Long id = o.getId();
			if (id.equals(this.id))
				return 0;
			else if (id.longValue() > this.id.longValue())
				return 1;
			else
				return -1;
		}
		return -1;
	}

}
