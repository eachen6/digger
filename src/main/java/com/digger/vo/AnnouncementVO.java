package com.digger.vo;

import java.util.Date;

public class AnnouncementVO {
	 private Integer id;

	    private Integer gameid;

	    private String imgurl;

		private String title;

		private Date createtime;

	    private Date updatetime;
	    
	    private String introduction;

	    private String content;
	    
	    private String name;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	    
	    public Integer getGameid() {
	        return gameid;
	    }

	    public void setGameid(Integer gameid) {
	        this.gameid = gameid;
	    }

	    public String getImgurl() {
	        return imgurl;
	    }

	    public void setImgurl(String imgurl) {
	        this.imgurl = imgurl;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public Date getCreatetime() {
	        return createtime;
	    }

	    public void setCreatetime(Date createtime) {
	        this.createtime = createtime;
	    }

	    public Date getUpdatetime() {
	        return updatetime;
	    }

	    public void setUpdatetime(Date updatetime) {
	        this.updatetime = updatetime;
	    }
	    
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
