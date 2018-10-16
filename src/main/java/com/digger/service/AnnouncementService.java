package com.digger.service;

import java.util.List;

import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;

public interface AnnouncementService {

	List<AnnouncementWithBLOBs> toGetAnnouncement();

}
