package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.*;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-20 下午 22:18
 */
@Entity
@Table(name = "order_msg")
public class OrderMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "oid")
    private String oid;
    @Column(name = "uid")
    private String uid;
    @Column(name = "deliveryTime")
    private String deliveryTime;
    @Column(name = "isRead")
    private boolean isRead;
    @Column(name = "isDelete")
    private boolean isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "OrderMsg{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", isRead=" + isRead +
                ", isDelete=" + isDelete +
                '}';
    }
}
