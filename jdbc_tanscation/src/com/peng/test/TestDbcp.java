package com.peng.test;

import java.sql.SQLException;
import java.util.Date;

import com.peng.dao.AccountDao;
import com.peng.model.Account;

public class TestDbcp {
	public static void main(String[] args) throws SQLException {
		// 1.通过不同的方式操纵数据库
		Date a = new Date();
		getMessage();
		Date b = new Date();
		System.out.println(b.getTime() - a.getTime());

		// 1.通过不同的方式操纵数据库
		Date c = new Date();
		getMessageByDbcp();
		Date d = new Date();
		System.out.println(d.getTime() - c.getTime());
	}

	public static void getMessage() throws SQLException {
		AccountDao dao = new AccountDao();
		Account a = new Account();
		a = dao.get(1);
		System.out.println(a.toString());
	}

	public static void getMessageByDbcp() throws SQLException {
		AccountDao dao = new AccountDao();
		Account b = new Account();
		b = dao.getByDbcp(2);
		System.out.println(b.toString());
	}

}
