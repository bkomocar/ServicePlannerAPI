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
public class Category implements Serializable {

	private static final long serialVersionUID = 5221135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@NotNull
	@Column(columnDefinition = "varchar(255)")
	private String name;
	
	@Column(columnDefinition = "varchar(500)")
	private String description;
	
	@Column(columnDefinition = "varchar(20)")
	private String color;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private SortedSet<Product> products;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId", nullable = false)
	private Group group;
	
	@SortNatural
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "categoriesEmployees", joinColumns = { @JoinColumn(name = "categoryId") }, inverseJoinColumns = { @JoinColumn(name = "employeeId") })
	private SortedSet<Group> categoriesEmployees;
	
}
