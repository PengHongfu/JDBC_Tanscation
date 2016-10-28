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
	 * 无事务的交易
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
		
		//人为报错代码
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
	 * 有事务处理的交易
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
			
			from.setAmount(from.getAmount()-amount); // 客户账户余额减去交易金额
			accountDao.update(from); // 更新客户账户信息
			//人为错误代码
//		String a = null;
//		a.split("1");
			
			to.setAmount(to.getAmount()+amount); // 商家账户余额加上交易金额
			accountDao.update(to); // 更新商家账户信息
			
			TransInfo info = new TransInfo();
			info.setSourceeAccount(from.getAccount());
			info.setDestinationAccount(to.getAccount());
			info.setAmount(amount);
			info.setSourceId(from.getId());
			info.setDestinationId(to.getId());
			transInfoDao.insert(info); // 保存交易信息
			
			conn.commit();//手动提交
			return "success";
		} catch (Exception e) {
			conn.rollback();//回滚
			e.printStackTrace();
			return "fail";
		}		
	}
}
