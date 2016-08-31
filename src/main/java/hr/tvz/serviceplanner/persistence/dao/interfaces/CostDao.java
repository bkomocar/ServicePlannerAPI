package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Cost;

public interface CostDao extends Operations<Cost> {

	public Long createCost(Long id, Cost cost);

	public boolean updateCost(Long id, Cost cost);

	public boolean deleteCost(Long id);
}
