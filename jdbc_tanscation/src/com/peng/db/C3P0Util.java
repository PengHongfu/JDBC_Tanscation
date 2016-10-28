package com.peng.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * Title: C3P0Util
 * Description: c3p0数据源配置类
 * Company: jxust
 * @author Peng
 * @date 上午11:01:58
 */
public class C3P0Util {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	public C3P0Util() throws PropertyVetoException{
	   ds = new ComboPooledDataSource();	   
	}
	public static Connection getConnection() {
		
		try {
			return ds.getConnection();
		} catch (SQLException e) {	
			System.out.println("无法获取连接");
			throw new RuntimeException(e);
		}
	}
}
