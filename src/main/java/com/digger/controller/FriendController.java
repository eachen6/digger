package com.digger.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.FriendService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	/**
     * 获取好友列表
     * @param id
     * @return
     */
	@RequestMapping(value = "get_personal_friendlist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse personalFriendList(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return friendService.toGetPersonalFriendList(user.getId());
	}
	
	@RequestMapping(value = "getFriendlist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getFriendlist(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			//return ServerResponse.createByErrorMessage("用户未登录");
		}
		return friendService.getPersonalFriends(user.getId());
	}
}
