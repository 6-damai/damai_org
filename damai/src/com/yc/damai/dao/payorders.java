//package com.yc.damai.dao;
//
//
//public String payOrder() throws IOException {
//	// 1.修改数据:
//	Order currOrder = orderService.findByOid(order.getOid());
//	currOrder.setAddr(order.getAddr());
//	currOrder.setName(order.getName());
//	currOrder.setPhone(order.getPhone());
//	// 修改订单
//	orderService.update(currOrder);
//	// 2.完成付款:
//	// 付款需要的参数:
//	String p0_Cmd = "Buy"; // 业务类型:
//	String p1_MerId = "10001126856";// 商户编号:
//	String p2_Order = order.getOid().toString();// 订单编号:
//	String p3_Amt = "0.01"; // 付款金额:
//	String p4_Cur = "CNY"; // 交易币种:
//	String p5_Pid = ""; // 商品名称:
//	String p6_Pcat = ""; // 商品种类:
//	String p7_Pdesc = ""; // 商品描述:
//	String p8_Url = "http://192.168.36.69:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
//	String p9_SAF = ""; // 送货地址:
//	String pa_MP = ""; // 商户扩展信息:
//	String pd_FrpId = this.pd_FrpId;// 支付通道编码:
//	String pr_NeedResponse = "1"; // 应答机制:
//	String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
//	String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
//	p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
//	pd_FrpId, pr_NeedResponse, keyValue); // hmac
//	// 向易宝发送请求:
//	StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
//	sb.append("p0_Cmd=").append(p0_Cmd).append("&");
//	sb.append("p1_MerId=").append(p1_MerId).append("&");
//	sb.append("p2_Order=").append(p2_Order).append("&");
//	sb.append("p3_Amt=").append(p3_Amt).append("&");
//	sb.append("p4_Cur=").append(p4_Cur).append("&");
//	sb.append("p5_Pid=").append(p5_Pid).append("&");
//	sb.append("p6_Pcat=").append(p6_Pcat).append("&");
//	sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
//	sb.append("p8_Url=").append(p8_Url).append("&");
//	sb.append("p9_SAF=").append(p9_SAF).append("&");
//	sb.append("pa_MP=").append(pa_MP).append("&");
//	sb.append("pd_FrpId=").append(pd_FrpId).append("&");
//	sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
//	sb.append("hmac=").append(hmac);
//
//	//校验返回的Hmac
//	                     Boolean verify=PayUtils. verifyCallback(String hmac, String p1_MerId,
//	String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt,
//	String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid,
//	String r8_MP, String r9_BType, String keyValue);
//	                         //如果成功，则向易宝发出请求，否则保存错误信息到errorPage；
//	                  if(verify==true){
//	                         / /   重定向:向易宝出发:
//	        ServletActionContext.getResponse().sendRedirect(sb.toString());
//	                       return NONE;
//	                         }else{
//	                                  this.addActionMessage("你不是什么好人！！");
//	                                  return "msg";
//	                            }
//	}
//	// 付款成功后跳转回来的路径:
//	public String callBack(){
//	// 修改订单的状态:
//	Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
//	// 修改订单状态为2:已经付款:
//	currOrder.setState(2);
//	orderService.update(currOrder);
//	this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
//	return "msg";
//	}
//
