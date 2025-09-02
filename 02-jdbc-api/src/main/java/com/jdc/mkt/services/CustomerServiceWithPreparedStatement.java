package com.jdc.mkt.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.dto.Customer;
import com.jdc.mkt.dto.Customer.MemberType;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

public class CustomerServiceWithPreparedStatement implements ServiceInt {

	@Override
	public int save(String name) {
		String query = """
				insert into customer_tbl(name)values(?);
				""";
		try (var con = getConnection();
			 var pstmt = con.prepareStatement(query)) {
			 
			pstmt.setString(1, name);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(String name, String memberType, Boolean active, int id) {
		StringBuilder sb = new StringBuilder("update customer_tbl set ");
		List<Object>params = new ArrayList<>();
		
		if (null != name) {
			sb.append("name = ?");
			params.add(name);
		}
		if (null != memberType) {
			sb.append(checkPrefix(name) ? ",memberType = ?" : "memberType=?");
			params.add(memberType);
			
		}
		if (null != active) {
			sb.append(checkPrefix(name)|| checkPrefix(memberType) ? ",active" : "active");
			params.add(active ? 1 : 0);
		}
		
		String query = sb.toString() + " where id = ?";
		params.add(id);
				
		try(var con = getConnection();
			var stmt = con.prepareStatement(query)){
			
			addParam(params, stmt);			
			return stmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Customer> find(String name, String memberType, Boolean active, Integer id) {
		
		StringBuilder sb = new StringBuilder("select * from customer_tbl where 1=1 ");
		List<Customer> list = new ArrayList<>();
		List<Object> params = new ArrayList<>();
		
		if (null != name) {
			sb.append(" and lower(name) like lower(?)");
			params.add(name.concat("%"));
			
		}
		if (null != memberType) {
			sb.append(" and memberType = ? ");
			params.add(memberType);
			
		}
		if (null != active) {
			sb.append(" and active = ?");
			System.out.println(active);
			params.add(active ? 1 : 0);
					
		}
		
		if (null != id && id != 0) {
			sb.append(" and id = ?");
			params.add(id);
		}
		
		try(var con = getConnection();
			var stmt = con.prepareStatement(sb.toString())){
					
			addParam(params, stmt);			
			var rs = stmt.executeQuery();
						
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
	
	private void addParam(List<Object> params, PreparedStatement stmt) throws SQLException {
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i + 1, params.get(i));
		}
	}

}
