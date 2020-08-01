package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.util.DBHelper;

public class OrderItemDao {
	public int insert(String uid) {
		String sql="INSERT INTO dm_orderitem \n" +
				"SELECT\n" +
				"NULL,\n" +
				"a.count,\n" +
				"a.count*b.shop_price,\n" +
				"a.pid,\n" +
				"(SELECT MAX(id) FROM dm_orders)\n" +
				"FROM\n" +
				"dm_cart a\n" +
				"JOIN dm_product b ON a.pid=b.id\n" +
				"WHERE uid=?";
		return new DBHelper().update(sql, uid);
	}
	
	public List<?> queryByOid(String oid){
		String sql="select * from dm_orderitem a join dm_product b on a.pid=b.id where oid=?";
		return new DBHelper().query(sql, oid);
	}
	
	
//	public static void main(String[] args) {
//		new OrderItemDao().insert("2");
//	}

}
