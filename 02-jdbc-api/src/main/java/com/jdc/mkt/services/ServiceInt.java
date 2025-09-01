package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.util.List;

import com.jdc.mkt.dto.Customer;

public interface ServiceInt{
	
	int save(String t);
	int update(String name, String memberType, Boolean active, int id);
	List<Customer> find(String name, String memberType, Boolean active, Integer id);
	
	default void resetCustomerTable() {
		String query = """
				truncate table customer_tbl;
				""";
		
		try(var con = getConnection();
				var stmt = con.createStatement()){
				stmt.execute(query);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
