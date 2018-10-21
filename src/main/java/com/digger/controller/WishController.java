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
import com.digger.service.WishService;

@Controller
@RequestMapping("/wish")
public class WishController {
	
	@Autowired
	WishService wishService;
	
	/**
     * 获取该游戏是否被当前用户加入愿望清单
     * @return
     */
	@RequestMapping(value = "get_wishgame", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetWishGame(Integer gameid,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		return wishService.toGetWishGame(gameid,user.getId());
	}
	
	/**
     * 将游戏添加到愿望清单
     * @return
     */
	@RequestMapping(value = "add_wishgame", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toAddWishGame(Integer gameid,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		return wishService.toAddWishGame(gameid,user.getId());
	}
	
	/**
     * 根据用户id和游戏id将游戏从愿望清单删除
     * @return
     */
	@RequestMapping(value = "delete_wishgame", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toDeleteWishGame(@RequestParam(value="gameid") int gameid,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER); 
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		return wishService.toDeleteWishGame(gameid,user.getId());
	}
	
	/**
     * 查看自己的愿望清单
     * @return
     */
	@RequestMapping(value = "get_myself_wishgame", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetMyselfWishGame(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return wishService.toGetMyselfWishGame(user.getId());
	}
	
	/**
     * 查看好友的愿望清单
     * @return
     */
	@RequestMapping(value = "get_friend_wishgame", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetFriendWishGame(Integer userid) 
	{
		return wishService.toGetFriendWishGame(userid);
	}
	
}
