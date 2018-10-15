package com.digger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.UserService;
import com.digger.utils.DbUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
			/*if(checkSession(username)){ 
			      //如果该session在此之前已经存在，则将该用户进行退出操作 
			      DbUtil.userLogout(username);
			      return ServerResponse.createByErrorMessage("该用户正在上线");
			    } 
			    //将新的session存放到map<username,session>中 
			    DbUtil.mapSession.put(username, session);*/
			    return response;
		}
		return ServerResponse.createByErrorMessage("登陆失败");
	}

	/**
	 * @author 徐子颖
	 * 检查是否已经含有此session
	 * @return
	 */
	private boolean checkSession(String username){ 
		  HttpSession session = DbUtil.mapSession.get(username); 
		  if(session!=null){ 
		    return true; 
		  } 
		  return false; 
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
	
	/**
	 * 更新用户个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> update(@RequestBody User user, HttpSession session)
	{
		/*
		 * 测试用例User currentUser = new User();
		currentUser.setId(7);
		currentUser.setEmail("00");
		currentUser.setQuestion("00");
		currentUser.setUsername("00");*/
		User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        ServerResponse<User> response = userService.updateInformation(user, currentUser);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
	}
	
	/**
	 * 检查用户是否已登录，点击“更新个人信息”之前验证
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "check_state", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> check_state(HttpSession session)
	{
		User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return ServerResponse.createBySuccessMessage("用户已登录");
	}
	
	/**
	 * @author eachen
	 * 获取用户信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "get_userinfo", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getUserInfo(HttpSession session){
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null)
			return ServerResponse.createByErrorMessage("用户未登录");
		return userService.getUserInfo(user.getId());
	}
	
	
	/**
	 * @author gzj
	 * 分页测试
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "pagetest", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getUser(@RequestParam(value="pn",defaultValue="1") Integer pn){
		//startPage是PageHelper的静态方法，参数1：默认开始页面，参数2：每页显示数据的条数
        PageHelper.startPage(pn, 2);
        //从当前类下注入的业务层实现类userService中调用方法，该方法所在类利用注入的userDao来调用真正的查询方法查询数据库信息。
        List<User> allUser = userService.getAllUser();
        System.out.println(allUser);
        //使用PageInfo包装查询页面，封装了详细的分页信息.第二个参数表示连续显示的页数
        PageInfo page = new PageInfo(allUser,5);
        return ServerResponse.createBySuccess(page);
	}
	
}
