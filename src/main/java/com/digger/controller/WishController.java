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
import com.digger.service.WishService;

@Controller
@RequestMapping("/wish")
public class WishController {
	
	@Autowired
	WishService wishService;
	
	/**
     * 获取游戏是否加入清单
     * @return
     */
	@RequestMapping(value = "get_wishgame", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetWishGame(int gameid,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		//0为加入愿望清单，1为未加入愿望清单
		if(user == null){
			return ServerResponse.createBySuccess(1);
		}
		return wishService.toGetWishGame(gameid);
	}
}
