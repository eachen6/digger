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
import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;
import com.digger.pojo.User;
import com.digger.service.AnnouncementService;
import com.digger.vo.AnnouncementVO;
import com.digger.vo.GameAuditVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
	
	@Autowired
	AnnouncementService announcementService;
	
	/**
     * 获取公告列表
     * @return
     */
	@RequestMapping(value = "get_announcement/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetAnnouncement(@PathVariable(value="pn") int pn, HttpSession session) 
	{
        User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.announcementcount);
				List<AnnouncementVO> list = announcementService.toGetAnnouncement();
				PageInfo page = new PageInfo(list,Const.pagecount);
		        return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}
	
	/**
     * 修改公告
     * @return
     */
	@RequestMapping(value = "update_announcement", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toUpdateAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		else{
			if(user.getRole()==1){
				return announcementService.toUpdateAnnouncement(announcementWithBLOBs);
			}
			else{
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
		
	}
	
	/**
     * 删除公告
     * @return
     */
	@RequestMapping(value = "delete_announcement", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toDeleteAnnouncement(Integer id,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		else{
			if(user.getRole()==1){
				return announcementService.toDeleteAnnouncement(id);
			}
			else{
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
		
	}
	
	/**
     * 增加公告
     * @return
     */
	@RequestMapping(value = "add_announcement", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse toAddAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs,HttpSession session) 
	{
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if(user == null){
			return ServerResponse.createByErrorCodeMessage(1,"用户未登录"); //返回一个1代表用户未登陆
		}
		else{
			if(user.getRole()==1){
				return announcementService.toAddAnnouncement(announcementWithBLOBs);
			}
			else{
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
		
	}
	
	/**
     * 根據游戲id获取公告列表
     * @return
     */
	@RequestMapping(value = "get_announcementbygameid/{pn}", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetAnnouncement(@PathVariable(value="pn") int pn, Integer gameid, HttpSession session) 
	{
        User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) {
				PageHelper.startPage(pn, Const.announcementcount);
				List<Announcement> list = announcementService.toGetAnnouncementByGameid(gameid);
				PageInfo page = new PageInfo(list,Const.pagecount);
		        return ServerResponse.createBySuccess(page);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}
	
	/**
     * 根據公告id获取公告列表
     * @return
     */
	@RequestMapping(value = "get_announcementbyid", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse toGetAnnouncementById(Integer id, HttpSession session) 
	{
        User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(1, "用户未登录"); // 返回一个1代表用户未登陆
		} else {
			if (user.getRole() == 1) { 
				AnnouncementWithBLOBs announcement = announcementService.toGetAnnouncementById(id);
				return ServerResponse.createBySuccess(announcement);
			} else {
				return ServerResponse.createByErrorMessage("无权限!");
			}
		}
	}
}
