package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.PurchaseDto;
import hr.tvz.serviceplanner.dtos.request.CreatePurchaseDto;
import hr.tvz.serviceplanner.dtos.request.UpdatePurchaseDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Purchase;

public interface PurchaseService extends Operations<Purchase> {

	public IdDto createPurchase(Long id, CreatePurchaseDto model);

	public boolean updatePurchase(Long id, UpdatePurchaseDto model);

	public boolean deletePurchase(Long venueId, Long purchaseId);

	public PurchaseDto getPurchase(Long purchaseId);

	public List<PurchaseDto> getPurchases(Long venueId, Long groupId, Long productId, String date);
}
