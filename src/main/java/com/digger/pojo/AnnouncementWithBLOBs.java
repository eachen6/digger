package com.digger.pojo;

public class AnnouncementWithBLOBs extends Announcement {
    private String introduction;

    private String content;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}