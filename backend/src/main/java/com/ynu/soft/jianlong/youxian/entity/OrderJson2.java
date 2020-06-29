package com.ynu.soft.jianlong.youxian.entity;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-01 下午 19:49
 */
public class OrderJson2 {

    private Order order;
    private List<Integer> itemList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Integer> getItemList() {
        return itemList;
    }

    public void setItemList(List<Integer> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "OrderJson2{" +
                "order=" + order +
                ", itemList=" + itemList +
                '}';
    }
}
