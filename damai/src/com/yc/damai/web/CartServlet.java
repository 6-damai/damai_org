package com.yc.damai.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.UserDao;
import com.yc.damai.util.DBHelper;

@WebServlet("/CartServlet.do")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CartDao cdao=new CartDao();
	private UserDao udao=new UserDao();

  
	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uid = udao.queryId((Map<String, Object>)request.getSession().getAttribute("loginedUser")) + "";
	    String pid=request.getParameter("pid");
	    if(cdao.update(uid, pid)==0) {
	    	cdao.insert(uid, pid);
	    }
		response.getWriter().append("{\"msg\":\"购物车添加成功！\"}");
	}
	
	protected void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uid = udao.queryId((Map<String, Object>)request.getSession().getAttribute("loginedUser")) + "";
	   List<?> list=cdao.queryByUid(uid);
	   print(response,list);
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uid = udao.queryId((Map<String, Object>)request.getSession().getAttribute("loginedUser")) + "";
	    if(cdao.deleteByUid(uid)>0) {

			response.getWriter().append("{\"msg\":\"购物车qingk成功！\"}");
	    	
	    }
	}
	
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
