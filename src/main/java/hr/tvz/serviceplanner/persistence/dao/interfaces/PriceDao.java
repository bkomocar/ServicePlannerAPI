package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Price;

public interface PriceDao extends Operations<Price> {

	public Long createPrice(Long id, Price price);

	public boolean updatePrice(Long id, Price price);

	public boolean deletePrice(Long id);

}
