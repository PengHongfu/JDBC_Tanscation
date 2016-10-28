package com.peng.action;

import java.sql.SQLException;

import com.peng.dao.AccountDao;
import com.peng.model.Account;
import com.peng.service.TransService;

public class TransAction {
	public static void main(String[] args) {
		String res;
		try {
			res = trans(); // 
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static String trans() throws SQLException{
		AccountDao accountDao = new AccountDao();
		TransService transService = new TransService();
		
		Account from = null; // 客户对象
		Account to = null; // 商家对象
		
		from = accountDao.get(1); // 得到 id 为 1 的客户的所有信息
		to = accountDao.get(2); // 得到 id 为 2  的商家的所有信息
		return transService.transacation(from, to, 20d); //有事务处理的交易
	}
}
