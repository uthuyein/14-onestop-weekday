package com.jdc.mkt.model.services;

import java.util.ArrayList;
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
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

@Service
public class SaleService {

	@Autowired
	private SaleRepo saleRepo;
	@Autowired
	private SaleDetailRepo detailRepo;

	private List<SaleDetailForm> saleDetails = new ArrayList<>();

	/**
	 * @param id
	 * @param form
	 * @return
	 */
	public ModificationResult<UUID> update(UUID id, SaleForm form) {
		var sale = getSaleById(id);
		var status = sale == null ? ModifiedType.Save : ModifiedType.Update;

		if (!saleDetails.isEmpty() && saleDetails.size() > 0) {
			sale = saleRepo.save(status == ModifiedType.Save ? form.entity(new Sale()) : form.entity(sale));
			updateSaleDetail(sale, saleDetails);
			saleDetails.clear();
		}

		if(null == sale) {
			return  ModificationResult.success(null, status, null);
		}
		return ModificationResult.success(sale == null ? null: sale.getId(), status, sale.getCustomer().getName());
	}

	/**
	 * @param sale
	 * @param saleDetails
	 */
	private void updateSaleDetail(Sale sale, List<SaleDetailForm> saleDetails) {
		if (sale != null) {		
			
			for (SaleDetailForm detail : saleDetails) {		
				detailRepo.save(detail.entity(sale));
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
	 * @param form
	 * @return
	 */
	public void addSaleDetail(SaleDetailForm form) {
		saleDetails.add(form);		
	}


	private Sale getSaleById(UUID id) {
		return id == null ? null : saleRepo.findById(id).orElse(null);
	}

}
