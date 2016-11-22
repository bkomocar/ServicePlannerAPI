package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PriceDao;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class PriceDaoImpl extends AbstractHibernateDao<Price> implements PriceDao {

	public PriceDaoImpl() {
		super();
		setClazz(Price.class);
	}

	@Override
	public Long createPrice(Long id, Price price) {
		Product product = getCurrentSession().get(Product.class, id);

		if (product != null) {
			Venue venue = product.getVenue();
			price.setVenue(venue);
			price.setProduct(product);
			create(price);
			for (Cost cost : price.getCosts()) {
				cost.setPrice(price);
				getCurrentSession().saveOrUpdate(cost);
			}
			return price.getId();
		}
		return null;
	}

	@Override
	public boolean updatePrice(Long id, Price price) {
		Price originalPrice = findOne(id);
		if (originalPrice != null) {
			if (price.getName() != null) {
				originalPrice.setName(price.getName());
			}
			if (price.getDescription() != null) {
				originalPrice.setDescription(price.getDescription());
			}
			if (price.getDurationInMin() != null) {
				originalPrice.setDurationInMin(price.getDurationInMin());
			}
			if (price.getItemsCount() != null) {
				originalPrice.setItemsCount(price.getItemsCount());
			}
			update(originalPrice);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePrice(Long id) {
		Price price = findOne(id);
		Product product = price.getProduct();
		if (product.getPrices() != null && product.getPrices().size() > 1) {
			// SortedSet<Price> prices = product.getPrices();
			price.setProduct(null);
			// prices.remove(price);
			update(price);
			return true;
		}
		return false;
	}

}
