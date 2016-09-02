package hr.tvz.serviceplanner.viewmodels.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;

public class EventViewModelSmall {

	private Long id;
	private Date startTime;
	private Date endTime;
	private ProductViewModelSmall product;
	private EmployeeViewModel employee;

	public EventViewModelSmall(Long id, Date startTime, Date endTime, ProductViewModelSmall product,
			EmployeeViewModel employee) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.product = product;
		this.employee = employee;
	}

	public static EventViewModelSmall fromEvent(Event event) {
		if (event != null) {
			return new EventViewModelSmall(event.getId(), event.getStartTime(), event.getEndTime(),
					ProductViewModelSmall.fromProduct(event.getProduct()),
					EmployeeViewModel.toEmployeeViewModel(event.getEmployee(), ViewModelType.medium));
		}
		return null;
	}

	public static List<EventViewModelSmall> fromEvent(List<Event> prices) {
		if (prices != null) {
			return prices.stream().map(u -> EventViewModelSmall.fromEvent(u)).collect(Collectors.toList());
		}
		return null;
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

	public ProductViewModelSmall getProduct() {
		return product;
	}

	public void setProduct(ProductViewModelSmall product) {
		this.product = product;
	}

	public EmployeeViewModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeViewModel employee) {
		this.employee = employee;
	}

}
