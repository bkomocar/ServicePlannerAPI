package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.request.CreatePriceDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePriceDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PriceDao;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.PriceService;

@Service
public class PriceServiceImpl extends AbstractService<Price> implements PriceService {

	@Autowired
	private PriceDao dao;

	@Override
	public IdDto createPrice(Long id, CreatePriceDto model) {
		Long priceId = dao.createPrice(id, CreatePriceDto.toPrice(model));
		if (priceId != null) {
			return new IdDto(priceId);
		}
		return null;
	}

	@Override
	public boolean updatePrice(Long id, UpdatePriceDto model) {
		if (model != null) {
			return dao.updatePrice(id, UpdatePriceDto.toPrice(model));
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
