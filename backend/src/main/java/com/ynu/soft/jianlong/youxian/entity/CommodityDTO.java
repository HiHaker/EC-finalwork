package com.ynu.soft.jianlong.youxian.entity;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-04 下午 15:08
 */
public class CommodityDTO{
    // 图片地址
    private List<String> imgId;
    // 商品id
    private String cid;
    // 商品名字
    private String cname;
    // 商品价格
    private float price;
    // 商品的库存
    private int repertory;
    // 商品描述
    private String description;
    // 商品的销量（本月）
    private int saleVolume;
    // 商品类型
    private int type;

    public CommodityDTO() {
    }

    public CommodityDTO(List<String> imgId, String cid, String cname,
                        float price, int repertory, String description, int saleVolume, int type) {
        this.imgId = imgId;
        this.cid = cid;
        this.cname = cname;
        this.price = price;
        this.repertory = repertory;
        this.description = description;
        this.saleVolume = saleVolume;
        this.type = type;
    }

    public List<String> getImgId() {
        return imgId;
    }

    public void setImgId(List<String> imgId) {
        this.imgId = imgId;
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

    public int getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(int saleVolume) {
        this.saleVolume = saleVolume;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CommodityDTO{" +
                "imgId=" + imgId +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", price=" + price +
                ", repertory=" + repertory +
                ", description='" + description + '\'' +
                ", saleVolume=" + saleVolume +
                ", type=" + type +
                '}';
    }
}