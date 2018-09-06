package com.digger.pojo;

import java.util.Date;

public class Payinfo {
    private Integer id;

    private Integer userid;

    private Long ordernum;

    private Byte payplatform;

    private String platformnumber;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Long ordernum) {
        this.ordernum = ordernum;
    }

    public Byte getPayplatform() {
        return payplatform;
    }

    public void setPayplatform(Byte payplatform) {
        this.payplatform = payplatform;
    }

    public String getPlatformnumber() {
        return platformnumber;
    }

    public void setPlatformnumber(String platformnumber) {
        this.platformnumber = platformnumber;
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
}