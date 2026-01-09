package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.PurchaseForm;
import com.jdc.mkt.api.inputs.search.SearchPurchaseForm;
import com.jdc.mkt.api.outputs.SelectPurchase;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail;
import com.jdc.mkt.model.repositories.PurchaseDetailRepo;
import com.jdc.mkt.model.repositories.PurchaseRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.UpdateStatus;

@Service
@Transactional(readOnly = true)
public class PurchaseService {

	@Autowired
	private PurchaseRepo repo;
	@Autowired
	private PurchaseDetailRepo detailRepo;

	@Transactional
	public ModificationResult<Integer> update(Integer id, PurchaseForm form) {
		var purchase = id != null ? repo.findById(id).orElse(null) : null;
		
		UpdateStatus update = purchase == null ? UpdateStatus.Save : UpdateStatus.Update;
		try {
			purchase = repo.save(update == UpdateStatus.Update ? form.entity(purchase) : form.entity(new Purchase()));
			updateDetail(purchase,form.purchaseDetails());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ModificationResult.success(purchase.getId(), update, purchase.getSupplier().getName());

	}

	private void updateDetail(Purchase purchase,
			List<PurchaseDetail> purchaseDetails) {
		
		for(PurchaseDetail detail : purchaseDetails) {
			detail.setPurchase(purchase);
			detailRepo.save(detail);
		}
		
	}

	public SelectPurchase findById(Integer id) {
		return null;
	}

	public List<SelectPurchase> findBy(SearchPurchaseForm form) {
		return null;
	}

}
