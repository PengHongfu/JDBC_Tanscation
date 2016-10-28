package com.peng.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.peng.dao.AccountDao;
import com.peng.dao.TransInfoDao;
import com.peng.model.Account;
import com.peng.model.TransInfo;

public class TestDao {
	// public static void main(String[] args) {
	// List<Account> result = new ArrayList<>();
	// AccountDao dao = new AccountDao();
	// try {
	// result = dao.query(2);
	// for(int i =0;i<result.size();i++){
	//
	// System.out.println(result.get(i).getId()+" "+result.get(i).getAccount()+"
	// "+
	// result.get(i).getAmount()+" "+result.get(i).getCreateAt());
	// }
	// } catch (SQLException e) {
	// System.out.println("²éÑ¯Ê§°Ü");
	// e.printStackTrace();
	// }
	// }
	
//	public static void main(String[] args) {
//
//		AccountDao dao = new AccountDao();
//		Account account = new Account();
//		List<Account> result = new ArrayList<>();
//		account.setAccount("a");
//		try {
//			result = dao.query(account);
//			for (int i = 0; i < result.size(); i++) {
//
//				System.out.println(result.get(i).getId() + " " + result.get(i).getAccount() + " "
//						+ result.get(i).getAmount() + " " + result.get(i).getCreateAt());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void main(String[] args) {
//		AccountDao dao = new AccountDao();
//		Account account = new Account();
//		account.setAmount(2500.00);
//		account.setAccount("b");
//		account.setId(1);
//		try {
//			dao.update(account);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args) {
		TransInfo ts = new TransInfo();
		TransInfoDao tdao = new TransInfoDao();
		List<TransInfo> result = new ArrayList<>();
		ts.setSourceeAccount("b");

		try {
			result = tdao.query(ts);
			for (int i = 0; i < result.size(); i++) {

				System.out.println(result.get(i).toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
