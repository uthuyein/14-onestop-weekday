package com.jdc.mkt.model.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.SaleDetailForm;
import com.jdc.mkt.api.inputs.SaleForm;
import com.jdc.mkt.api.inputs.search.SearchSaleForm;
import com.jdc.mkt.api.outputs.SelectSaleDetail;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.repositories.SaleDetailRepo;
import com.jdc.mkt.model.repositories.SaleRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.UpdateStatus;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepo saleRepo;
	@Autowired
	private SaleDetailRepo detailRepo;

	/**
	 * @param id
	 * @param form
	 * @return
	 */
	public ModificationResult<Integer> update(UUID id, SaleForm form) {
		 var sale = getSaleById(id);
		 var status = sale == null ? UpdateStatus.Save : UpdateStatus.Update;
		
		  sale = saleRepo.save(status == UpdateStatus.Save ? new Sale() : sale);
		  updateSaleDetail(sale,form.saleDetails());
		  
		 return ModificationResult.success(null, status, null);
	}

	/**
	 * @param sale
	 * @param saleDetails
	 */
	private void updateSaleDetail(Sale sale, List<SaleDetailForm> saleDetails) {
		
		if(sale != null) {
			for(SaleDetailForm form : saleDetails) {
				detailRepo.save(form.entity(sale));
			}
		}
	}

	/**
	 * @param form
	 * @return
	 */
	public List<SelectSaleDetail> findBy(SearchSaleForm form) {
		return null;
	}

	/**
	 * @param id
	 * @return sale
	 */
	public SelectSaleDetail findById(UUID id) {
		 var sale = getSaleById(id);		 
		// return sale != null ? SelectSaleDetail.from(sale) : null;
		 return null;
	}
	
	private Sale getSaleById(UUID id) {
		return id == null ? null : saleRepo.findById(id).orElse(null);
	}

}
