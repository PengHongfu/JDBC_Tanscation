package com.peng.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPUtil {
	/** 数据源，static **/
	private static DataSource DS;

	private static final String configFile = "resources/dbcp.properties";

	private static final String connectURL = "jdbc:mysql://127.0.0.1:3306/peng?characterEncoding=utf8&useSSL=false";
	private static final String username = "root";
	private static final String pswd = "root";
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final int initialSize = 5;
	private static final int maxtotal = 200;
	private static final int maxActive = 30;
	private static final int maxIdle = 10;
	private static final int maxWaitMillis = 1000;
	private static final int minIdle = 1;

	/**
	 * 从一个数据源获得一个连接
	 * 
	 * @return
	 */
	public Connection getConn() {
		Connection conn = null;
		if (DS != null) {
			try {
				conn = DS.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		return conn;
	}

	/**
	 * 默认的构造函数
	 */
	public DBCPUtil() {
		/**
		 * 在dbcp.properties文件中读取配置信息
		 */
		initDbcp();
		/**
		 * 自己设置BasicDataSource的参数
		 */
		/*
		 * initDS(connectURL, username, pswd, driverClass, initialSize,maxtotal,
		 * maxActive, maxIdle, maxWaitMillis, minIdle);
		 */
	}

	private static void initDbcp() {
		Properties pops = new Properties();
		try {
			File file = new File(configFile);
			FileInputStream in = new FileInputStream(file);
			// 读取配置文件
			pops.load(in);
			// 把文件里的参数存进DS中
			DS = BasicDataSourceFactory.createDataSource(pops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 构造函数，初始化了 DS ，指定 数据库 **/
	public DBCPUtil(String connectURL) {
		initDS(connectURL);
	}

	/** 构造函数，初始化了 DS，指定所有参数 **/
	public DBCPUtil(String connectURL, String username, String pswd, String driverClass, int initialSize, int maxtotal,
			int maxActive, int maxIdle, int maxWaitMillis, int minIdle) {
		initDS(connectURL, username, pswd, driverClass, initialSize, maxtotal, maxActive, maxIdle, maxWaitMillis,
				minIdle);
	}

	/**
	 * 
	 * @param connectURL
	 * @param username
	 * @param pswd
	 * @param driverClass
	 * @param initialSize
	 * @param maxtotal
	 * @param maxActive
	 * @param maxIdle
	 * @param maxWaitMillis
	 *            获取连接的最大等待毫秒数
	 * @param minIdle
	 *            最小连接数
	 */
	public static void initDS(String connectURL, String username, String pswd, String driverClass, int initialSize,
			int maxtotal, int maxActive, int maxIdle, int maxWaitMillis, int minIdle) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUsername(username);
		ds.setPassword(pswd);
		ds.setUrl(connectURL);
		ds.setInitialSize(initialSize); // 初始化连接数
		ds.setMaxTotal(maxtotal);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWaitMillis(maxWaitMillis);
		ds.setMinIdle(minIdle);
		DS = ds;
	}

	private void initDS(String connectURL) {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(connectURL);
		DS = ds;
	}
}
