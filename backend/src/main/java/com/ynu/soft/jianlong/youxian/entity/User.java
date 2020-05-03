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
    // 用户的注册日期
    @Column(name = "registrationDate")
    private String registrationDate;

    public User() {
    }

    public User(String uid, String registrationDate) {
        this.uid = uid;
        this.registrationDate = registrationDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}
