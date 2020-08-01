package com.yc.damai.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.adminDao;



@WebServlet("/adminloginServlet.do")
public class adminloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private adminDao adao=new adminDao();   

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username=request.getParameter("username");
		    String password=request.getParameter("password");
		    Map<String,Object> user=adao.selectByLogin(username, password);
		    if(user !=null) {
		    	request.getSession().setAttribute("loginedUser", username);
		    	response.getWriter().print("登录成功");
		    }else {
		    	response.getWriter().print("用户名或密码错误");
		    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
