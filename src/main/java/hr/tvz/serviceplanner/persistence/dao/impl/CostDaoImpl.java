package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.CostDao;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;

@Repository
public class CostDaoImpl extends AbstractHibernateDao<Cost> implements CostDao {

	public CostDaoImpl() {
		super();
		setClazz(Cost.class);
	}

	@Override
	public Long createCost(Long id, Cost cost) {
		Price price = getCurrentSession().get(Price.class, id);
		if (price != null) {
			cost.setPrice(price);
			create(cost);
			return cost.getId();
		}
		return null;
	}

	@Override
	public boolean updateCost(Long id, Cost cost) {
		Cost originalCost = findOne(id);
		if (originalCost != null) {
			if (cost.getCurrency() != null) {
				originalCost.setCurrency(cost.getCurrency());
			}
			if (cost.getValueInSmallestCurrency() != null) {
				originalCost.setValueInSmallestCurrency(cost.getValueInSmallestCurrency());
			}
			update(originalCost);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCost(Long id) {
		Cost cost = findOne(id);
		Price price = cost.getPrice();
		if (price.getCosts() != null && price.getCosts().size() > 1) {
			deleteById(id);
			return true;
		}
		return false;
	}

}
