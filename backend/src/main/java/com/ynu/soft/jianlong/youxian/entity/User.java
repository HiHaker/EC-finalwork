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
@Table(name = "user")
public class User {
    @Id
    private String uid;
    // 提货人的姓名
    @Column(name = "name")
    private String name;
    // 用户的注册日期
    @Column(name = "registrationDate")
    private String registrationDate;
    // 提货人的地址
    @Column(name = "address")
    private String address;
    // 提货人的电话
    @Column(name = "telephone")
    private String telephone;

    public User() {
    }

    public User(String uid, String name, String registrationDate, String address, String telephone) {
        this.uid = uid;
        this.name = name;
        this.registrationDate = registrationDate;
        this.address = address;
        this.telephone = telephone;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
