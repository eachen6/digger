package com.digger.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.User;
import com.digger.service.GameService;
import com.digger.vo.GameAuditVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/gameaudit")
public class GameAuditController {

	@Autowired
	GameService gameService;

	/**
	 * 根据游戏名获取待审核的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "unaudited_gamelistbyname/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse unauditedGamelistByName(@PathVariable(value = "pn") int pn, String name,
			HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.unauditedGamelistByName(name);
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}

	}

	/**
	 * 根据游戏名获取未上架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "notonthesshelf_gamelistbyname/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse notOnTheShelfGamelistByName(@PathVariable(value = "pn") int pn, String name,
			HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.notOnTheShelfGamelistByName(name);
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 根据游戏名获取已上架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "onthesshelf_gamelistbyname/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse onTheShelfGamelistByName(@PathVariable(value = "pn") int pn, String name,
			HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.onTheShelfGamelistByName(name);
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 根据游戏名获取下架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "pulloffshelves_gamelistbyname/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse pullOffShelvesGamelistByName(@PathVariable(value = "pn") int pn, String name,
			HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.pullOffShelvesGamelistByName(name);
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 获取待审核的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "unaudited_gamelist/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse unauditedGamelist(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.unauditedGamelist();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 获取未上架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "notonthesshelf_gamelist/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse notOnTheShelfGamelist(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.notOnTheShelfGamelist();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 获取已上架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "onthesshelf_gamelist/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse onTheShelfGamelist(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.onTheShelfGamelist();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 获取下架的游戏列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "pulloffshelves_gamelist/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse pullOffShelvesGamelist(@PathVariable(value = "pn") int pn, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.gamecount);
				List<GameAuditVO> list = gameService.pullOffShelvesGamelist();
				PageInfo page = new PageInfo(list, Const.pagecount);
				return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 根据id审核游戏
	 * 
	 * @return
	 */
	@RequestMapping(value = "audit_game", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse auditGame(Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return gameService.auditGame(id);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}

	}

	/**
	 * 根据id上架游戏
	 * 
	 * @return
	 */
	@RequestMapping(value = "onthesshelf_game", method = RequestMethod.POST)
	@ResponseBody
    public ServerResponse onThesShelfGame(Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return gameService.onThesShelfGame(id);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}

	/**
	 * 根据id下架游戏
	 * 
	 * @return
	 */
	@RequestMapping(value = "pulloffshelves_game", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse pullOffShelvesGame(Integer id, HttpSession session) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return gameService.pullOffShelvesGame(id);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}

	}
	
	/**
     * 根据订单id将游戏删除
     * @return
     */
	@RequestMapping(value = "delete_game", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toDeleteGame(Integer id,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				return gameService.toDeleteGame(id);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}
	
}
