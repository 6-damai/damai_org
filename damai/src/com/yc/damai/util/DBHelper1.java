package com.yc.damai.util;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper1 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//注册驱动 加载驱动
	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //获取连接对象
	public Connection getConn() throws SQLException {
		conn =DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),MyProperties.getInstance());
		return conn;
	}
	//关闭资源
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(null!=rs) {
				rs.close();
			}
			if(null!=pstmt) {
				pstmt.close();
			}
			if(null!=conn) {
				conn.close();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	
	public int update(List<String>sqls,List<List<Object>> params) throws SQLException {
		int result=0;
		try {
			conn=getConn();
			conn.setAutoCommit(false);
			for(int i=0;i<sqls.size();i++) {
				String sql=sqls.get(i);
				List<Object>param=params.get(i);
				pstmt=conn.prepareStatement(sql);
				setParams(pstmt,param);
				result=pstmt.executeUpdate();
				if(result<=0) {
					conn.rollback();
					return result;
				}
				
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			result=0;
			e.printStackTrace();
		}finally {
			conn.setAutoCommit(true);
			closeAll(conn,pstmt,null);
		}
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Map<String,Object>> findMutipl(String sql,List<Object>params) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object>map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			List<String>columnNames=getColumnNames(rs);
			while(rs.next()) {
				map=new HashMap<String,Object>();
				for(String name:columnNames) {
					Object obj=rs.getObject(name);
					if(null==obj) {
						continue;
					}
					String typeName=obj.getClass().getName();
					if("oracle.sql.BLOB".equals(typeName)) {
						Blob blob=(Blob)obj;
						InputStream in=blob.getBinaryStream();
						byte[]bt=new byte[(int)blob.length()];
						in.read(bt);
						map.put(name, bt);
						
					}else {
						map.put(name,rs.getObject(name));
						
					}
				}
				list.add(map);
			}
			
		} finally {
			closeAll(conn,pstmt,rs);
		}
		return list;
		
		
	}
	
	public Map<String,Object> findSingle(String sql,List<Object>params) throws Exception{

		Map<String,Object>map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			List<String>columnNames=getColumnNames(rs);
			if(rs.next()) {
				map=new HashMap<String,Object>();
				for(String name:columnNames) {
					Object obj=rs.getObject(name);
					if(null==obj) {
						continue;
					}
					String typeName=obj.getClass().getName();
					if("oracle.sql.BLOB".equals(typeName)) {
						Blob blob=(Blob)obj;
						InputStream in=blob.getBinaryStream();
						byte[]bt=new byte[(int)blob.length()];
						in.read(bt);
						map.put(name, bt);
						
					}else {
						map.put(name,rs.getObject(name));
						
					}
				}
				
			}
			
		} finally {
			closeAll(conn,pstmt,rs);
		}
		return map;
		
		
	}
	private List<String>getColumnNames(ResultSet rs) throws SQLException{
		List<String> columnNames=new ArrayList<String>();
		ResultSetMetaData data=rs.getMetaData();
		int count=data.getColumnCount();
		for(int i=1;i<=count;i++) {
			columnNames.add(data.getColumnName(i));
			
		}
		return columnNames;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int update(String sql,List<Object>params) throws SQLException {
		int result=0;
		try {
			conn =getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			result=pstmt.executeUpdate();
			
		}finally {
			closeAll(conn,pstmt,null);
		}
		return result;
	}
	private void setParams (PreparedStatement pstmt,List<Object>params) throws SQLException {
		if(null==params ||params.size()<=0) {
			return;
		}
		for(int i=0;i<params.size();i++) {
			pstmt.setObject(i+1, params.get(i));
		}
	}
	
	public double getPolymer(String sql,List<Object>params) throws SQLException {
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt,params);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getDouble(1);
			}
		} finally {
			closeAll(conn,pstmt,rs);
		}
		return 0;
		
		
	}

}
