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
@Table(name = "products")
public class Product implements Serializable {

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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private SortedSet<Price> prices;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;
	
}
