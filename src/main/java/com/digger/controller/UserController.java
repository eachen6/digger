package com.digger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

import com.digger.common.Const;
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
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login(String username, String password, HttpSession session) {
		ServerResponse<User> response = userService.login(username, password);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		return response;
	}

	/**
	 * 查询密保问题
	 * 
	 * @param username
	 * @param type（为判断传入的是username还是email）
	 * @return
	 */
	@RequestMapping(value = "question", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> question(String username, String type, HttpSession session) {
		session.setAttribute("qtype", type);

		System.out.println((String) session.getAttribute("qtype"));
		return userService.selectQuestion(username, type);
	}

	/**
	 * 校验密保答案
	 * 
	 * @param username
	 * @param type（为判断传入的是username还是email）
	 * @return
	 */
	@RequestMapping(value = "answer", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> answer(String username, String question, String answer, HttpSession session) {
		String type = (String) session.getAttribute("qtype");
		return userService.checkAnswer(username, question, answer, type);
	}

	/**
	 * 忘记密码
	 * 
	 * @param username
	 * @param type（为判断传入的是username还是email）
	 * @return
	 */
	@RequestMapping(value = "password", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> password(String username, String passwordNew, String forgetToken,
			HttpSession session) {
		String type = (String) session.getAttribute("qtype");
		return userService.forgetResetPassword(username, passwordNew, forgetToken, type);
	}

	/**
	 * 修改密码
	 * 
	 * @param passwordOld
	 * @param passwordNew
	 * @return
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> updatePassword(HttpSession session, String passwordOld, String passwordNew) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorMessage("用户未登录");
		}
		return userService.updatePassword(passwordOld, passwordNew, user);
	}

	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccess();
	}
}
