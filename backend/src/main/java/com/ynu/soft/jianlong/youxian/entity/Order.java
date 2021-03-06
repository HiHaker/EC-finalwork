package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String oid;
    // 地址的id
    @Column(name = "sid")
    private int sid;
    // 总价
    @Column(name = "totalPrice")
    private float totalPrice;
    // 订单状态
    @Column(name = "status")
    private int status;
    // 下单时间
    @Column(name = "orderTime")
    private String orderTime;
    // 发货时间
    @Column(name = "deliveryTime")
    private String deliveryTime;

    public Order() {
    }

    public Order(String oid, int sid, float totalPrice, int status,
                 String orderTime, String deliveryTime) {
        this.oid = oid;
        this.sid = sid;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", sid='" + sid + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime='" + orderTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                '}';
    }
}