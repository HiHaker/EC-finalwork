package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.*;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Entity
@Table(name = "shoppingitem")
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cid")
    private String cid;
    @Column(name = "uid")
    private String uid;
    // 状态
    @Column(name = "status")
    private int status;
    // 数量
    @Column(name = "number")
    private int number;
    // 价格
    @Column(name = "itemPrice")
    private float itemPrice;

    public ShoppingItem() {
    }

    public ShoppingItem(String cid, String uid, int status, int number, float itemPrice) {
        this.cid = cid;
        this.uid = uid;
        this.status = status;
        this.number = number;
        this.itemPrice = itemPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", status=" + status +
                ", number=" + number +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
