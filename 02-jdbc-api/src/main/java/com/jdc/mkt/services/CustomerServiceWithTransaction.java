package com.jdc.mkt.services;

import static com.jdc.mkt.util.MysqlConnector.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
public class CustomerServiceWithTransaction {

	public void transfer(int idFrom,int idTo,double amount) throws SQLException   {
		Connection con = null;
		try{
			con = getConnection();
			var stmt = con.createStatement();
			
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);		
			
			var rs = stmt.executeQuery("select balance from account_tbl where customer_id ="+idFrom);
			Double balanceFrom = 0.0;
			
			while(rs.next()) {
				balanceFrom = rs.getDouble(1);
			}
							
			stmt.executeUpdate("update account_tbl set balance = balance - "+amount+" where customer_id = "+idFrom);
			
			stmt.executeUpdate("update account_tbl set balance = balance + "+amount+" where customer_id = "+idTo);
			
			if(balanceFrom <= amount) {
				throw new SQLException("Not enought to transfer ! ");
			}
			con.commit();
			
			stmt.close();
			con.close();
			
		}catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}
}
