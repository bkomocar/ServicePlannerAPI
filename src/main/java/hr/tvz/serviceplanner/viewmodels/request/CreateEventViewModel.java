package hr.tvz.serviceplanner.viewmodels.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public class CreateEventViewModel {

	@NotNull(message = "Employee is a required field")
	Employee employee;
	@NotNull(message = "Product is a required field")
	Product product;
	@NotNull(message = "Start Time is a required field")
	private Date startTime;
	@NotNull(message = "End Time is a required field")
	private Date endTime;
	public List<Purchase> purchases = new ArrayList<>();

	public CreateEventViewModel() {
		super();
	}

	public CreateEventViewModel(Employee employee, Product product, Date startTime, Date endTime,
			List<Purchase> purchases) {
		super();
		this.employee = employee;
		this.product = product;
		this.startTime = startTime;
		this.endTime = endTime;
		this.purchases = purchases;
	}

	public static Event toEvent(CreateEventViewModel model) {
		if (model != null) {
			SortedSet<Purchase> purchaseSet = new TreeSet<>(model.purchases);
			return new Event(model.product, model.employee, model.startTime, model.endTime, purchaseSet);
		}
		return null;
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
