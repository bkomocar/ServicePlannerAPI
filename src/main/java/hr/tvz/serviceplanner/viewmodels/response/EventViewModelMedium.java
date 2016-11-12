package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.List;
import hr.tvz.serviceplanner.viewmodels.CustomerViewModel;


public class EventViewModelMedium {

	Long eventId;
	Long employeeId;
	String categoryColor;
	String productShortName;
	String startTime;
	String endTime;
	private List<CustomerViewModel> customers = new ArrayList<>();
	
	
	public EventViewModelMedium(Long eventId, Long employeeId, String categoryColor, String productShortName,
			String startTime, String endTime, List<CustomerViewModel> customers) {
		super();
		this.eventId = eventId;
		this.employeeId = employeeId;
		this.categoryColor = categoryColor;
		this.productShortName = productShortName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.customers = customers;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public List<CustomerViewModel> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerViewModel> customers) {
		this.customers = customers;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
