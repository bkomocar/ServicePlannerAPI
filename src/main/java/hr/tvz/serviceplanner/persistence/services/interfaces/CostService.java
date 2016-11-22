package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.dtos.request.CreateCostDto;
import hr.tvz.serviceplanner.dtos.request.UpdateCostDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Cost;

public interface CostService extends Operations<Cost> {

	public IdDto createCost(Long id, CreateCostDto model);

	public boolean updateCost(Long id, UpdateCostDto model);

	public boolean deleteCost(Long id);
}
