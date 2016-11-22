package hr.tvz.serviceplanner.persistence.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.PurchaseDto;
import hr.tvz.serviceplanner.dtos.PurchaseDtoFactory;
import hr.tvz.serviceplanner.dtos.request.CreatePurchaseDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePurchaseDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.dao.interfaces.PurchaseDao;
import hr.tvz.serviceplanner.persistence.models.Purchase;
import hr.tvz.serviceplanner.persistence.services.common.AbstractService;
import hr.tvz.serviceplanner.persistence.services.interfaces.PurchaseService;

@Service
public class PurchaseServiceImpl extends AbstractService<Purchase> implements PurchaseService {

	@Autowired
	private PurchaseDao dao;

	@Override
	public IdDto createPurchase(Long id, CreatePurchaseDto model) {
		Long purchaseId = dao.createPurchase(id, CreatePurchaseDto.toPurchase(model));
		if (purchaseId != null) {
			return new IdDto(purchaseId);
		}
		return null;
	}

	@Override
	public boolean updatePurchase(Long id, UpdatePurchaseDto model) {
		if (model != null) {
			return dao.updatePurchase(id, UpdatePurchaseDto.toPurchase(model));
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
	public PurchaseDto getPurchase(Long purchaseId) {
		Purchase purchase = dao.getPurchase(purchaseId);
		if (purchase != null) {
			return PurchaseDtoFactory.toPurchaseDto(purchase, DtoType.large);
		}
		return null;
	}

	@Override
	public List<PurchaseDto> getPurchases(Long venueId, Long groupId, String date) {
		List<Purchase> purchases = dao.getPurchases(venueId, groupId, date);
		if (purchases != null) {
			return PurchaseDtoFactory.toPurchaseDto(purchases, DtoType.large);
		}
		return null;
	}

}
