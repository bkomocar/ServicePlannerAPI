package hr.tvz.serviceplanner.persistence.dao.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import hr.tvz.serviceplanner.persistence.dao.common.AbstractHibernateDao;
import hr.tvz.serviceplanner.persistence.dao.interfaces.EventDao;
import hr.tvz.serviceplanner.persistence.models.Employee;
import hr.tvz.serviceplanner.persistence.models.Event;
import hr.tvz.serviceplanner.persistence.models.Group;
import hr.tvz.serviceplanner.persistence.models.Product;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.models.Venue;

@Repository
public class EventDaoImpl extends AbstractHibernateDao<Event> implements EventDao {

	public EventDaoImpl() {
		super();
		setClazz(Event.class);
	}

	@Override
	public Long createEvent(Long venueId, Event event) {
		Product product = getCurrentSession().get(Product.class, event.getProduct().getId());

		Venue venue = getCurrentSession().get(Venue.class, venueId);

		if (event.getPurchases() != null && !event.getPurchases().isEmpty()) {
			SortedSet<Purchase> purchases = new TreeSet<>();
			for (Purchase purchase : event.getPurchases()) {
				if (purchase.getId() != null) {
					purchases.add(getCurrentSession().get(Purchase.class, purchase.getId()));
				}
			}
			event.setPurchases(purchases);
		}

		if (product != null && product.getCategory() != null && product.getCategory().getGroup() != null
				&& product.getCategory().getGroup().getId() != null) {
			Group group = getCurrentSession().get(Group.class, product.getCategory().getGroup().getId());
			if (venue != null && group != null) {
				if (group.getVenue().equals(venue)) {
					event.setProduct(product);
					if (event.getEmployee() != null && event.getEmployee().getId() != null) {
						Employee employee = getCurrentSession().get(Employee.class, event.getEmployee().getId());
						if (employee != null && employee.getVenue().equals(venue)) {
							event.setEmployee(employee);
						} else {
							return null;
						}
					}
					event.setGroup(group);
					create(event);
					return event.getId();
				}
			}
		}
		return null;
	}

	@Override
	public boolean updateEvent(Long eventId, Event event) {
		Event originalEvent = findOne(eventId);
		if (originalEvent != null) {
			if (event.getStartTime() != null) {
				originalEvent.setStartTime(event.getStartTime());
			}
			if (event.getEndTime() != null) {
				originalEvent.setEndTime(event.getEndTime());
			}
			if (event.getProduct() != null && event.getProduct().getId() != null) {
				Product product = getCurrentSession().get(Product.class, event.getProduct().getId());
				originalEvent.setProduct(product);
			}
			if (event.getEmployee() != null && event.getEmployee().getId() != null) {
				Employee employee = getCurrentSession().get(Employee.class, event.getEmployee().getId());
				originalEvent.setEmployee(employee);
			}
			update(originalEvent);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEvent(Long venueId, Long eventId) {
		Event event = findOne(eventId);
		if (event.getGroup() != null && event.getGroup().getVenue() != null
				&& event.getGroup().getVenue().getId() != null && event.getGroup().getVenue().getId() == venueId) {
			deleteById(eventId);
			return true;
		}
		return false;
	}

	@Override
	public Event getEvent(Long eventId) {
		return findOne(eventId);
	}

	@Override
	public boolean removePurchase(Long eventId, Long purchaseId) {
		Event event = findOne(eventId);
		Purchase purchase = getCurrentSession().get(Purchase.class, purchaseId);
		if (event != null && purchase != null) {
			SortedSet<Purchase> purchases = event.getPurchases();
			if (purchases != null && !purchases.isEmpty() && purchases.contains(purchase)) {
				purchases.remove(purchase);
				event.setPurchases(purchases);
				update(event);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addPurchase(Long eventId, Long purchaseId) {
		Event event = findOne(eventId);
		Purchase purchase = getCurrentSession().get(Purchase.class, purchaseId);
		if (event != null && purchase != null) {
			SortedSet<Purchase> purchases = event.getPurchases();
			if (purchases != null) {
				purchases.add(purchase);
				event.setPurchases(purchases);
				update(event);
				return true;
			}
		}
		return false;
	}

}
