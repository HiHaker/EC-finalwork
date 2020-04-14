package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.*;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cid")
    private String cid;
    @Column(name = "oid")
    private String oid;
    // 数量
    @Column(name = "number")
    private int number;
    // 总价
    @Column(name = "itemPrice")
    private float itemPrice;

    public OrderItem() {
    }

    public OrderItem(String cid, String oid, int number, float itemPrice) {
        this.cid = cid;
        this.oid = oid;
        this.number = number;
        this.itemPrice = itemPrice;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
        return "OrderItem{" +
                "cid='" + cid + '\'' +
                ", oid='" + oid + '\'' +
                ", number=" + number +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
