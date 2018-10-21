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
import com.mysql.fabric.Server;

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
	
	/**
	 * @author eachen
	 * @param session
	 * 获取新增的好友请求
	 * @return
	 */
	@RequestMapping(value = "get_Friend_invite", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse get_Friend_invite(HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return friendService.get_Friend_invite(user.getId());
	}
	
	/**
	 * @author eachen
	 * @param session
	 * @param username
	 * 建立好友关系
	 * @return
	 */
	@RequestMapping(value = "make_friendship", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse make_friendship(HttpSession session,String username) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		else if(username == null) {
			return ServerResponse.createByErrorMessage("好友名称不可为空");
		}
		return friendService.make_friendship(user.getId(), username);
	}
	
	/**
	 * @author eachen
	 * @param session
	 * @return
	 * 获取向我申请了好友的用户列表
	 */
	@RequestMapping(value = "receive_Friend_invite", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse receive_Friend_invite(HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return friendService.get_Friend_invite(user.getId());
	}
	
	/**
	 * @author eachen
	 * @param session
	 * @param friendID
	 * 根据好友id改变friend关系表中的好友状态，并建立我与好友的关系
	 * @return
	 */
	@RequestMapping(value = "pass_Friend_invite", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse pass_Friend_invite(HttpSession session,Integer friendID) {
		User user = (User)session.getAttribute(Const.CURRENT_USER);
		if(user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		System.out.println(friendID);
		return friendService.pass_Friend_invite(user.getId(), friendID);
	}
	
	/**
	 * @author eachen
	 * @param session
	 * @param friendID
	 * 删除好友通知
	 * @return
	 */
	@RequestMapping(value = "delete_Friend_invite", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse delete_Friend_invite(HttpSession session,Integer friendID) {
		User user = (User)session.getAttribute(Const.CURRENT_USER);
		if(user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return friendService.deleteUnSureInvite(user.getId(), friendID);
	}
	
}
