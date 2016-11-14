package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.viewmodels.PurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.request.CreatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;

public interface PurchaseService extends Operations<Purchase> {

	public IdViewModel createPurchase(Long id, CreatePurchaseViewModel model);

	public boolean updatePurchase(Long id, UpdatePurchaseViewModel model);

	public boolean deletePurchase(Long venueId, Long purchaseId);

	public PurchaseViewModel getPurchase(Long purchaseId);
	
	public List<PurchaseViewModel> getPurchases(Long venueId, Long groupId, String date);
}
