package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.dtos.request.CreatePriceDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePriceDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Price;

public interface PriceService extends Operations<Price> {

	public IdDto createPrice(Long id, CreatePriceDto model);

	public boolean updatePrice(Long id, UpdatePriceDto model);

	public boolean deletePrice(Long id);
}
