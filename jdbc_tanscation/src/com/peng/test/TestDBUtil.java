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
		// 1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获得数据库连接
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from account_info");

		while (rs.next()) {
			System.out.println(rs.getString("account") + "," + rs.getString("amount"));
		}

	}

}
