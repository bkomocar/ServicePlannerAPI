package hr.tvz.serviceplanner.viewmodels.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.EventViewModel;
import hr.tvz.serviceplanner.viewmodels.ProductViewModel;
import hr.tvz.serviceplanner.viewmodels.PurchaseViewModel;

public class EventViewModelLarge implements EventViewModel {

	private Long id;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;
	private ProductViewModel product;
	private EmployeeViewModel employee;
	private List<PurchaseViewModel> purchases = new ArrayList<>();

	public EventViewModelLarge(Long id, Date startTime, Date endTime, ProductViewModel product,
			EmployeeViewModel employee, List<PurchaseViewModel> purchases) {
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

	public List<PurchaseViewModel> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseViewModel> purchases) {
		this.purchases = purchases;
	}

}
