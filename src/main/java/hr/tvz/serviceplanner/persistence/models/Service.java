package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import hr.tvz.serviceplanner.enums.ServiceType;

@Entity
@Table(name = "services")
public class Service implements Serializable {

	private static final long serialVersionUID = 5541135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "varchar(50)")
	private String name;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private ServiceType type;
	
	@SortNatural
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service")
	private Set<Category> categories = new HashSet<>();;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venueId", nullable = false)
	private Venue venue;
}
