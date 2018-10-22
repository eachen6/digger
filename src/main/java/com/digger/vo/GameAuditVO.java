package com.digger.vo;

import java.util.Date;

public class GameAuditVO {
	private Integer id;

    private String name;

    private String category;

    private String label;
    
    private String carouseurl;

    private String bgurl;

    private String coverurl;

    private String videourl;

    private String opstage;

    private String developer;

    private String oper;

    private String platform;

    private String memory;

    private String grcard;

    private String cd;

    private String cpu;

    private String system;

    private String driver;

    private Integer state;

    private Integer click;

    private Integer sale;

    private Date createtime;

    private Date updatetime;

    private Float price;

    private Date shelftime;

    private Integer uploadid;

    private Byte discountstate;

    private String detailtxt;

    private String username;
    
    private String surfaceurl;
    
    public String getSurfaceurl() {
		return surfaceurl;
	}

	public void setSurfaceurl(String surfaceurl) {
		this.surfaceurl = surfaceurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCarouseurl(String carouseurl) {
		this.carouseurl = carouseurl;
	}

	
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBgurl() {
        return bgurl;
    }

    public void setBgurl(String bgurl) {
        this.bgurl = bgurl;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getOpstage() {
        return opstage;
    }

    public void setOpstage(String opstage) {
        this.opstage = opstage;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGrcard() {
        return grcard;
    }

    public void setGrcard(String grcard) {
        this.grcard = grcard;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getShelftime() {
        return shelftime;
    }

    public void setShelftime(Date shelftime) {
        this.shelftime = shelftime;
    }

    public Integer getUploadid() {
        return uploadid;
    }

    public void setUploadid(Integer uploadid) {
        this.uploadid = uploadid;
    }

    public Byte getDiscountstate() {
        return discountstate;
    }

    public void setDiscountstate(Byte discountstate) {
        this.discountstate = discountstate;
    }

    public String getDetailtxt() {
        return detailtxt;
    }

    public void setDetailtxt(String detailtxt) {
        this.detailtxt = detailtxt;
    }
    
    public String getCarouseurl() {
        return carouseurl;
    }

    public void setCarouseur(String carouseurl) {
        this.carouseurl = carouseurl;
    }
}
