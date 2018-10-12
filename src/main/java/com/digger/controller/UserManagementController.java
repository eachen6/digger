package com.digger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserManagementController {
	@Autowired
	UserService userService;
	
	/*
	 * 由于还没写管理员登陆页面，暂时不进行登陆验证
	 */
	
	/**
	 * 获取用户列表
	 * @return
	 */
	/*@RequestMapping(value = "get_total_userlist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getTotalUserList(HttpSession session){
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null)
			return ServerResponse.createByErrorMessage("用户未登录");
		return userService.getTotalUserList();
	}*/
	@RequestMapping(value = "get_total_userlist", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getTotalUserList(){
		return userService.getTotalUserList();
	}
	
	/**
	 * 修改用户封禁状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updatestatebyid", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updateStateById(Integer id,Integer state){
		return userService.updateStateById(id,state);
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteuserbyid", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse deleteUserById(Integer id){
		return userService.deleteUserById(id);
	}
	
	/**
	 * 按照用户名搜索
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "selectuserbyusername", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse selectUserByUsername(String username){
		return userService.selectUserByUsername(username);
	}
	
	/**
	 * 根据id排序
	 * @return
	 */
	@RequestMapping(value = "sortbyid", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse sortById(){	
		return userService.sortById();
	}
	
	/**
	 * 根据状态排序
	 * @return
	 */
	@RequestMapping(value = "sortbystate", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse sortByState(){	
		return userService.sortByState();
	}
	
	/**
	 * 管理员修改密码
	 * @param passwordOld
	 * @param passwordNew
	 * @param passwordRepeat
	 * @return
	 */
	@RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updatePassword(String username,String passwordOld,String passwordNew,String passwordRepeat){
		return userService.updatePassword(username,passwordOld,passwordNew,passwordRepeat);
	}

}
