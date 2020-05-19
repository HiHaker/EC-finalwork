package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.*;
import com.ynu.soft.jianlong.youxian.repository.CommoImageRepository;
import com.ynu.soft.jianlong.youxian.repository.CommodityRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderItemRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-04 下午 15:06
 */
@Service
public class CommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommoImageRepository commoImageRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CommonService commonService;

    /**
     * 检查参数的合法性
     * @param cid
     */
    private Commodity checkArg(String cid){
        Commodity commodity = commodityRepository.findById(cid).orElse(null);
        if (commodity == null){
            throw new IllegalArgumentException("商品id不存在!");
        }
        else{
            return commodity;
        }
    }

    /**
     * 上架新商品
     * @param commodity 商品对象
     */
    public void addCommodity(Commodity commodity){
        // 参数检查
        if (commodity.getCid().equals("")
                || commodity.getCname().equals("")
                || commodity.getRepertory() <=0
                || commodity.getPrice() < 0
                || commodity.getType() < 1
                || commodity.getType() > 5){
            throw new IllegalArgumentException("商品参数非法，新增失败!");
        }

        commodityRepository.save(commodity);
    }

    /**
     * 下架商品
     * @param cid 商品id
     */
    public void deleteCommodity(String cid){
        // 检查商品id
        Commodity commodity = checkArg(cid);
        commodity.setDelete(true);
        commodityRepository.save(commodity);
    }

    /**
     * 获取商品的月销量
     * @param cid 商品id
     * @return
     */
    private int getMonthSaleVolume(String cid){

        checkArg(cid);
        int salesVolume = 0;

        // 查找已经完成的订单
        List<Order> orderList = orderRepository.findByStatus(4);

        // 筛选出本月的订单
        orderList.removeIf(o -> !commonService.isInMonth(o.getOrderTime()));

        for (Order o : orderList){
            String oid = o.getOid();
            List<OrderItem> items = orderItemRepository.findByOid(oid);

            // 筛选出该商品
            items.removeIf(item -> !item.getCid().equals(cid));

            for (OrderItem item : items){
                salesVolume += item.getNumber();
            }
        }

        return salesVolume;
    }

    /**
     * 转换为DTO对象
     * @param c
     * @return
     */
    private CommodityDTO transferDTO(Commodity c){

        CommodityDTO cd = new CommodityDTO();

        String cid = c.getCid();
        cd.setCid(cid);
        cd.setCname(c.getCname());
        cd.setPrice(c.getPrice());
        cd.setRepertory(c.getRepertory());
        cd.setDescription(c.getDescription());
        cd.setType(c.getType());
        cd.setImgId(commonService.getImgList(cid));
        cd.setSaleVolume(getMonthSaleVolume(cid));
        return cd;
    }

    /**
     * 获取整个商品列表
     * @return
     */
    private List<CommodityDTO> getAllCommodities(){
        // 获取整个商品列表
        // 未被删除的
        List<Commodity> commodities = commodityRepository.findByDelete(false);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();

        // 对于每个商品，构造一个DTO对象
        for (Commodity c : commodities){
            commodityDTOS.add(transferDTO(c));
        }

        return commodityDTOS;
    }

    /**
     * 返回系统中销量前十的商品（首页推荐）
     * @return
     */
    public List<CommodityDTO> getRecommendCommodities(){

        List<CommodityDTO> commodityDTOList = getAllCommodities();

        // 根据月销量进行排序
        commodityDTOList.sort(Comparator.comparingInt(CommodityDTO::getSaleVolume));

        if (commodityDTOList.size() > 10){
            return commodityDTOList.subList(0,10);
        }
        else{
            return commodityDTOList;
        }
    }

    /**
     * 根据类别返回所有商品
     * @param type
     * @return
     */
    public List<CommodityDTO> getCommoditiesByType(int type){

        if (type < 1 || type > 5){
            throw new IllegalArgumentException("类型参数非法！");
        }

        // 获取整个商品列表
        // 未被删除的
        List<Commodity> commodities = commodityRepository.findByTypeAndDelete(type, false);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();

        // 对于每个商品，构造一个DTO对象
        for (Commodity c : commodities){
            commodityDTOS.add(transferDTO(c));
        }

        return commodityDTOS;
    }

    /**
     * 商品详情
     * @param cid 商品id
     * @return
     */
    public CommodityDTO getCommodity(String cid){

        Commodity commodity = checkArg(cid);

        return transferDTO(commodity);
    }

    /**
     * 获取所有的商品信息（管理端）
     * @return
     */
    public List<Commodity> getAllCommoditiesM(){
        return commodityRepository.findAll();
    }

    /**
     * 编辑商品（管理端）
     * @param commodity
     */
    public void updateCommodity(Commodity commodity){

//        Commodity old = commodityRepository.findById(commodity.getCid()).orElse(null);
        checkArg(commodity.getCid());

        // 参数检查
        if (commodity.getCname().equals("")
                || commodity.getRepertory() <0
                || commodity.getPrice() < 0
                || commodity.getType() < 1
                || commodity.getType() > 5){
            throw new IllegalArgumentException("商品参数非法，更新失败!");
        }

        commodityRepository.save(commodity);
    }
}