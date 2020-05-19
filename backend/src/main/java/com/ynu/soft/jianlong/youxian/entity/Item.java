package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.Column;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-12 下午 19:15
 */
public class Item {
    private int id;
    private String cid;
    private String uid;
    private int number;

    public Item() {
    }

    public Item(String cid, String uid, int number) {
        this.cid = cid;
        this.uid = uid;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
