package com.ynu.soft.jianlong.youxian.entity;

import java.util.List;

/**
 * @Description 用于新增上架商品时所使用的对象
 * @Author Jianlong
 * @Date 2020-05-25 下午 20:09
 */
public class CommodityJson {

    private Commodity commodity;
    private List<String> imgList;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
