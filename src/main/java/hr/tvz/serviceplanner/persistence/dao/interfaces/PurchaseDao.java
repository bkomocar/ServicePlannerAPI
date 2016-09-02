package hr.tvz.serviceplanner.persistence.dao.interfaces;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public interface PurchaseDao extends Operations<Purchase> {

	public Long createPurchase(Long venueId, Purchase purchase);

	public boolean updatePurchase(Long purchaseId, Purchase purchase);

	public boolean deletePurchase(Long venueId, Long purchaseId);

	public Purchase getPurchase(Long purchaseId);
}
