package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.util.List;

import com.jdc.mkt.dto.Customer;

public class CustomerService {

	public int save(String name) {
		String query = "insert into customer_tbl(name) value('"+name+"')";
		
		try(var con = getConnection();
			var stmt = con.createStatement()){
			return stmt.executeUpdate(query);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(String name, String memberType, Boolean active, int id) {
		return 0;
	}

	public List<Customer> find(String name, String memberType, Boolean active, int id) {
		return null;
	}
}
