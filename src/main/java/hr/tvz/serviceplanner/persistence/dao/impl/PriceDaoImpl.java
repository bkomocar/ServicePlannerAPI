package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PriceDao;
import hr.tvz.serviceplanner.persistence.models.Price;

@Repository
public class PriceDaoImpl extends AbstractHibernateDao<Price> implements PriceDao {

	public PriceDaoImpl() {
		super();
		setClazz(Price.class);
	}

	@Override
	public Long createPrice(Long id, Price price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePrice(Long id, Price price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePrice(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
