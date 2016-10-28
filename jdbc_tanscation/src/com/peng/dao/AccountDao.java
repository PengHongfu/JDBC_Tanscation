package com.peng.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.peng.db.C3P0Util;
import com.peng.db.DBCPUtil;
import com.peng.db.DBUtil;
import com.peng.model.Account;

public class AccountDao {
	/**
	 * ��
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void insert(Account account) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "INSERT INTO account_info (account,amount) VALUES (?,?); ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, account.getAccount());
		ptmt.setDouble(2, account.getAmount());
		ptmt.execute();
	}

	/**
	 * ��
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void update(Account account) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "UPDATE account_info SET account=?,amount=? WHERE id =?; ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, account.getAccount());
		ptmt.setDouble(2, account.getAmount());
		ptmt.setInt(3, account.getId());
		ptmt.execute();
	}

	/**
	 * ɾ
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void delete(Account account) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "delete from account_info  WHERE id =?; ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, account.getId());
		ptmt.execute();
	}
	/**
	 * �� ����id��������
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<Account> query(Integer id) throws SQLException {
		Connection conn = DBUtil.getConn();
		List<Account> list = new ArrayList<Account>();
		Account account = null;
		String sql = "select * from account_info  WHERE id =?; ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
			// hh:mm:ss");
			// String date = sdf.format(rs.getDate("create_at"));

			// System.out.println(rs.getString("account")+"
			// "+rs.getDouble("amount")+" "
			// +rs.getTimestamp("create_at"));

			account = new Account();
			account.setAccount(rs.getString("account"));
			account.setAmount(rs.getDouble("amount"));
			account.setId(rs.getInt("id"));
			// getTimestamp�ܵõ�ʱ�����ʱ������
			account.setCreateAt(rs.getTimestamp("create_at"));

			list.add(account);
		}
		return list;
	}
	/**
	 * �� �����û���ƥ���ѯ
	 * @param account
	 * @return
	 * @throws SQLException
	 */
	public List<Account> query(Account account) throws SQLException{
		List<Account> list = new ArrayList<>();
		Connection conn = DBUtil.getConn(); 
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from account_info ");
		sb.append(" where account like ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+account.getAccount()+"%");
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			account = new Account();
			account.setAccount(rs.getString("account"));
			account.setAmount(rs.getDouble("amount"));
			account.setId(rs.getInt("id"));
			// getTimestamp�ܵõ�ʱ�����ʱ������
			account.setCreateAt(rs.getTimestamp("create_at"));

			list.add(account);
		}
		return list;
	}
	public Account get(Integer id) throws SQLException {
		Account a = null;
		Connection conn = DBUtil.getConn();
		String sql = " select * from account_info " + " where id =? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			a = new Account();
			a.setAccount(rs.getString("account"));
			a.setAmount(rs.getDouble("amount"));
			a.setId(rs.getInt("id"));
			// getTimestamp�ܵõ�ʱ�����ʱ������
			a.setCreateAt(rs.getTimestamp("create_at"));
			
		}
		return a;
	}
	/**
	 * �� ����idֵ��ѯ��Ϣ
	 * �������ӳ��������ݿ�
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Account getByDbcp(Integer id) throws SQLException {
		Account a = null;
		DBCPUtil dbcp = new DBCPUtil();
		Connection conn = dbcp.getConn();
		String sql = " select * from account_info " + " where id =? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			a = new Account();
			a.setAccount(rs.getString("account"));
			a.setAmount(rs.getDouble("amount"));
			a.setId(rs.getInt("id"));
			// getTimestamp�ܵõ�ʱ�����ʱ������
			a.setCreateAt(rs.getTimestamp("create_at"));
			
		}
		return a;
	}
	/**
	 * �� ����c3p0���ӳ��������ݿ�
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws PropertyVetoException
	 */
	public Account getByc3p0(Integer id) throws SQLException, PropertyVetoException {
		Account a = null;
		C3P0Util c3p0 = new C3P0Util();
		Connection conn = c3p0.getConnection();
		String sql = " select * from account_info " + " where id =? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			a = new Account();
			a.setAccount(rs.getString("account"));
			a.setAmount(rs.getDouble("amount"));
			a.setId(rs.getInt("id"));
			// getTimestamp�ܵõ�ʱ�����ʱ������
			a.setCreateAt(rs.getTimestamp("create_at"));
			
		}
		return a;
	}
}
