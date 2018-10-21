package com.digger.service;

import java.util.List;

import com.digger.common.ServerResponse;
import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;
import com.digger.vo.AnnouncementVO;

public interface AnnouncementService {

	List<AnnouncementVO> toGetAnnouncement();

	ServerResponse toUpdateAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs);

	ServerResponse toDeleteAnnouncement(Integer id);

	ServerResponse toAddAnnouncement(AnnouncementWithBLOBs announcementWithBLOBs);

	List<Announcement> toGetAnnouncementByGameid(Integer gameid);

	AnnouncementWithBLOBs toGetAnnouncementById(Integer id);

}
