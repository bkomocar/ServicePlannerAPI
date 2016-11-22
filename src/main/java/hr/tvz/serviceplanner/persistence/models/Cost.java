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
public class Cost implements Serializable, Comparable<Cost> {

	private static final long serialVersionUID = 141135L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(columnDefinition = "varchar(5)")
	private String currency;

	@NotNull
	@Column
	private Long valueInSmallestCurrency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priceId")
	private Price price;

	public Cost() {
		super();
	}

	public Cost(Long id, String currency, Long valueInSmallestCurrency) {
		super();
		this.id = id;
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public Cost(String currency, Long valueInSmallestCurrency) {
		super();
		this.currency = currency;
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getValueInSmallestCurrency() {
		return valueInSmallestCurrency;
	}

	public void setValueInSmallestCurrency(Long valueInSmallestCurrency) {
		this.valueInSmallestCurrency = valueInSmallestCurrency;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public int compareTo(Cost o) {
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
