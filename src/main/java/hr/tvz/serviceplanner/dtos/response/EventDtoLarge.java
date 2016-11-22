package hr.tvz.serviceplanner.dtos.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.tvz.serviceplanner.dtos.EmployeeDto;
import hr.tvz.serviceplanner.dtos.EventDto;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.PurchaseDto;

public class EventDtoLarge implements EventDto {

	private Long id;
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime;
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;
	private ProductDto product;
	private EmployeeDto employee;
	private List<PurchaseDto> purchases = new ArrayList<>();

	public EventDtoLarge(Long id, Date startTime, Date endTime, ProductDto product, EmployeeDto employee,
			List<PurchaseDto> purchases) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.product = product;
		this.employee = employee;
		this.purchases = purchases;
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

	public List<PurchaseDto> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseDto> purchases) {
		this.purchases = purchases;
	}

}
