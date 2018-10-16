package com.digger.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digger.common.Const;
import com.digger.common.ServerResponse;
import com.digger.pojo.AnnouncementWithBLOBs;
import com.digger.service.AnnouncementService;
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
	public ServerResponse toGetAnnouncement(@PathVariable(value="pn") int pn) 
	{
		PageHelper.startPage(pn, Const.announcementcount);
		List<AnnouncementWithBLOBs> list = announcementService.toGetAnnouncement();
		PageInfo page = new PageInfo(list,Const.pagecount);
        return ServerResponse.createBySuccess(page);
	}
}
