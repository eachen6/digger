package com.digger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 注册方法
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> registered(@RequestBody User user) {
		System.out.println(user.toString());
		if (userService.register(user) > 0) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("失败了");

	}

	/**
	 * 登陆方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(String username, String password) {
		System.out.println(123);
		System.out.println(username);
		System.out.println(password);
		User user = userService.todologin(username, password);

		if (user.getUsername().equals("")) {
			return ServerResponse.createByErrorMessage("用户名不存在！");
		} 
		else if (user.getPassword() == null) {
			return ServerResponse.createByErrorMessage("密码错误！");
		} 
		else {
			// 用户登陆
			if (user.getRole() == 0) {
				return ServerResponse.createBySuccess("niubi");
			}
			// 管理员登陆
			else if (user.getRole() == 1) {
				return ServerResponse.createBySuccess("niubi！！");
			} 
			// 客服登陆
			else if (user.getRole() == 2) {
				return ServerResponse.createBySuccess("niubi！！!");
			}
		}
		return ServerResponse.createByErrorMessage("登陆失败！");

	}

}
