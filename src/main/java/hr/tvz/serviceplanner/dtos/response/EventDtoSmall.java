package hr.tvz.serviceplanner.dtos.response;

import java.util.Date;

import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.EventDto;
import hr.tvz.serviceplanner.dtos.ProductDto;

public class EventDtoSmall implements EventDto {

	private Long id;
	private Date startTime;
	private Date endTime;
	private ProductDto product;
	private EmployeeDto employee;

	public EventDtoSmall(Long id, Date startTime, Date endTime, ProductDto product, EmployeeDto employee) {
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

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

}
