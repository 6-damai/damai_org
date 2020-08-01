package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
import com.yc.damai.util.DBHelper;
import com.yc.damai.util.DBHelper1;

public class OrdersDao {
	// 修改订单状态为1
	public int update1(String id) {
		String sql = "update dm_orders set state =1  where id=?  ";
		return new DBHelper().update(sql, id);
	}

	// 修改订单状态为2
	public int update2(String id) {
		String sql = "update dm_orders set state =2  where id=? and state=1";
		return new DBHelper().update(sql, id);
	}

	// 修改订单状态为3
	public int update3(String id) {
		String sql = "update dm_orders set state =3  where id=? and state=2";
		return new DBHelper().update(sql, id);
	}
	
	// 查询订单的状态
//	public List<?> queryStates() {
//		String sql = "SELECT  id,\n" + "	CASE\n" + "WHEN state = 0 THEN\n" + "	'未支付'\n" + "WHEN state = 1 THEN\n"
//				+ "	'已支付'\n" + "WHEN state = 2 THEN\n" + "	'已发货'\n" + "WHEN state = 3 THEN\n" + "	'已收货'\n"
//				+ "WHEN state = 4 THEN\n" + "	'已评价'\n" + "ELSE\n" + "	'已退货'\n" + "END state\n" + "FROM\n"
//				+ "	dm_orders";
//		return new DBHelper().query(sql);
//	}

	// 查询所有订单
//	public List<?> queryAllOrders() {
//		String sql = "SELECT\n" + "	a.id,\n" + "	a.total,\n" + "	a.createtime,\n" + "	a.uid,\n" + "	b.addr,\n"
//				+ "	b.phone,\n" + "	b.name,\n" + "	CASE\n" + "WHEN a.state = 0 THEN\n" + "	'未支付'\n"
//				+ "WHEN a.state = 1 THEN\n" + "	'待发货'\n" + "WHEN a.state = 2 THEN\n" + "	'已发货'\n"
//				+ "WHEN a.state = 3 THEN\n" + "	'已收货'\n" + "WHEN a.state = 4 THEN\n" + "	'已评价'\n" + "ELSE\n"
//				+ "	'已退货'\n" + "END state\n" + "FROM\n" + "	dm_orders a\n" + "JOIN dm_address b ON a.aid = b.id";
//		return new DBHelper().query(sql);
//	}

//	/**
//	 * 事务绑定 提交订单
//	 * 
//	 * @param uid
//	 * @return
//	 * @throws Exception
//	 */
//	public int payOrders(String uid) throws Exception {
//		String sql = "INSERT into dm_orders SELECT\n" + "	NULL,\n" + "	c.total,\n" + "	NOW(),\n" + "	0,\n"
//				+ "	a.id,\n" + "	b.id\n" + "FROM\n" + "	dm_user a\n" + "JOIN dm_address b ON a.id = b.uid\n"
//				+ "AND dft = 1\n" + "JOIN (\n" + "	SELECT\n" + "		SUM(b.shop_price * a.count) total,\n"
//				+ "		a.uid\n" + "	FROM\n" + "		dm_cart a\n" + "	JOIN dm_product b ON a.pid = b.id\n"
//				+ "	WHERE\n" + "		a.uid =?\n" + "	GROUP BY\n" + "		a.uid\n" + ") c ON a.id = c.uid\n"
//				+ "WHERE\n" + "	a.id =? ";
//		String sql02 = "INSERT INTO dm_orderitem SELECT\n" + "	-- LAST_INSERT_ID() 获取最近产生的主键列 （自增列）\n" + "	NULL,\n"
//				+ "	a.count,\n" + "	a.count * b.shop_price,\n" + "	a.pid,\n"
//				+ "	(SELECT max(id) FROM dm_orders) oid\n" + "FROM\n" + "	dm_cart a\n"
//				+ "JOIN dm_product b ON a.pid = b.id\n" + "WHERE\n" + "	uid = ?";
//		String sql03 = "delete from dm_cart where uid =?";
//		List<String> sqls = new ArrayList<String>();
//		List<List<Object>> params = new ArrayList<List<Object>>();
//
//		List<Object> param01 = new ArrayList<Object>();
//		param01.add(uid);
//		param01.add(uid);
//		sqls.add(sql);
//		params.add(param01);
//
//		List<Object> param02 = new ArrayList<Object>();
//		param02.add(uid);
//		sqls.add(sql02);
//		params.add(param02);
//
//		List<Object> param03 = new ArrayList<Object>();
//		param03.add(uid);
//		sqls.add(sql03);
//		params.add(param03);
//		return new DBHelper1().update(sqls, params);
//	}

	// 添加订单主表记录 orders
	public int insert(String uid) {
		String sql = "INSERT into dm_orders SELECT\n" + "	NULL,\n" + "	c.total,\n" + "	NOW(),\n" + "	0,\n"
				+ "	a.id,\n" + "	b.id\n" + "FROM\n" + "	dm_user a\n" + "JOIN dm_address b ON a.id = b.uid\n"
				+ "AND dft = 1\n" + "JOIN (\n" + "	SELECT\n" + "		SUM(b.shop_price * a.count) total,\n"
				+ "		a.uid\n" + "	FROM\n" + "		dm_cart a\n" + "	JOIN dm_product b ON a.pid = b.id\n"
				+ "	WHERE\n" + "		a.uid =?\n" + "	GROUP BY\n" + "		a.uid\n" + ") c ON a.id = c.uid\n"
				+ "WHERE\n" + "	a.id =? ";
		return new DBHelper().update(sql, uid, uid);
	}

//查询订单列表
	public List<Map<String, Object>> queryOrders(String uid) {
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	dm_orders a\n" + "JOIN dm_orderitem b ON a.id = b.oid\n"
				+ "JOIN dm_product c ON c.id = b.pid\n" + "WHERE\n" + "	a.uid = ?";
		List<Map<String, Object>> list = new DBHelper().query(sql, uid);
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	public Map<String, Object> queryNewOrders(String uid) {
		String sql = "select a.*,b.addr, b.phone, b.name from dm_orders a join dm_address b on a.aid=b.id"
				+ " where a.uid=? and state=0 order by id desc limit 0,1";
		List<Map<String, Object>> list = new DBHelper().query(sql, uid);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Map<String, Object> queryByoid(String uid, String oid) {
		String sql = "SELECT\n" + "	a.*, b.addr,\n" + "	b.phone,\n" + "	b. NAME\n" + "FROM\n" + "	dm_orders a\n"
				+ "JOIN dm_address b ON a.aid = b.id\n" + "WHERE\n" + "	a.uid = ?\n" + "AND state = 0\n"
				+ "AND a.id = ?";
		List<Map<String, Object>> list = new DBHelper().query(sql, uid, oid);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	
	public void update(DmOrders do1) {
		String sql = "update dm_orders  set "
			
				+ "state = ?"
				+ " where id=?";
		new DBHelper().update(sql

				
				,do1.getState()
				,do1.getId());
	}
	
	public int count1(DmOrders dos) {
		String where="";
		List<Object> params=new ArrayList<>();
		if(dos.getUid()!=null ) {
			where+=" and uid= ?";
			params.add(dos.getUid());
			
		}
		
		if(dos.getState()!=null) {
			where+=" and state = ?";
			params.add(dos.getState());
			
		}
		String sql="select null from dm_orders where 1=1"+where;
		return new DBHelper().count(sql,params.toArray());
	}
	
	
	public List<Map<String,Object>> query1(DmOrders dos,String page,String rows){
		String where="";
		List<Object> params=new ArrayList<>();

		if(dos.getUid()!=null ) {
			where+=" and uid= ?";
			params.add(dos.getUid());
			
		}
		
		if(dos.getState()!=null) {
			where+=" and state = ?";
			params.add(dos.getState());
			
		}
		
		int ipage=Integer.parseInt(page);
		int irows=Integer.parseInt(rows);
		ipage=(ipage-1)*10;
	   String sql="select o.id,\n" +
			"	o.uid,\n" +
			"	u.ename,\n" +
			"	o.total,\n" +
			"	o.createtime,\n" +
			"	o.state,\n" +
			"	a.phone,\n" +
			"	a.addr\n" +
			"FROM\n" +
			"	dm_orders o\n" +
			"LEFT JOIN dm_user u ON o.uid = u.id\n" +
			"JOIN dm_address a ON u.id = a.uid\n" +
			" where 1=1 "+where+" limit ?,?";



			
		//return new DBHelper().query(sql, ipage,irows);
		params.add(ipage);
		params.add(irows);
		return new DBHelper().query(sql, params.toArray());
	}



}
