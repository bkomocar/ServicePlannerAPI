package hr.tvz.serviceplanner.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PurchaseDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Price;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class PurchaseDaoImpl extends AbstractHibernateDao<Purchase> implements PurchaseDao {

	public PurchaseDaoImpl() {
		super();
		setClazz(Purchase.class);
	}

	@Override
	public Long createPurchase(Long venueId, Purchase purchase) {
		Product product = getCurrentSession().get(Product.class, purchase.getProduct().getId());
		Customer customer = getCurrentSession().get(Customer.class, purchase.getCustomer().getId());
		Price price = getCurrentSession().get(Price.class, purchase.getPrice().getId());
		Venue venue = getCurrentSession().get(Venue.class, venueId);

		if (product != null && customer != null && price != null && venue != null) {
			purchase.setProduct(product);
			purchase.setPrice(price);
			purchase.setCustomer(customer);
			purchase.setVenue(venue);
			create(purchase);
			return purchase.getId();
		}
		return null;
	}

	@Override
	public boolean updatePurchase(Long purchaseId, Purchase purchase) {
		Purchase originalPurchase = findOne(purchaseId);
		if (originalPurchase != null) {
			if (purchase.getCurrency() != null) {
				originalPurchase.setCurrency(purchase.getCurrency());
			}
			if (purchase.getValueInSmallestCurrency() != null) {
				originalPurchase.setValueInSmallestCurrency(purchase.getValueInSmallestCurrency());
			}
			if (purchase.getPurchaseTime() != null) {
				originalPurchase.setPurchaseTime(purchase.getPurchaseTime());
			}
			if (purchase.getProduct() != null) {
				Product product = getCurrentSession().get(Product.class, purchase.getProduct().getId());
				originalPurchase.setProduct(product);
			}
			if (purchase.getVenue() != null) {
				Product product = getCurrentSession().get(Product.class, purchase.getProduct().getId());
				originalPurchase.setProduct(product);
			}
			if (purchase.getCustomer() != null && purchase.getCustomer().getId() != null) {
				Customer customer = getCurrentSession().get(Customer.class, purchase.getCustomer().getId());
				originalPurchase.setCustomer(customer);
			}
			if (purchase.getPrice() != null && purchase.getPrice().getId() != null) {
				Price price = getCurrentSession().get(Price.class, purchase.getPrice().getId());
				originalPurchase.setPrice(price);
			}
			update(originalPurchase);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePurchase(Long venueId, Long purchaseId) {
		Purchase purchase = findOne(purchaseId);
		if(purchase.getVenue().getId() == venueId){
			deleteById(purchaseId);
			return true;
		}
		return false;
	}

	@Override
	public Purchase getPurchase(Long purchaseId) {
		return findOne(purchaseId);
	}

}
