package hr.tvz.serviceplanner.dtos.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class UpdateEventDto {

	Long id;
	Employee employee;
	Product product;
	private Date startTime;
	private Date endTime;
	public List<Purchase> purchases = new ArrayList<>();

	public UpdateEventDto() {
		super();
	}

	public UpdateEventDto(Long id, Employee employee, Product product, Date startTime, Date endTime,
			List<Purchase> purchases) {
		super();
		this.id = id;
		this.employee = employee;
		this.product = product;
		this.startTime = startTime;
		this.endTime = endTime;
		this.purchases = purchases;
	}

	public static Event toEvent(UpdateEventDto model) {
		if (model != null) {
			SortedSet<Purchase> purchaseSet = new TreeSet<>(model.purchases);
			return new Event(model.id, model.product, model.employee, model.startTime, model.endTime, purchaseSet);
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
