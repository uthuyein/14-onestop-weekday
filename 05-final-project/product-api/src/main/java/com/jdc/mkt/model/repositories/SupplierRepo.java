package com.jdc.mkt.model.repositories;

import java.util.List;

import com.jdc.mkt.model.entities.Supplier;

public interface SupplierRepo extends BaseRepo<Supplier, Integer>{

	List<Supplier> findSupplierByNameLikeIgnoreCase(String name);

}
