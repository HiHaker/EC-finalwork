package com.ynu.soft.jianlong.youxian.entity;


import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-12 下午 18:58
 */
public class OrderDTO {
    private String oid;
    // 总价
    private float totalPrice;
    // 订单状态
    private int status;
    // 下单时间
    private String orderTime;
    // 发货时间
    private String deliveryTime;
    // 收货地址
    private ShippingAddress address;
    // 订单项
    private List<ShoppingItemDTO> orderItems;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public ShippingAddress getAddress() {
        return address;
    }

    public void setAddress(ShippingAddress address) {
        this.address = address;
    }

    public List<ShoppingItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ShoppingItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oid='" + oid + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime='" + orderTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", address=" + address +
                ", orderItems=" + orderItems +
                '}';
    }
}
