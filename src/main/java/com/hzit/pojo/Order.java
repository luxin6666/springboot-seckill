package com.hzit.pojo;

import java.util.Date;

public class Order {
    private String orderNo;

    private String userId;

    private String recName;

    private String recAddr;

    private String recPhone;

    private String postAge;

    private Double price;

    private Date createTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName == null ? null : recName.trim();
    }

    public String getRecAddr() {
        return recAddr;
    }

    public void setRecAddr(String recAddr) {
        this.recAddr = recAddr == null ? null : recAddr.trim();
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone == null ? null : recPhone.trim();
    }

    public String getPostAge() {
        return postAge;
    }

    public void setPostAge(String postAge) {
        this.postAge = postAge == null ? null : postAge.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}