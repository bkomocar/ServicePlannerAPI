package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.ArrayList;
import java.util.SortedSet;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.ProductDao;
import hr.tvz.serviceplanner.persistence.models.Category;
import hr.tvz.serviceplanner.persistence.models.Cost;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Venue;
import scala.remote;

@Repository
public class ProductDaoImpl extends AbstractHibernateDao<Product> implements ProductDao {

	public ProductDaoImpl() {
		super();
		setClazz(Product.class);
	}

	@Override
	public boolean updateProduct(Long id, Product product) {
		Product originalProduct = findOne(id);

		if (originalProduct != null) {

			Venue venue = originalProduct.getVenue();

			if (product.getName() != null) {
				originalProduct.setName(product.getName());
			}
			if (product.getMaxCustomers() != null) {
				originalProduct.setMaxCustomers(product.getMaxCustomers());
			}
			if (product.getDescription() != null) {
				originalProduct.setDescription(product.getDescription());
			}
			if (product.getShortName() != null) {
				originalProduct.setShortName(product.getShortName());
			}
			SortedSet<Price> prices = product.getPrices();

			if (prices != null && !prices.isEmpty()) {
				ArrayList<Price> originalPriceList = new ArrayList<Price>(originalProduct.getPrices());
				ArrayList<Price> priceList = new ArrayList<Price>(prices);
				for (Price price : priceList) {

					if (price.getId() == null) {
						price.setVenue(venue);
						price.setProduct(originalProduct);
						getCurrentSession().saveOrUpdate(price);
						ArrayList<Cost> costList = new ArrayList<Cost>(price.getCosts());
						for (Cost cost : costList) {
							cost.setPrice(price);
							if (cost.getId() == null) {
								getCurrentSession().saveOrUpdate(cost);
							}
						}
						originalProduct.getPrices().add(price);
					} else if (originalProduct.getPrices().contains(price)) {
						updatePrice(price.getId(), price);
					}

				}

				for (Price price : originalPriceList) {
					if (!prices.contains(price)) {
						price.setProduct(null);
						getCurrentSession().saveOrUpdate(price);
						originalProduct.getPrices().remove(price);
					} else {
						price.setVenue(venue);
						price.setProduct(originalProduct);
					}
				}
			}
			/*
			 * if (prices != null && !prices.isEmpty()) { for (Price price :
			 * prices) { price.setProduct(originalProduct);
			 * getCurrentSession().saveOrUpdate(price); for (Cost cost :
			 * price.getCosts()) { cost.setPrice(price);
			 * getCurrentSession().saveOrUpdate(cost); } }
			 * originalProduct.setPrices(prices); }
			 */
			update(originalProduct);
			return true;
		}
		return false;
	}

	void updatePrice(long priceId, Price price) {
		Price originalPrice = getCurrentSession().get(Price.class, priceId);
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
		}
		ArrayList<Cost> originalCostList = new ArrayList<Cost>(originalPrice.getCosts());

		ArrayList<Cost> costList = new ArrayList<Cost>(price.getCosts());
		for (Cost cost : costList) {
			if (cost.getId() == null) {
				getCurrentSession().saveOrUpdate(cost);
				originalPrice.getCosts().add(cost);
			} else if (originalPrice.getCosts().contains(cost)) {
				updateCost(cost.getId(), cost);
			}
		}

		for (Cost cost : originalCostList) {
			if (!originalPrice.getCosts().contains(cost)) {
				cost.setPrice(null);
				getCurrentSession().saveOrUpdate(cost);
				originalPrice.getCosts().remove(cost);
			} else {
				cost.setPrice(originalPrice);
			}
		}
		getCurrentSession().merge(originalPrice);
	}

	void updateCost(long id, Cost cost) {
		Cost originalCost = getCurrentSession().get(Cost.class, id);
		if (originalCost != null) {
			if (cost.getCurrency() != null) {
				originalCost.setCurrency(cost.getCurrency());
			}
			if (cost.getValueInSmallestCurrency() != null) {
				originalCost.setValueInSmallestCurrency(cost.getValueInSmallestCurrency());
			}
		}
		getCurrentSession().merge(originalCost);
	}

	@Override
	public Long createProduct(Long id, Product product) {
		Category category = getCurrentSession().get(Category.class, id);
		SortedSet<Price> prices = product.getPrices();
		if (category != null && prices != null && !prices.isEmpty()) {

			Group group = category.getGroup();
			Venue venue = group.getVenue();
			product.setVenue(venue);
			product.setCategory(category);

			for (Price price : prices) {
				price.setProduct(product);
				price.setVenue(venue);
				for (Cost cost : price.getCosts()) {
					cost.setPrice(price);
				}
			}
			create(product);

			/*
			 * for (Price price : prices) { price.setProduct(product);
			 * getCurrentSession().saveOrUpdate(price); for (Cost cost :
			 * price.getCosts()) { cost.setPrice(price);
			 * getCurrentSession().saveOrUpdate(cost); } }
			 */
			return product.getId();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		Product product = findOne(productId);
		if (product != null) {
			product.setCategory(null);
			update(product);
			return true;
		}
		return false;
	}
}
