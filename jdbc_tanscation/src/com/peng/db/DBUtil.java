package com.peng.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/imooc_db?characterEncoding=utf8&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private static Connection conn = null;

	static {// 加载类时会执行这些静态的代码块
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConn() {
		return conn;
	}
}
