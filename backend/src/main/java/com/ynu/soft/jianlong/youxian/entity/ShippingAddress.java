package com.ynu.soft.jianlong.youxian.entity;

import javax.persistence.*;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-03 下午 21:13
 */
@Entity
@Table(name = "shippingaddress")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;
    // 用户id
    @Column(name = "uid")
    private String uid;
    // 收货人的名字
    @Column(name = "name")
    private String name;
    // 练习方式
    @Column(name = "telephone")
    private String telephone;
    // 收货人地址
    @Column(name = "address")
    private String address;
    // 是否是默认地址
    @Column(name = "isDefault")
    private boolean isDefault;
    // 地址是否被删除
    @Column(name = "isDelete")
    private boolean isDelete;

    public ShippingAddress() {
    }

    public ShippingAddress(String uid, String name, String telephone, String address, boolean isDefault) {
        this.uid = uid;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.isDefault = isDefault;
    }

    public Integer getSid() {
        return sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "sid=" + sid +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                ", isDelete=" + isDelete +
                '}';
    }
}
