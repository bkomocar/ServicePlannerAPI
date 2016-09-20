package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EmployeeDao;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.EmployeeService;
import hr.tvz.serviceplanner.viewmodels.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.EmployeeViewModelFactory;
import hr.tvz.serviceplanner.viewmodels.ViewModelType;
import hr.tvz.serviceplanner.viewmodels.request.CreateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Override
	public EmployeeViewModel getEmployee(Long id, ViewModelType type) {
		Employee employee = dao.findOne(id);
		if (employee != null) {
			return EmployeeViewModelFactory.toEmployeeViewModel(employee, type);
		}
		return null;
	}

	@Override
	public IdViewModel createEmployee(Long id, CreateEmployeeViewModel model) {
		Long groupId = dao.createEmployee(id, CreateEmployeeViewModel.toEmployee(model));
		if (groupId != null) {
			return new IdViewModel(groupId);
		}
		return null;
	}

	@Override
	public boolean updateEmployee(Long id, UpdateEmployeeViewModel model) {
		if (model != null) {
			return dao.updateEmployee(id, UpdateEmployeeViewModel.toEmployee(model));
		}
		return false;
	}

	@Override
	protected Operations<Employee> getDao() {
		return dao;
	}

	@Override
	public List<EmployeeViewModel> getEmployeesForVenue(Long venueId, ViewModelType type) {
		SortedSet<Employee> employees = dao.getEmployeesForVenue(venueId);
		if (employees != null) {
			return EmployeeViewModelFactory.toEmployeeViewModel(new ArrayList<Employee>(employees), type);
		}
		return null;
	}

}
