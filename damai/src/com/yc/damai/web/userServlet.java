package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.DmUser;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;

@WebServlet("/userServlet.do")
public class userServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao pdao= new ProductDao();
	
       
  


	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmUser du=new DmUser();
		BeanUtils.populate(du, request.getParameterMap());
		if(du.getEname()==null||du.getEname().trim().isEmpty()) {
			print(response,new Result(0,"用户名称不能为空！"));
			return;
			
		}
//		if(dp.getShopPrice()==null||dp.getShopPrice()<=0) {
//			print(response,new Result(0,"商品商品价格必须大于0！"));
//			return;
//			
//		}
//		if(dp.getId() == null || dp.getId() == 0) {
//			pdao.insert(dp);
//		} else {
//			pdao.update(dp);
//		}
		print( response, new Result(1,"商品保存成功!"));
	}
	

}

