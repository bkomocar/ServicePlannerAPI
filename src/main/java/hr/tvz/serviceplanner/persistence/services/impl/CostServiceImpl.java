package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CostDao;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CostService;
import hr.tvz.serviceplanner.viewmodels.request.CreateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class CostServiceImpl extends AbstractService<Cost> implements CostService {

	@Autowired
	private CostDao dao;

	@Override
	public IdViewModel createCost(Long id, CreateCostViewModel model) {
		Long costId = dao.createCost(id, CreateCostViewModel.toCost(model));
		if (costId != null) {
			return new IdViewModel(costId);
		}
		return null;
	}

	@Override
	public boolean updateCost(Long id, UpdateCostViewModel model) {
		if (model != null) {
			return dao.updateCost(id, UpdateCostViewModel.toCost(model));
		}
		return false;
	}

	@Override
	public boolean deleteCost(Long id) {
		return dao.deleteCost(id);
	}

	@Override
	protected Operations<Cost> getDao() {
		return dao;
	}

}
