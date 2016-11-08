package hr.tvz.serviceplanner.persistence.dao.impl;

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
			
			Category category = originalProduct.getCategory();
			Group group = category.getGroup();
			Venue venue = group.getVenue();
			
			
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
				for (Price price : prices) {
					price.setVenue(venue);
					price.setProduct(originalProduct);
					for (Cost cost : price.getCosts()) {
						cost.setPrice(price);
					}
				} 
				originalProduct.setPrices(prices);
			}
			/*if (prices != null && !prices.isEmpty()) {
				for (Price price : prices) {
					price.setProduct(originalProduct);
					getCurrentSession().saveOrUpdate(price);
					for (Cost cost : price.getCosts()) {
						cost.setPrice(price);
						getCurrentSession().saveOrUpdate(cost);
					}
				} 
				originalProduct.setPrices(prices);
			}*/
			update(originalProduct);
			return true;
		}
		return false;
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
			
			/*for (Price price : prices) {
				price.setProduct(product);
				getCurrentSession().saveOrUpdate(price);
				for (Cost cost : price.getCosts()) {
					cost.setPrice(price);
					getCurrentSession().saveOrUpdate(cost);
				}
			} 	*/			
			return product.getId();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		Product product = findOne(productId);
		if (product!= null){
			product.setCategory(null);
			update(product);
			return true;
		}
		return false;
	}
}
