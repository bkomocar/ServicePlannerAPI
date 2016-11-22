package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.request.CreateCostDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCostDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CostDao;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.CostService;

@Service
public class CostServiceImpl extends AbstractService<Cost> implements CostService {

	@Autowired
	private CostDao dao;

	@Override
	public IdDto createCost(Long id, CreateCostDto model) {
		Long costId = dao.createCost(id, CreateCostDto.toCost(model));
		if (costId != null) {
			return new IdDto(costId);
		}
		return null;
	}

	@Override
	public boolean updateCost(Long id, UpdateCostDto model) {
		if (model != null) {
			return dao.updateCost(id, UpdateCostDto.toCost(model));
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
