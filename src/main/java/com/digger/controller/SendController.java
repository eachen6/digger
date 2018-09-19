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
import com.digger.service.SendService;

@Controller
@RequestMapping("/send")
public class SendController {

	@Autowired
	SendService sendService;
	
	/**
     * 赠送
     * @param friendint
     * @param price
     * @return
     */
	@RequestMapping(value = "gift", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> toSendGame(int gameid, int targetid,float price, String message, HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return sendService.toSendGame(gameid,user.getId(),targetid,price,message);
	}
}
