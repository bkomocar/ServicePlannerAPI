package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "costs")
public class Cost implements Serializable {

	private static final long serialVersionUID = 141135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(columnDefinition = "varchar(5)")
	private String currency;
	
	@Column
	private Long valueInSmallestCurrency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priceId", nullable = false)
	private Price price;
}
