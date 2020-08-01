package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;

@WebServlet("/ProductServlet.do")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao pdao= new ProductDao();
	
       
  
	protected void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sql="select * from dm_product where is_hot=1 order by id desc limit 0,10";
		List<?> list=new DBHelper().query(sql);
		HashMap<String,Object> page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	
	
	protected void query2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		DmProduct dp=new DmProduct();
		BeanUtils.populate(dp, request.getParameterMap());
		String sql="select * from dm_product ";
		List<?> list=pdao.query1(dp,page, rows);
		int total=pdao.count1(dp);
		HashMap<String,Object> data=new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		print(response,data);
	}
	
	
	protected void query1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sql="select * from dm_product order by createtime desc limit 0,10";
		List<?> list=new DBHelper().query(sql);
		HashMap<String,Object> page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	
	
	
	protected void queryclist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		String sql="select * from dm_product where cid=?";
		List<?> list=new DBHelper().query(sql,cid);
		HashMap<String,Object> page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	
	protected void queryById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		String sql="select * from dm_product where id=?";
		
		
		List<?> list=new DBHelper().query(sql,id);
		
		print(response,list.get(0));
	}
	
	
	protected void queryclistitem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		String pid=request.getParameter("pid");
		String sql="select * from dm_product where cid=? and pid=?";
		List<?> list=new DBHelper().query(sql,cid);
		HashMap<String,Object> page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	
	


	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmProduct dp=new DmProduct();
		BeanUtils.populate(dp, request.getParameterMap());
		if(dp.getPname()==null||dp.getPname().trim().isEmpty()) {
			print(response,new Result(0,"商品名称不能为空！"));
			return;
			
		}
		if(dp.getShopPrice()==null||dp.getShopPrice()<=0) {
			print(response,new Result(0,"商品商品价格必须大于0！"));
			return;
			
		}
		if(dp.getId() == null || dp.getId() == 0) {
			pdao.insert(dp);
		} else {
			pdao.update(dp);
		}
		print( response, new Result(1,"商品保存成功!"));
	}
	
	protected void queryCate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * from dm_category where pid is null";
		List<?> list = new DBHelper().query(sql);
		HashMap<String, Object> page = new HashMap<>();
		page.put("list", list);
		print(response, page);
	}
	
	protected void category(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * from(SELECT\n" + "	a.id csid,\n" + "	a.cname NAME,\n" + "	a.pid cid,\n"
				+ "	b.cname cname\n" + "FROM\n" + "	dm_category a\n" + "JOIN dm_category b ON a.pid = b.id\n"
				+ "WHERE\n" + "a.pid IS NOT NULL\n" + ") a  ORDER BY a.cid";
		List<?> list = new DBHelper().query(sql);
		HashMap<String, Object> page = new HashMap<>();
		page.put("list", list);
		print(response, page);
	}
	
	// 分页查询
	protected void FYquery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String page = request.getParameter("page");
		int iPage = 1;// 第几页
		if (page != null && page.trim().isEmpty() == false) {
			iPage = Integer.valueOf(page);
		}
		List<Map<String, Object>> list = pdao.queryPage(cid, iPage, 12);// 查询页号和每页行数

		int pages = pdao.countPages(cid, 12);// 总页数
		// 使用？对象保存list和pages ==>
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("list", list);
		data.put("pages", pages);

		Gson gson = new Gson();
		System.out.println(data);
		String json = gson.toJson(data);

		System.out.println(json);
		response.getWriter().append(json);

	}
}
