package com.jdc.mkt.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.SaleDetailForm;
import com.jdc.mkt.api.inputs.SaleForm;
import com.jdc.mkt.api.inputs.search.SearchSaleForm;
import com.jdc.mkt.api.outputs.SelectSaleDetail;
import com.jdc.mkt.model.entities.ProductPrice_;
import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail_;
import com.jdc.mkt.model.entities.Sale_;
import com.jdc.mkt.model.repositories.SaleDetailRepo;
import com.jdc.mkt.model.repositories.SaleRepo;
import com.jdc.mkt.utils.BusinessException;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

@Service
@Transactional(readOnly = true)
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
	@Transactional
	public ModificationResult<UUID> update(UUID id, SaleForm form) {
		var sale = getSaleById(id);
		var status = sale == null ? ModifiedType.Save : ModifiedType.Update;

		try {
			if (saleDetails.isEmpty() && saleDetails.size() == 0) {
				throw new BusinessException("Please select product first");
			}

			sale = saleRepo.save(status == ModifiedType.Save ? form.entity(new Sale()) : form.entity(sale));
			updateSaleDetail(sale, saleDetails);
			saleDetails.clear();

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		return ModificationResult.status(sale == null ? null : sale.getId(), status, sale.getCustomer().getName());
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
		return detailRepo.findBy(
				cb -> {
					var cq = cb.createQuery(SelectSaleDetail.class);
					var root = cq.from(Sale.class);
					
					var detail = root.join(Sale_.saleDetails);
					var product = detail.join(SaleDetail_.productPrice).get(ProductPrice_.product);
					var size = detail.join(SaleDetail_.productPrice).get(ProductPrice_.size);
					var customer = root.join(Sale_.customer);
					
					SelectSaleDetail.select(cb,cq,root,detail,product,size,customer);
					cq.where(form.where(cb,root,detail,product,size,customer));
					
					return cq;
				});
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
