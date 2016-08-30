package hr.tvz.serviceplanner.persistence.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
	
	private static final long serialVersionUID = 2325135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="DATETIME")
	private Date paymentDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="priceId")
	private Purchase purchaseId;
}
