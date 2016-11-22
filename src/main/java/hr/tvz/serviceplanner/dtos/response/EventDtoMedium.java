package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.serviceplanner.dtos.CustomerDto;

public class EventDtoMedium {

	Long eventId;
	Long employeeId;
	Long productId;
	String categoryColor;
	String productShortName;
	String startTime;
	String endTime;
	private List<CustomerDto> customers = new ArrayList<>();

	public EventDtoMedium(Long eventId) {
		this.eventId = eventId;
	}

	public EventDtoMedium(Long eventId, Long employeeId, Long productId, String categoryColor, String productShortName,
			String startTime, String endTime, List<CustomerDto> customers) {
		this.eventId = eventId;
		this.productId = productId;
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

	public List<CustomerDto> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDto> customers) {
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
