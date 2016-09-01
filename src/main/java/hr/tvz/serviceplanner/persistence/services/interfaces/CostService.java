package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.viewmodels.request.CreateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdateCostViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface CostService extends Operations<Cost> {

	public IdViewModel createCost(Long id, CreateCostViewModel model);

	public boolean updateCost(Long id, UpdateCostViewModel model);

	public boolean deleteCost(Long id);
}
