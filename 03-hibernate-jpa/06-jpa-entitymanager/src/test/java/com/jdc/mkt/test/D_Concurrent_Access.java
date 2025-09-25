package com.jdc.mkt.test;

import org.hibernate.TransactionException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.mkt.entity.Account;

public class D_Concurrent_Access extends JpaFactory {

	@Test
	@Disabled
	void flushAndRefreshTest() {
		Thread first = getOperationOne(15000);
		Thread second = getOperationTwo(15000);

		first.start();
		second.start();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@ParameterizedTest
	@CsvSource(value = {
			"1,3,5000"
	})
	void transitionTest(int transferId, int recieverId, double update) {
		var em = emf.createEntityManager();
		var transaction = em.getTransaction();
	
		//transition.begin();

		try {
			transaction.begin();
			var charlie = em.find(Account.class, transferId);
			var init = charlie.getBalance();
			
			if (init <= update) {
				throw new TransactionException("Initial balance not enough to transfer !");
			}

			charlie.setBalance(init - update);

			var trump = em.find(Account.class, recieverId);

			if (null == trump) {
				throw new NullPointerException("Reciever Account not found !");
			}

			trump.setBalance(trump.getBalance() + update);

			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

	}

	private Thread getOperationOne(double balance) {
		return new Thread(() -> {
			var em = emf.createEntityManager();
			em.getTransaction().begin();
			var acc = em.find(Account.class, 1);
			var initBalance = acc.getBalance();

			System.out.println("====================   Before Operation One   =================");
			System.out.println("   ******** (One) Before Update Balance : " + initBalance + "   *********");

			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (balance >= initBalance) {

			}

			em.refresh(acc);
			acc.setBalance(acc.getBalance() - balance);
			em.flush();

			System.out.println("====================   After Operation One   =================");
			System.out.println("   ********  (One) After Update Balance : " + acc.getBalance() + "   *********");
			System.out.println();

			em.getTransaction().commit();

			em.close();
		});
	}

	private Thread getOperationTwo(double i) {
		return new Thread(() -> {
			var em = emf.createEntityManager();
			var acc = em.find(Account.class, 2);
			em.getTransaction().begin();

			System.out.println("====================   Before Operation Two   =================");
			System.out.println("   ******** (Two) Before Update Balance : " + acc.getBalance() + "   *********");

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			em.refresh(acc);
			acc.setBalance(acc.getBalance() - i);
			em.flush();

			System.out.println("====================   After Operation Two   =================");
			System.out.println("   ********  (Two) After Update Balance : " + acc.getBalance() + "   *********");
			System.out.println();

			em.getTransaction().commit();
			em.close();
		});
	}

}
