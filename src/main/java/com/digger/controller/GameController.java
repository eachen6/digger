package com.digger.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digger.service.GameService;
import com.digger.common.ResponseCode;
import com.digger.common.ServerResponse;
import com.digger.pojo.Game;
import com.digger.pojo.User;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse toAddGame(HttpSession session,Game game){
		User user = (User) session.getAttribute("currentUser");
		if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
		return gameService.toAddGame(game);
	}
	
	/**
     * 获取热销轮播图
     * @return
     */
	@RequestMapping(value = "get_hotsale_carouse", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse hotSaleCarouse()
	{
		return gameService.toGetHotSaleCarouse();
	}
	
	/**
     * 获取热销游戲集合
     * @return
     */
	@RequestMapping(value = "get_hotsale_gamelist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse hotSaleGameList()
	{
		return gameService.toGetHotSaleGameList();
	}
	
}
