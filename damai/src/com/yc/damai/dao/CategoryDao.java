package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmProduct;
import com.yc.damai.util.DBHelper;

public class CategoryDao {
	
	public List<?> query(){
		String sql="select * from dm_category ";
		return new DBHelper().query(sql);
	}
	
	
	public List<Map<String,Object>> queryclist(String id){
		String sql="select * from  dm_category   where id=? ";
		return new DBHelper().query(sql,id);
	}
	
	public List<Map<String,Object>> queryByFirstCate(String pid){
		String sql="select * from  dm_category where pid=? ";
		return new DBHelper().query(sql,pid);
	}
	
	public void update(DmCategory dc) {
		String sql = "update dm_category set "
				+ "cname=?,"
				+ "pid=? "
				+ " where id=?";
		new DBHelper().update(sql, dc.getCname(), dc.getPid(), dc.getId());
	}

	public List<Map<String,Object>> query1(DmCategory dc,String page,String rows){
		String where="";
		List<Object> params=new ArrayList<>();
		if(dc.getCname()!=null && dc.getCname().trim().isEmpty()==false) {
			where+=" and cname like ?";
			params.add("%"+dc.getCname()+"%");
			
		}
		
		if(dc.getPid()!=null ) {
			where+=" and pid= ?";
			params.add(dc.getPid());
			
		}
		int ipage=Integer.parseInt(page);
		int irows=Integer.parseInt(rows);
		ipage=(ipage-1)*20;
		String sql="SELECT * FROM dm_category WHERE 1=1 "+where+" limit ?,?";
		//return new DBHelper().query(sql, ipage,irows);
		params.add(ipage);
		params.add(irows);
		return new DBHelper().query(sql, params.toArray());
	}
	
	
	public int count1(DmCategory dc) {
		String where="";
		List<Object> params=new ArrayList<>();
		if(dc.getCname()!=null && dc.getCname().trim().isEmpty()==false) {
			where+=" and cname like ?";
			params.add("%"+dc.getCname()+"%");
			
		}
		if(dc.getPid()!=null ) {
			where+=" and pid= ?";
			params.add(dc.getPid());
			
		}
		String sql="select null from dm_category where 1=1"+where;
		return new DBHelper().count(sql,params.toArray());
	}

	public List<Map<String,Object>> insert(DmCategory dc){
		String sql="insert into dm_category values(null,?,?)";
		return new DBHelper().query(sql,dc.getCname(), dc.getPid());
	}
	
	public List<?> queryByNull(){
		String sql="select * from dm_category where pid is null";
		return new DBHelper().query(sql);
	}
}
