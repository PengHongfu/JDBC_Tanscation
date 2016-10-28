package com.peng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.peng.db.DBUtil;
import com.peng.model.Account;
import com.peng.model.TransInfo;

public class TransInfoDao {
	/**
	 * Ôö
	 * @param transInfo
	 * @throws SQLException
	 */
	public void insert(TransInfo transInfo) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "INSERT INTO trans_info (source_id,source_account,destination_id,destination_account,amount)"
				+ " VALUES (?,?,?,?,?); ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, transInfo.getSourceId());
		ptmt.setString(2, transInfo.getSourceeAccount());
		ptmt.setInt(3, transInfo.getDestinationId());
		ptmt.setString(4, transInfo.getDestinationAccount());
		ptmt.setDouble(5, transInfo.getAmount());
		ptmt.execute();
	}
	/**
	 * É¾
	 * @param transInfo
	 * @throws SQLException
	 */
	public void delete(TransInfo transInfo) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "delete from trans_info  WHERE id =?; ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, transInfo.getId());
		ptmt.execute();
	}
	/**
	 * ¸ü
	 * @param transInfo
	 * @throws SQLException
	 */
	public void update(TransInfo transInfo) throws SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "UPDATE trans_info SET source_account=?,"
				+ "destination_id=?,destination_account=?,amount=? where id=? ; ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, transInfo.getSourceeAccount());
		ptmt.setInt(2, transInfo.getDestinationId());
		ptmt.setString(3, transInfo.getDestinationAccount());
		ptmt.setDouble(4, transInfo.getAmount());
		ptmt.setInt(5, transInfo.getId());
		ptmt.execute();
	}
	public List<TransInfo> query(TransInfo transInfo) throws SQLException{
		List<TransInfo> list = new ArrayList<>();
		Connection conn = DBUtil.getConn(); 
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from trans_info ");
		sb.append(" where source_account like ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+transInfo.getSourceeAccount()+"%");
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()){
			transInfo = new TransInfo();
			transInfo.setId(rs.getInt("id"));
			transInfo.setSourceId(rs.getInt("source_id"));
			transInfo.setSourceeAccount(rs.getString("source_account"));
			transInfo.setDestinationId(rs.getInt("destination_id"));
			transInfo.setDestinationAccount(rs.getString("destination_account"));
			transInfo.setCreateAt(rs.getTimestamp("create_at"));
			transInfo.setAmount(rs.getDouble("amount"));
		
			list.add(transInfo);
		}
		return list;
	}
}
