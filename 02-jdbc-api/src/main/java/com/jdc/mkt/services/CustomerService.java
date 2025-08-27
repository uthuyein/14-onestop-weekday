package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

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
}
