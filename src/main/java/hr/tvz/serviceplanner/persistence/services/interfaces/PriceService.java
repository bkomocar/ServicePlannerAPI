package hr.tvz.serviceplanner.persistence.services.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.viewmodels.request.CreatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePriceViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface PriceService extends Operations<Price> {

	public IdViewModel createPrice(Long id, CreatePriceViewModel model);

	public boolean updatePrice(Long id, UpdatePriceViewModel model);

	public boolean deletePrice(Long id);
}
