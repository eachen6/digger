package com.digger.dao;

import com.digger.pojo.Announcement;
import com.digger.pojo.AnnouncementWithBLOBs;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnnouncementWithBLOBs record);

    int insertSelective(AnnouncementWithBLOBs record);

    AnnouncementWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnnouncementWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AnnouncementWithBLOBs record);

    int updateByPrimaryKey(Announcement record);
}