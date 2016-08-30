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

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "prices")
public class Price implements Serializable {

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
	private Long durationInMin;

	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "price")
	private SortedSet<Cost> costs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", nullable = false)
	private Product product;
}
