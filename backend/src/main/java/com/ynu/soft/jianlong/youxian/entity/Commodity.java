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
@Table(name = "Commodity")
public class Commodity {
    @Id
    private String cid;
    // 商品名
    @Column(name = "cname")
    private String cname;
    // 商品价格
    @Column(name = "price")
    private float price;
    // 商品库存
    @Column(name = "repertory")
    private int repertory;
    // 商品描述
    @Column(name = "description")
    private String description;
    // 商品类型
    @Column(name = "type")
    private int type;

    public Commodity() {
    }

    public Commodity(String cname, float price, int repertory, String description, int type) {
        this.cname = cname;
        this.price = price;
        this.repertory = repertory;
        this.description = description;
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRepertory() {
        return repertory;
    }

    public void setRepertory(int repertory) {
        this.repertory = repertory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", price=" + price +
                ", repertory=" + repertory +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
