package com.ynu.soft.jianlong.youxian.entity;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-01 下午 20:10
 */
public class StatusJson {

    private int waitingPay;
    private int waitingDelivery;
    private int waitingReceipt;
    private int finished;

    public int getWaitingPay() {
        return waitingPay;
    }

    public void setWaitingPay(int waitingPay) {
        this.waitingPay = waitingPay;
    }

    public int getWaitingDelivery() {
        return waitingDelivery;
    }

    public void setWaitingDelivery(int waitingDelivery) {
        this.waitingDelivery = waitingDelivery;
    }

    public int getWaitingReceipt() {
        return waitingReceipt;
    }

    public void setWaitingReceipt(int waitingReceipt) {
        this.waitingReceipt = waitingReceipt;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
