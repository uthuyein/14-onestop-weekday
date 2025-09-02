package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.sql.Connection;
public class CustomerServiceWithTransaction {

	public void transfer(int idFrom,int idTo,double amount) {
		Connection con = null;
		try{
			con = getConnection();
			var stmt = con.createStatement();
			
			var rs = stmt.executeQuery("select balance from account_tbl where customer_id ="+idFrom);
			Double balanceFrom = 0.0;
			
			while(rs.next()) {
				balanceFrom = rs.getDouble(1);
			}
					
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);		
			
			
			stmt.addBatch("update account_tbl set balance = balance - "+amount+" where customer_id = "+idFrom);
			stmt.addBatch("update account_tbl set balance = balance + "+amount+" where customer_id = "+idTo);
			stmt.executeBatch();
			
			if(balanceFrom <= amount) {
				con.rollback();
			}
			con.commit();
			
			stmt.close();
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
