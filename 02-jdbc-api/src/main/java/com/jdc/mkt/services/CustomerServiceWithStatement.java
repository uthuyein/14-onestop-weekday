package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.dto.Customer;
import com.jdc.mkt.dto.Customer.MemberType;

public class CustomerServiceWithStatement implements ServiceInt{
	
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
		if (null != memberType) {
			String s = checkPrefix(name) ? ",memberType" : "memberType";
			sb.append(s+" = '%s'".formatted(memberType));
			
		}
		if (null != active) {
			String s = checkPrefix(name)|| checkPrefix(memberType) ? ",active" : "active";
			sb.append(s+" = %d".formatted(active ? 1 : 0));
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
	
	public List<Customer> find(String name, String memberType, Boolean active, Integer id) {
		StringBuilder sb = new StringBuilder("select * from customer_tbl where 1=1 ");
		List<Customer> list = new ArrayList<>();
		
		if (null != name) {
			sb.append(" and lower(name) like lower('%s')".formatted(name.concat("%")));
			
		}
		if (null != memberType) {
			sb.append(" and memberType = '%s'".formatted(memberType));
			
		}
		if (null != active) {
			sb.append(" and active = %d".formatted(active ? 1 : 0));
		}
		
		if (null != id && id != 0) {
			sb.append(" and id = %d".formatted(id));
		}
		
		try(var con = getConnection();
			var stmt = con.createStatement()){
			
			var rs = stmt.executeQuery(sb.toString());
			
			while (rs.next()) {
				var c = new Customer(
						rs.getInt("id"), 
						rs.getString("name"),
						MemberType.valueOf(rs.getString("memberType")));
				list.add(c);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
