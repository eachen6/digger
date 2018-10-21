package com.digger.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digger.common.ServerResponse;
import com.digger.dao.AnnouncementMapper;
import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;
import com.digger.service.AnnouncementService;
import com.digger.vo.AnnouncementVO;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Autowired
	AnnouncementMapper announcementMapper;

	/* 
	 * 获取公告列表
	 * @author 徐子颖
	 */
	@Override
	public List<AnnouncementVO> toGetAnnouncement() {
		// TODO Auto-generated method stub
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		list = announcementMapper.getAllAnnouncement();
		return list;
	}

	/* 
	 * 修改公告
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toUpdateAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs) {
		// TODO Auto-generated method stub
		int resultCount = announcementMapper.updateByPrimaryKeyWithBLOBs(announcementWithBLOBs);
		if(resultCount>0){
			return ServerResponse.createBySuccessMessage("公告修改成功！");
		}
		return ServerResponse.createByErrorMessage("公告修改失败！");
	}

	/* 
	 * 删除公告
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toDeleteAnnouncement(Integer id) {
		// TODO Auto-generated method stub
		int resultCount = announcementMapper.deleteByPrimaryKey(id);
		if(resultCount>0){
			return ServerResponse.createBySuccessMessage("公告删除成功！");
		}
		return ServerResponse.createByErrorMessage("公告删除失败！");
	}	

	/* 
	 * 增加公告
	 * @author 徐子颖
	 */
	@Override
	public ServerResponse toAddAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs) {
		// TODO Auto-generated method stub
		int resultCount = announcementMapper.insert(announcementWithBLOBs);
		if(resultCount>0){
			return ServerResponse.createBySuccessMessage("公告增加成功！");
		}
		return ServerResponse.createByErrorMessage("公告增加失败！");
	}

	/* 
	 * 根據游戲id获取公告列表
	 * @author 徐子颖
	 */
	@Override
	public List<Announcement> toGetAnnouncementByGameid(Integer gameid) {
		// TODO Auto-generated method stub
		List<Announcement> list = new ArrayList<Announcement>();
		list = announcementMapper.toGetAnnouncementByGameid(gameid);
		return list;
	}

	/* 
	 * 根據公告id获取公告列表
	 * @author 徐子颖
	 */
	@Override
	public AnnouncementWithBLOBs toGetAnnouncementById(Integer id) {
		// TODO Auto-generated method stub
		AnnouncementWithBLOBs announcement = announcementMapper.selectByPrimaryKey(id);
		return announcement;
	}
	
	
}
