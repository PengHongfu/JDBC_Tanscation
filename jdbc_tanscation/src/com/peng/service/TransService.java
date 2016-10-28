package com.peng.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.peng.dao.AccountDao;
import com.peng.dao.TransInfoDao;
import com.peng.db.DBUtil;
import com.peng.model.Account;
import com.peng.model.TransInfo;

public class TransService {
	/**
	 * ������Ľ���
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public String trans(Account from,Account to,Double amount) throws SQLException{
		AccountDao accountDao = new AccountDao();
		TransInfoDao transInfoDao = new TransInfoDao();
		from.setAmount(from.getAmount()-amount);
		accountDao.update(from);
		
		//��Ϊ�������
		String a = null;
		a.split("1");
		
		to.setAmount(to.getAmount()+amount);
		accountDao.update(to);
		TransInfo info = new TransInfo();
		info.setSourceeAccount(from.getAccount());
		info.setDestinationAccount(to.getAccount());
		info.setAmount(amount);
		info.setSourceId(from.getId());
		info.setDestinationId(to.getId());
		transInfoDao.insert(info);
		return "success";
	}
	/**
	 * ��������Ľ���
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws SQLException
	 */
	public String transacation(Account from,Account to,Double amount) throws SQLException{
		Connection conn = DBUtil.getConn();
		conn.setAutoCommit(false);
		
		try {
			AccountDao accountDao = new AccountDao();
			TransInfoDao transInfoDao = new TransInfoDao();
			
			from.setAmount(from.getAmount()-amount); // �ͻ��˻�����ȥ���׽��
			accountDao.update(from); // ���¿ͻ��˻���Ϣ
			//��Ϊ�������
//		String a = null;
//		a.split("1");
			
			to.setAmount(to.getAmount()+amount); // �̼��˻������Ͻ��׽��
			accountDao.update(to); // �����̼��˻���Ϣ
			
			TransInfo info = new TransInfo();
			info.setSourceeAccount(from.getAccount());
			info.setDestinationAccount(to.getAccount());
			info.setAmount(amount);
			info.setSourceId(from.getId());
			info.setDestinationId(to.getId());
			transInfoDao.insert(info); // ���潻����Ϣ
			
			conn.commit();//�ֶ��ύ
			return "success";
		} catch (Exception e) {
			conn.rollback();//�ع�
			e.printStackTrace();
			return "fail";
		}		
	}
}
