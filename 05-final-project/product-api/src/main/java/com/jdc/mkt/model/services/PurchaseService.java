package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.PurchaseDetailForm;
import com.jdc.mkt.api.inputs.PurchaseForm;
import com.jdc.mkt.api.inputs.search.SearchPurchaseForm;
import com.jdc.mkt.api.outputs.SelectPurchase;
import com.jdc.mkt.api.outputs.SelectPurchaseDetail;
import com.jdc.mkt.model.entities.Purchase;
import com.jdc.mkt.model.entities.PurchaseDetail_;
import com.jdc.mkt.model.entities.Purchase_;
import com.jdc.mkt.model.repositories.PurchaseDetailRepo;
import com.jdc.mkt.model.repositories.PurchaseRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

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
		ModifiedType update = purchase == null ? ModifiedType.Save : ModifiedType.Update;
		
		try {
			purchase = repo.save(update == ModifiedType.Update ? form.entity(purchase) : form.entity(new Purchase()));
			updateDetail(purchase,form.purchaseDetails());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ModificationResult.success(purchase.getId(), update, purchase.getSupplier().getName());

	}

	private void updateDetail(Purchase purchase,
			List<PurchaseDetailForm> purchaseDetails) {
		
		for(PurchaseDetailForm form : purchaseDetails) {
			detailRepo.save(form.entity());
		}		
	}

	public SelectPurchase findById(Integer id) {
		return SelectPurchase.from(repo.findById(id).orElse(null));
	}

	public List<SelectPurchaseDetail> findBy(SearchPurchaseForm form) {
		return repo.findBy(cb -> {
			
			var cq = cb.createQuery(SelectPurchaseDetail.class);
			var root = cq.from(Purchase.class);
			var detail = root.join(Purchase_.purchaseDetails);
			var price = detail.join(PurchaseDetail_.productPrice);
			
			SelectPurchaseDetail.select(cb,cq,root,detail,price);
			cq.where(form.where(cb,root,detail,price));
			
			return cq;
		});
	}	
}
