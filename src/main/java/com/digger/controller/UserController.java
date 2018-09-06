package com.digger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.ServerResponse;
import com.digger.pojo.User;

@Controller
@RequestMapping("/user")
public class UserController {

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
