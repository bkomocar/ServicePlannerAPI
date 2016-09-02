package hr.tvz.serviceplanner.persistence.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PurchaseDao;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.PurchaseService;
import hr.tvz.serviceplanner.viewmodels.request.CreatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.request.UpdatePurchaseViewModel;
import hr.tvz.serviceplanner.viewmodels.response.IdViewModel;
import hr.tvz.serviceplanner.viewmodels.response.PurchaseViewModelLarge;

@Service
public class PurchaseServiceImpl extends AbstractService<Purchase> implements PurchaseService {

	@Autowired
	private PurchaseDao dao;

	@Override
	public IdViewModel createPurchase(Long id, CreatePurchaseViewModel model) {
		Long purchaseId = dao.createPurchase(id, CreatePurchaseViewModel.toPurchase(model));
		if (purchaseId != null) {
			return new IdViewModel(purchaseId);
		}
		return null;
	}

	@Override
	public boolean updatePurchase(Long id, UpdatePurchaseViewModel model) {
		if (model != null) {
			return dao.updatePurchase(id, UpdatePurchaseViewModel.toPurchase(model));
		}
		return false;
	}

	@Override
	public boolean deletePurchase(Long venueId, Long purchaseId) {
		return dao.deletePurchase(venueId, purchaseId);
	}

	@Override
	protected Operations<Purchase> getDao() {
		return dao;
	}

	@Override
	public PurchaseViewModelLarge getPurchase(Long purchaseId) {
		Purchase purchase = dao.getPurchase(purchaseId);
		if (purchase != null) {
			return PurchaseViewModelLarge.fromPurchase(purchase);
		}
		return null;
	}

}
