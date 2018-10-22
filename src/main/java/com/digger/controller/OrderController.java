package com.digger.controller;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.digger.common.AlipayConfig;
import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.Order;
import com.digger.pojo.User;
import com.digger.service.OrderService;
import com.digger.vo.CarouseVO;
import com.digger.vo.OrderVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	/**
     * 生成订单
     * @param id
     * @return
     */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toCreateOrder(Order order,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		System.out.println(order.getPrice());
		order.setUserid(user.getId());
		//重新开始检查一遍是否已购买了该游戏
		ServerResponse sr = orderService.isBuy_Order(order.getGameid(),user.getId());
		if(sr.getStatus()==0)
			return ServerResponse.createByErrorMessage("用户已购买该游戏");
		return orderService.toCreateOrder(order);
	}
	
	/**
     * 查看订单
     * @return
     */
	@RequestMapping(value = "get_order", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetOrder(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toGetOrder(user.getId());
	}
	
	/**
     * 查看我的游戏包含别人赠送的
     * @return
     */
	@RequestMapping(value = "get_mygame", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetMyGame(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toGetMyGame(user.getId());
	}
	
	/**
     * 根据订单id将订单删除
     * @return
     */
	@RequestMapping(value = "delete_order", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toDeleteOrder(Integer id,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toDeleteOrder(id);
	}
	
	/**
	 * author 高志劲
     * 取消订单退款
     * @return
     */
	@RequestMapping(value = "cancel_order", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toCancelOrder(Integer id,HttpSession session)
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toCancelOrder(id);
	}
	
	/**
	 * author 高志劲
     * 申请退款
     * @return
     */
	@RequestMapping(value = "apply_refund", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse applyRefund(Integer id,HttpSession session)
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toApplyRefund(id);
	}
	
	/**
	 * author 高志劲
     * 查询该游戏是否已被用户购买
     * @return
     */
	@RequestMapping(value = "isbuy_order", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse IsBuy_Order(@RequestParam(value="gameid") int gameid,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录");
		}
		return orderService.isBuy_Order(gameid,user.getId());
	}
	
	
	/**
	 * author 高志劲
    * 进行支付宝付款
    * @return
	 * @throws AlipayApiException 
    */
	@RequestMapping(value = "goAlipay", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String goAlipay(@RequestParam(value="ordernum") String ordernum,HttpSession session) throws AlipayApiException 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		
		Order order = orderService.getOrderByOrdernum(ordernum);
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = ordernum;
		//付款金额，必填
		String total_amount =String.valueOf(order.getPrice());
		//订单名称，必填
		String subject = Const.subject;
		//商品描述，可空
		String body = "用户订购商品个数：" + 1;

		// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
		String timeout_express = "5m";

		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout_express +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("执行到这里-------------------------");
		return result;
	}
	
	
	/**
	 *
	 * @Title: AlipayController.java
	 * @Package com.sihai.controller
	 * @Description: 支付宝同步通知页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *
	 * @author sihai 高志劲改
	 * @date 2017年8月23日 下午8:51:01
	 * @version V1.0
	 */
	@RequestMapping(value = "/alipayReturnNotice")
	public String alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		System.out.println("支付成功, 进入同步通知接口...");

		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			// 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
			orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);
		}else {
			System.out.println("购买失败!!!!!!!!!!");
		}

		return "paysuccess";
	}

	/**
	 *
	 * @Title: AlipayController.java
	 * @Package com.sihai.controller
	 * @Description: 支付宝异步 通知页面
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *
	 * @author sihai  高志劲改
	 * @date 2017年8月23日 下午8:51:13
	 * @version V1.0
	 */
	@RequestMapping(value = "/alipayNotifyNotice")
	public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

		System.out.println("支付成功, 进入异步通知接口...");

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知

				// 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
				orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);

				/*Orders order = orderService.getOrderById(out_trade_no);
				Product product = productService.getProductById(order.getProductId());

				log.info("********************** 支付成功(支付宝异步通知) **********************");
	    		log.info("* 订单号: {}", out_trade_no);
	    		log.info("* 支付宝交易号: {}", trade_no);
	    		log.info("* 实付金额: {}", total_amount);
	    		log.info("* 购买产品: {}", product.getName());
	    		log.info("***************************************************************");*/
			}
			System.out.println("支付成功...");

		}else {//验证失败
			System.out.println("支付, 验签失败...");
		}

		return "paysuccess";
	}
	
	
	/**
	 * author 高志劲
    * 进行支付宝退款
    * @return
	 * @throws AlipayApiException 
	 * @throws UnsupportedEncodingException 
    */
	@RequestMapping(value = "goRefund")
	@ResponseBody
	public ServerResponse goRefund(String ordernum, String price, HttpSession session) throws AlipayApiException, UnsupportedEncodingException 
	{
		/*String ordernum = "20181019112854703";
		String price = "0.4";*/
		String reason = "就是想退款";	
		System.out.println(reason);
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		if(!orderService.goRefund(ordernum))
		    return ServerResponse.createByErrorMessage("退款失败");
		
	/*	//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradeFastpayRefundQueryRequest  alipayRequest = new AlipayTradeFastpayRefundQueryRequest ();
		
		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(ordernum.getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
		//请二选一设置
		//需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = new String(price.getBytes("ISO-8859-1"),"UTF-8");
		//退款的原因说明
		String refund_reason = new String(reason.getBytes("ISO-8859-1"),"UTF-8");
		//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		//String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"trade_no\":\""+ trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"," 
				+ "\"out_request_no\":\""+ out_request_no +"\"}");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"}");
		
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(alipayRequest);
		if(response.isSuccess()){
		   System.out.println("调用成功");
		   return ServerResponse.createBySuccessMessage("退款成功");
		} else {
		   System.out.println("调用失败");
		   return ServerResponse.createByErrorMessage("退款失败");
		}
		//请求
		String result = alipayClient.execute(alipayRequest).getBody();
		
		//输出
		return result;*/
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		
		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(ordernum.getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
		//请二选一设置
		//需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = new String(price.getBytes("ISO-8859-1"),"UTF-8");
		//退款的原因说明
		String refund_reason = new String(reason.getBytes("ISO-8859-1"),"UTF-8");
		//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		//String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");
		
		request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"refund_amount\":\""+ refund_amount +"\"," 
				+ "\"refund_reason\":\""+ refund_reason +"\"}");
		
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		return ServerResponse.createBySuccessMessage("退款成功"); 
		} else {
		System.out.println("调用失败");
		   return ServerResponse.createByErrorMessage("退款失败");

		}
	}
	
	/**
	 * author 高志劲
     * 取消订单退款
     * @return
     */
	@RequestMapping(value = "getrefund/{pn}", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toGetRefund(@PathVariable(value="pn") int pn,HttpSession session)
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}	
		//startPage是PageHelper的静态方法，参数1：默认开始页面，参数2：每页显示数据的条数
        PageHelper.startPage(pn, Const.gamecount);
        //从当前类下注入的业务层实现类userService中调用方法，该方法所在类利用注入的userDao来调用真正的查询方法查询数据库信息。
        List<OrderVO> list = orderService.toGetRefund();
        //使用PageInfo包装查询页面，封装了详细的分页信息.第二个参数表示连续显示的页数
        PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
	
	/**
	 * author 高志劲
     * 不同意退款
     * @return
     */
	@RequestMapping(value = "disagreerefund", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse disagreeRefund(String ordernum,HttpSession session)
	{
		System.out.println(ordernum+"yyyyyyyyyyyyyyyyyyy");
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}	
        return orderService.disagreeRefund(ordernum);
	}
	
}
