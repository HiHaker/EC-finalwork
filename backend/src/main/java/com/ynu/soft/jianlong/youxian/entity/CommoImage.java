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
@Table(name = "commimage")
public class CommoImage {
    @Id
    private String id;
    @Column(name = "cid")
    private String cid;

    public CommoImage() {
    }

    public CommoImage(String id, String cid) {
        this.id = id;
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "CommoImage{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
