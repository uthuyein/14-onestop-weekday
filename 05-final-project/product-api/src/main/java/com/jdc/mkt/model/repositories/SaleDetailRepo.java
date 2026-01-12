/**
 * 
 */
package com.jdc.mkt.model.repositories;

import java.util.List;
import java.util.UUID;

import com.jdc.mkt.model.entities.Sale;
import com.jdc.mkt.model.entities.SaleDetail;
import com.jdc.mkt.model.entities.SaleDetailPk;

/**
 * SaleDetailRepo
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

public interface SaleDetailRepo extends BaseRepo<SaleDetail, SaleDetailPk>{


	List<SaleDetail> findBySaleId(UUID id);

}
