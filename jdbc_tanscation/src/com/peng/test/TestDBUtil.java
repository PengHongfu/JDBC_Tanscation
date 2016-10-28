package com.peng.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/imooc_db?characterEncoding=utf8&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static Connection conn = null;

	public static void main(String[] args) throws Exception {
		// 1.������������
		Class.forName("com.mysql.jdbc.Driver");
		// 2.������ݿ�����
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		// 3.ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ�
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from account_info");

		while (rs.next()) {
			System.out.println(rs.getString("account") + "," + rs.getString("amount"));
		}

	}

}
