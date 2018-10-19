package com.digger.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.UserService;
import com.digger.vo.GameAuditVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class UserManagementController {
	@Autowired
	UserService userService;

	/**
	 * 获取用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "get_total_userlist/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse getTotalUserList(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<User> list = userService.getTotalUserList();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 修改用户封禁状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updatestatebyid", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updateStateById(Integer id, Integer state, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return userService.updateStateById(id, state);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteuserbyid", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse deleteUserById(Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return userService.deleteUserById(id);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 按照用户名搜索
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "selectuserbyusername", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse selectUserByUsername(String username, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return userService.selectUserByUsername(username);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}

	}

	/**
	 * 根据id排序
	 * 
	 * @return
	 */
	@RequestMapping(value = "sortbyid/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse sortById(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<User> list = userService.sortById();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 根据状态排序
	 * 
	 * @return
	 */
	@RequestMapping(value = "sortbystate/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse sortByState(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<User> list = userService.sortByState();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 管理员修改密码
	 * 
	 * @param passwordOld
	 * @param passwordNew
	 * @param passwordRepeat
	 * @return
	 */
	@RequestMapping(value = "updatepassword", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updatePassword(String username, String passwordOld, String passwordNew,
			String passwordRepeat, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return userService.updatePassword(username, passwordOld, passwordNew, passwordRepeat);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

}
