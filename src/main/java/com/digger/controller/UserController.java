package com.digger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;


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
	public ServerResponse<String> registered(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.register(user);

	}

	/**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login(String username, String password,HttpSession session) {
		ServerResponse<User> response = userService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute("currentUser",response.getData());
        }
        return response;
	}

}
