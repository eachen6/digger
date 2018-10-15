package com.digger.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.OrderService;

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
	public ServerResponse<String> toCreateOrder(int gameid,float price,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return orderService.toCreateOrder(user.getId(),gameid,price);
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
	
	
}
