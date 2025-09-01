package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.util.List;

import com.jdc.mkt.dto.Customer;

public class CustomerService {
	
	public void resetCustomerTable() {
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
		StringBuilder sb = new StringBuilder("update customer_tbl set ");
		
		if (null != name) {
			sb.append("name = '%s'".formatted(name));
		}
//		if (null != memberType) {
//			var s = addComa(name, memberType);
//			sb.append(s+"= '%s'".formatted(memberType));
//			
//		}
		if (null != active) {
			//var s = addComa(name, memberType);
			sb.append(",active = %d".formatted(active ? 1 : 0));
		}
		
		String query = sb.toString() + " where id = %d".formatted(id);
				
		try(var con = getConnection();
			var stmt = con.createStatement()){
			return stmt.executeUpdate(query);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private String addComa(String prefix, String suffix) {
		if (null != prefix) {
			suffix += ","+suffix;
		}
		return suffix;
	}

	public List<Customer> find(String name, String memberType, Boolean active, int id) {
		return null;
	}
}
