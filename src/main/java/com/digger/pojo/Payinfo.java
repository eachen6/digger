package com.digger.pojo;

import java.util.Date;

public class Payinfo {
    private Integer id;

    private Integer userid;

    private Integer orderid;

    private Byte payplaform;

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

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Byte getPayplaform() {
        return payplaform;
    }

    public void setPayplaform(Byte payplaform) {
        this.payplaform = payplaform;
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