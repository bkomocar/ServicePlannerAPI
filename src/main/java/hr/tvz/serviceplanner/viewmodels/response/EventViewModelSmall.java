package hr.tvz.serviceplanner.viewmodels.response;

import java.util.Date;

import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.EventViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;

public class EventViewModelSmall implements EventViewModel {

	private Long id;
	private Date startTime;
	private Date endTime;
	private ProductViewModel product;
	private EmployeeViewModel employee;

	public EventViewModelSmall(Long id, Date startTime, Date endTime, ProductViewModel product,
			EmployeeViewModel employee) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.product = product;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ProductViewModel getProduct() {
		return product;
	}

	public void setProduct(ProductViewModel product) {
		this.product = product;
	}

	public EmployeeViewModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeViewModel employee) {
		this.employee = employee;
	}

}
