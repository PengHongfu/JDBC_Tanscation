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
		
		Account from = null; // �ͻ�����
		Account to = null; // �̼Ҷ���
		
		from = accountDao.get(1); // �õ� id Ϊ 1 �Ŀͻ���������Ϣ
		to = accountDao.get(2); // �õ� id Ϊ 2  ���̼ҵ�������Ϣ
		return transService.transacation(from, to, 20d); //��������Ľ���
	}
}
