package com.digger.pojo;

import java.util.Date;

public class Send {
    private Integer id;

    private Integer targetid;

    private Date createtime;

    private Date updatetime;

    private Long ordernum;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
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

    public Long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Long ordernum) {
        this.ordernum = ordernum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}