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
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> registered(@RequestBody User user){
		System.out.println(user.toString());
		if(userService.register(user)>0) {
			return ServerResponse.createBySuccess(user) ;
		}
		return ServerResponse.createByErrorMessage("失败了");
		
	}
	
	
	/**
	 * 登陆方法
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse login() {
		System.out.println(123);
		return ServerResponse.createBySuccessMessage("牛逼");
	}

}
