package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmOrders;

import com.yc.damai.util.DBHelper;

public class Orders0Dao {
	
	public int insert(String uid) {
		String sql=
				"insert into dm_orders SELECT \n" +
				"  NULL,\n" +
				"  c.total,\n" +
				"	NOW(),\n" +
				"	0,\n" +
				"	a.id,\n" +
				"	b.id\n" +
				"FROM\n" +
				"  dm_user a\n" +
				"JOIN dm_address b ON a.id=b.uid\n" +
				"AND dft = 1\n" +
				"JOIN (\n" +
				"	SELECT \n" +
				"		SUM(b.shop_price*a.count) total,\n" +
				"		a.uid\n" +
				"	FROM\n" +
				"		dm_cart a\n" +
				"	JOIN dm_product b ON a.pid=b.id\n" +
				"	WHERE\n" +
				"	 a.uid= ?\n" +
				"	GROUP BY\n" +
				"	 a.uid\n" +
				") c ON a.id=c.uid\n" +
				"WHERE\n" +
				" a.id= ?";
		return new DBHelper().update(sql, uid,uid);
		
	}
	
	
	
	public Map<String,Object> queryNewOrders(String uid){
		
		String sql="select a.*,b.addr,b.phone,b.name from dm_orders a join dm_address b on a.aid=b.id where a.uid=? and state=0 order by id desc limit 0,1";
		List<Map<String,Object>> list=new DBHelper().query(sql, uid);
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	
public List<Map<String, Object>> queryallOrders(String uid){
		
		String sql="select * from dm_orders where uid=?";
		List<Map<String,Object>> list=new DBHelper().query(sql, uid);
		return list;
	}

public List<Map<String, Object>>  queryByUid(String uid){
	String sql="select * from(SELECT\n" +
			"	o.id,\n" +
			"	o.total,\n" +
			"	o.state,\n" +
			"	o.uid,\n" +
			"	o.createtime,\n" +
			"	oi.pid,\n" +
			"oi.id oid,\n"+
			"	oi.count,\n" +
			"	oi.total / oi.count price,\n" +
			"  oi.total itemtotal,\n" +
			"	p.pname,\n" +
			"	p.image\n" +
			"FROM\n" +
			"	dm_orders o\n" +
			"INNER JOIN dm_orderitem oi ON o.id = oi.oid\n" +
			"JOIN dm_product p ON oi.pid = p.id\n" +
			"WHERE\n" +
			"	o.uid = ?\n" +
			"ORDER BY\n" +
			"	o.createtime DESC) a";
	return new DBHelper().query(sql, uid);
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




//public List<Map<String, Object>> querymanallOrders(){
//	
//	String sql="select * from dm_orders where uid=?";
//	List<Map<String,Object>> list=new DBHelper().query(sql, uid);
//	return list;
//}


public int update1(String id) {
	String sql="update dm_orders set state=1 where id=?";
	return new DBHelper().update(sql, id);
	
}


public int update2(String id) {
	String sql="update dm_orders set state=2 where id=? and state=1";
	return new DBHelper().update(sql, id);
	
}

public int update3(String id) {
	String sql="update dm_orders set state=3 where id=? and state=2";
	return new DBHelper().update(sql, id);
	
}

	
	
	public static void main(String[] args) {
//		new OrdersDao().insert("2");
//		new OrderItemDao().insert("2");
//		new CartDao().deleteByUid("2");
	}

}
