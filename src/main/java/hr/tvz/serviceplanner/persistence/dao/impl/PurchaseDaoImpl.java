package hr.tvz.serviceplanner.persistence.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.enums.GroupType;
import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PurchaseDao;
import hr.tvz.serviceplanner.persistence.models.Customer;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
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

		Price price = getCurrentSession().get(Price.class, purchase.getPrice().getId());
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		if (product != null && product.getCategory() != null && product.getCategory().getGroup() != null
				&& product.getCategory().getGroup().getId() != null) {
			Group group = getCurrentSession().get(Group.class, product.getCategory().getGroup().getId());
			if (group != null && price != null && venue != null) {
				if (group.getVenue().equals(venue)) {
					if (purchase.getCustomer() != null && purchase.getCustomer().getId() != null) {
						Customer customer = getCurrentSession().get(Customer.class, purchase.getCustomer().getId());
						if (customer != null && customer.getVenue().equals(venue)) {
							purchase.setCustomer(customer);
						} else {
							return null;
						}
					} else if (group.getType() == GroupType.SERVICE) {
						return null;
					}
					purchase.setVenue(venue);
					purchase.setProduct(product);
					purchase.setPrice(price);
					purchase.setGroup(group);
					create(purchase);
					return purchase.getId();
				}
			}
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
			if (purchase.getPurchaseDate() != null) {
				originalPurchase.setPurchaseDate(purchase.getPurchaseDate());
			}
			if (purchase.getPaymentDate() != null) {
				originalPurchase.setPaymentDate(purchase.getPaymentDate());
			}
			if (purchase.getProduct() != null) {
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
		if (purchase.getGroup() != null && purchase.getGroup().getVenue() != null
				&& purchase.getGroup().getVenue().getId() != null
				&& purchase.getGroup().getVenue().getId() == venueId) {
			deleteById(purchaseId);
			return true;
		}
		return false;
	}

	@Override
	public Purchase getPurchase(Long purchaseId) {
		return findOne(purchaseId);
	}

	@Override
	public List<Purchase> getPurchases(Long venueId, String date) {
		Venue venue = getCurrentSession().get(Venue.class, venueId);
		if(venue != null) {
		if (date != null && !date.isEmpty()) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fromDate = df.parse(date + " 00:00:00");
				Date toDate = df.parse(date + " 23:59:59");
				Criteria criteria = getCurrentSession().createCriteria(Purchase.class);
				criteria.add(Restrictions.between("purchaseDate", fromDate, toDate));
				criteria.add(Restrictions.eq("venue", venue));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				//criteria.setProjection(Projections.distinct(Projections.property("id")));
				//criteria.setResultTransformer(Transformers.aliasToBean(Event.class));
				List<?> results = criteria.list();
				return (List<Purchase>) results;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {			
			if(venue.getPurchases() != null) {
				return new ArrayList<>(venue.getPurchases());
			}
		}
	
		}
		return null;
	}

}
