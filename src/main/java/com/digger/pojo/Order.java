package com.digger.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer gameid;

    private Integer userid;

    private String coverimg;

    private Float price;

    private Date createtime;

    private Date updatetime;

    private Byte state;

    private Date paytime;

    private Byte issend;

    private Date closetime;

    private Long ordernum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Byte getIssend() {
        return issend;
    }

    public void setIssend(Byte issend) {
        this.issend = issend;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public Long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Long ordernum) {
        this.ordernum = ordernum;
    }
}