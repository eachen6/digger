package com.digger.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
