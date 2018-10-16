package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.dao.AnnouncementMapper;
import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;
import com.digger.service.AnnouncementService;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Autowired
	AnnouncementMapper announcementMapper;

	/* 
	 * 获取公告列表
	 * @author 徐子颖
	 */
	@Override
	public List<AnnouncementWithBLOBs> toGetAnnouncement() {
		// TODO Auto-generated method stub
		List<AnnouncementWithBLOBs> list = new ArrayList<AnnouncementWithBLOBs>();
		list = announcementMapper.getAllAnnouncement();
		return list;
	}
	
	
}
