package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;


@WebServlet("/RegServlet.do")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao udao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vcode=request.getParameter("vcode");
		// String cname=request.getParameter("cname");
		   // String password=request.getParameter("password");
		String scode=(String)request.getSession().getAttribute("vcode");
		String ename = request.getParameter("ename");
		String cname = request.getParameter("cname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
	
		String repass = request.getParameter("repass");
		
		
		if(vcode!=null&&vcode.trim().isEmpty()==false) {
			if(vcode.equalsIgnoreCase(scode)) {
				//response.getWriter().append("验证码正确！ ");
				 // Map<String,Object> user=udao.selectByLogin(cname, password);

				if (ename == null || ename.trim().isEmpty()) {
					response.getWriter().append("请填写用户名！");
				} else if (password == null || password.trim().isEmpty()) {
					response.getWriter().append("请填写密码！");
				} else if (password.equals(repass) == false) {
					response.getWriter().append("密码不一致！");
				}else {

					udao.insert(ename, cname, password, email, phone, sex);
					response.getWriter().append("用户注册成功！");
				}
			}else {
				response.getWriter().append("验证码错误！ ");
			}
		}else {
			response.getWriter().append("请输入验证码！ ");
		}
		

	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
