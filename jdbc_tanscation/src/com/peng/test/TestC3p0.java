package com.peng.test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Date;

import com.peng.dao.AccountDao;
import com.peng.model.Account;

public class TestC3p0 {
	public static void main(String[] args) throws SQLException {

		Date a = new Date();
		getmessage();
		Date b = new Date();
		System.out.println(b.getTime() - a.getTime());

		Date c = new Date();
		getmessageByc3p0();
		Date d = new Date();
		System.out.println(d.getTime() - c.getTime());
	}

	public static void getmessage() throws SQLException {
		AccountDao dao = new AccountDao();
		Account account = new Account();

		account = dao.get(1);
		System.out.println(account.toString());

	}

	public static void getmessageByc3p0() throws SQLException {
		AccountDao dao = new AccountDao();
		Account account = new Account();
		try {
			account = dao.getByc3p0(1);
			System.out.println(account.toString());

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
