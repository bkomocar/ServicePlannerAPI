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

	private Long employeeId;

	@NotNull(message = "Product id is a required field")
	private Long productId;

	@NotNull(message = "Start Time is a required field")
	private Date startTime;

	@NotNull(message = "End Time is a required field")
	private Date endTime;

	public List<Long> purchaseIds = new ArrayList<>();

	public CreateEventViewModel() {
		super();
	}

	public CreateEventViewModel(Long employeeId, Long productId, Date startTime, Date endTime, List<Long> purchaseIds) {
		super();
		this.employeeId = employeeId;
		this.productId = productId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.purchaseIds = purchaseIds;
	}

	public static Event toEvent(CreateEventViewModel model) {
		if (model != null) {

			SortedSet<Purchase> purchases = new TreeSet<>();
			for (Long id : model.purchaseIds) {
				purchases.add(new Purchase(id));
			}

			Event event = new Event(new Product(model.productId), model.startTime, model.endTime, purchases);

			if (model.employeeId != null) {
				event.setEmployee(new Employee(model.employeeId));
			}

			return event;
		}
		return null;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
