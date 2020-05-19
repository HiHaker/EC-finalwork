package com.ynu.soft.jianlong.youxian.entity;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-10 下午 16:11
 */
public class ShoppingItemDTO {

    private int itemId;
    // 商品图片地址
    private List<String> imgId;
    // 商品类型
    private int type;
    // 当前项单价
    private float itemPrice;
    // 商品数量
    private int number;

    public ShoppingItemDTO() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public List<String> getImgId() {
        return imgId;
    }

    public void setImgId(List<String> imgId) {
        this.imgId = imgId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ShoppingItemDTO{" +
                "itemId=" + itemId +
                ", imgId='" + imgId + '\'' +
                ", type=" + type +
                ", itemPrice=" + itemPrice +
                ", number=" + number +
                '}';
    }
}
