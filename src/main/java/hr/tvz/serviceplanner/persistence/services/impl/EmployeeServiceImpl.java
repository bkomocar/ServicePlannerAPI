package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EmployeeDao;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.EmployeeService;
import hr.tvz.serviceplanner.viewmodels.request.CreateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateEmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.EmployeeViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Override
	public EmployeeViewModel getEmployee(Long id) {
		Employee employee = dao.findOne(id);
		if (employee != null) {
			return EmployeeViewModel.fromEmployee(employee);
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

}
