package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.*;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Entity
@Table(name = "shoppingitem")
public class ShoppingItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cid")
    private String cid;
    @Column(name = "uid")
    private String uid;
    // 数量
    @Column(name = "number")
    private int number;

    public ShoppingItem() {
    }

    public ShoppingItem(String cid, String uid, int number) {
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

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", number=" + number +
                '}';
    }
}
