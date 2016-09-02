package hr.tvz.serviceplanner.viewmodels.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Product;

public class CreateEventViewModel {

	@NotNull
	private Long employeeId;

	@NotNull
	private Long productId;

	@NotNull
	private Date startTime;

	@NotNull
	private Date endTime;

	public CreateEventViewModel() {
		super();
	}

	public CreateEventViewModel(Long employeeId, Long productId, Date startTime, Date endTime) {
		super();
		this.employeeId = employeeId;
		this.productId = productId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static Event toEvent(CreateEventViewModel model) {
		if (model != null) {
			return new Event(new Product(model.productId), new Employee(model.employeeId), model.startTime,
					model.endTime);
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
