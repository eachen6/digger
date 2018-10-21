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
import com.digger.service.UserService;

@Controller
@RequestMapping("/send")
public class SendController {

	@Autowired
	SendService sendService;
	
	@Autowired
	UserService userService;
	
	/**
     * 赠送
     * @return
     */
	@RequestMapping(value = "gift", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> toSendGame(Integer gameid, String username,float price, String message, String ordernum, HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		String targetid = null;
		targetid = userService.findIdByName(username);
		if(targetid==null)
			return ServerResponse.createByErrorMessage("未找到该用户");
		return sendService.toSendGame(gameid,user.getId(),Integer.parseInt(targetid),price,message,ordernum);
	}
	
	
	
}
