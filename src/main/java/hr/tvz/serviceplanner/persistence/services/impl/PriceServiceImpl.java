package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PriceDao;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.PriceService;
import hr.tvz.serviceplanner.viewmodels.request.CreatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

@Service
public class PriceServiceImpl extends AbstractService<Price> implements PriceService {

	@Autowired
	private PriceDao dao;

	@Override
	public IdViewModel createPrice(Long id, CreatePriceViewModel model) {
		Long priceId = dao.createPrice(id, CreatePriceViewModel.toPrice(model));
		if (priceId != null) {
			return new IdViewModel(priceId);
		}
		return null;
	}

	@Override
	public boolean updatePrice(Long id, UpdatePriceViewModel model) {
		if (model != null) {
			return dao.updatePrice(id, UpdatePriceViewModel.toPrice(model));
		}
		return false;
	}

	@Override
	protected Operations<Price> getDao() {
		return dao;
	}

	@Override
	public boolean deletePrice(Long id) {
		return dao.deletePrice(id);
	}

}
