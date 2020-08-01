package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.CategoryDao;
import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;

@WebServlet("/CategoryServlet.do")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao cdao= new CategoryDao();
    
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<?> list=cdao.query();
		print(response,list);
	}
	
	protected void query2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		DmCategory dc=new DmCategory();
		BeanUtils.populate(dc, request.getParameterMap());
		String sql="select * from dm_category ";
		List<?> list=cdao.query1(dc,page, rows);
		int total=cdao.count1(dc);
		HashMap<String,Object> data=new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		print(response,data);
	}
	protected void queryclist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		List<Map<String,Object>> list=cdao.queryclist(id);
		print(response,list);
	}
	
	
	protected void queryByFirstCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid=request.getParameter("pid");
		List<Map<String,Object>> list=cdao.queryclist(pid);
		print(response,list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmCategory dc=new DmCategory();
		BeanUtils.populate(dc, request.getParameterMap());
		if(dc.getCname()==null||dc.getCname().trim().isEmpty()) {
			print(response,new Result(0,"商品名称不能为空！"));
			return;
		}
		if(dc.getId() == null || dc.getId() == 0) {
			cdao.insert(dc);
		} else {
			cdao.update(dc);
		}
		print( response, new Result(1,"商品保存成功!"));
	}
	
	protected void query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<?> list=cdao.queryByNull();
		print(response,list);
	}
	/**
	protected void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		String pid=request.getParameter("pid");
		List<?> list = cdao.change(pid);
		print(response,list);
	}
	*/

}
