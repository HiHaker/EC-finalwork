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
    @Column(name = "uid")
    private String uid;
    // 总价
    @Column(name = "totalPrice")
    private float totalPrice;
    // 订单状态
    @Column(name = "status")
    private int status;
    // 提货人电话
    @Column(name = "telephone")
    private String telephone;
    // 提货地点
    @Column(name = "address")
    private String address;
    // 下单时间
    @Column(name = "orderTime")
    private String orderTime;
    // 发货时间
    @Column(name = "deliveryTime")
    private String deliveryTime;
    // 折扣
    @Column(name = "discount")
    private float discount;

    public Order() {
    }

    public Order(String oid, String uid, float totalPrice, int status,
                 String telephone, String address, String orderTime,
                 String deliveryTime, float discount) {
        this.oid = oid;
        this.uid = uid;
        this.totalPrice = totalPrice;
        this.status = status;
        this.telephone = telephone;
        this.address = address;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", discount=" + discount +
                '}';
    }
}
