package com.yc.damai.dao;

import java.util.List;
import java.util.Map;

import com.yc.damai.util.DBHelper;



public class adminDao {
	public Map<String,Object> selectByLogin(String username,String password) {
		String sql="select * from dm_adminuser where username=? and password=?";
		DBHelper dbh=new DBHelper();
		List<Map<String,Object>> list=dbh.query(sql, username,password);
		if(list.size()==0) {
			return null;
		}else {
			 Map<String,Object>   user=list.get(0);
			 return user;
		}
	
	}

}
