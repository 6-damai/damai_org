package com.yc.damai.dao;

import java.util.List;
import java.util.Map;


import com.yc.damai.po.DmUser;
import com.yc.damai.util.DBHelper;



public class UserDao {
	public void insert(String ename,String cname,String password,String email,String phone,String sex) {
		String sql="insert into dm_user values(null,?,?,?,?,?,?,?,now())";
		DBHelper dbh=new DBHelper();
		dbh.update(sql, ename,cname,password,email,phone,sex,1);
	}
	
	
	public void update(String ename,String cname,String password,String email,String phone,String sex) {
		String sql="update dm_user set(null,?,?,?,?,?,?,?,now())";
		DBHelper dbh=new DBHelper();
		dbh.update(sql, ename,cname,password,email,phone,sex,1);
	}
	
	public Map<String,Object> selectByLogin(String ename, String password) {
		String sql = "select * from dm_user where ename=? and password=?";
		DBHelper dbh = new DBHelper();
		List<Map<String, Object>> list = dbh.query(sql, ename, password);
		if (list.size() == 0) {
			return null;
		} else {
			Map<String, Object> user = list.get(0);
			return user;
		}
	//	return dbh.count(sql, ename, password)>0;

	}

public int queryId(Map<String, Object> loginedUser) {
    	
    	String name=loginedUser.get("ename").toString();
    	String pwd=loginedUser.get("password").toString();
    	String sql="select id from dm_user where ename=? and password=?";
    	return  (int) new DBHelper().query(sql,name,pwd).get(0).get("id");
    }


public String queryename(Map<String, Object> loginedUser) {
	
	String loginname=loginedUser.get("ename").toString();
	
	return   loginname;
}
/**
 * 查询用户信息
 */

public List<Map<String,Object>>  queryall(Map<String, Object> loginedUser) {
	
	String name=loginedUser.get("ename").toString();
	String pwd=loginedUser.get("password").toString();
	String sql="select * from dm_user where ename=? and password=?";
	return   new DBHelper().query(sql,name,pwd);
}


public void update(DmUser du) {
	String sql = "update dm_user set "
			+ "ename=?,"
			+ "email=?,"
			+ "phone=?,"
			+ "cname=?,"
			+ "pdesc=?,"
			+ "sex= ?,"
			
			+ " where id=?";
	new DBHelper().update(sql
			,du.getEname()
			,du.getEmail()
			,du.getPhone()
			,du.getCname()
			,du.getSex()
			
			,du.getId());
}







}
