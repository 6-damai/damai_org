package com.yc.damai.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;



@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private UserDao udao=new UserDao();   

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vcode=request.getParameter("vcode");
		 String ename=request.getParameter("ename");
		    String password=request.getParameter("password");
		String scode=(String)request.getSession().getAttribute("vcode");
		  Map<String,Object> user=udao.selectByLogin(ename, password);
		if(vcode!=null&&vcode.trim().isEmpty()==false) {
			if(vcode.equalsIgnoreCase(scode)) {
				//response.getWriter().append("验证码正确！ ");
				 // Map<String,Object> user=udao.selectByLogin(ename, password);
				    if(user !=null) {
				    	request.getSession().setAttribute("loginedUser", user);
				    	response.getWriter().print("登录成功");
				    }else {
				    	response.getWriter().print("用户名或密码错误");
				    }
			}else {
				response.getWriter().append("验证码错误！ ");
			}
		}else {
			response.getWriter().append("请输入验证码！ ");
		}
		
		  
		 //   if(udao.selectByLogin(ename, password)&&)
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
