package com.ynu.soft.jianlong.youxian.entity;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-01 下午 19:48
 */
public class OrderJson1 {

    private String uid;
    private List<Integer> itemList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Integer> getItemList() {
        return itemList;
    }

    public void setItemList(List<Integer> itemList) {
        this.itemList = itemList;
    }
}
