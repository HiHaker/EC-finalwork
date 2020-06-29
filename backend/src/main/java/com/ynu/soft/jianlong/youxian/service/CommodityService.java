package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.*;
import com.ynu.soft.jianlong.youxian.repository.CommoImageRepository;
import com.ynu.soft.jianlong.youxian.repository.CommodityRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderItemRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 检查cid参数的合法性
     * @param cid 商品id
     */
    private Commodity checkCid(String cid){
        Commodity commodity = commodityRepository.findByCidAndIsDelete(cid, false);
        if (commodity == null){
            throw new IllegalArgumentException("ERROR:商品id不存在!");
        }
        else{
            return commodity;
        }
    }

    /**
     * 检查参数合法性
     * @param cname 商品名
     * @param price 商品价格
     * @param repertory 商品库存
     * @param description 商品描述
     * @param type 商品类型
     */
    private void checkArg(String cname, float price, int repertory, String description, int type){
        if (cname.equals("")){
            throw new IllegalArgumentException("ERROR:商品名为空!");
        }
        else if (description.equals("")){
            throw new IllegalArgumentException("ERROR:商品描述为空!");
        }
        else if (repertory <= 0){
            throw new IllegalArgumentException("ERROR:商品库存不能小于等于0!");
        }
        else if (price <= 0){
            throw new IllegalArgumentException("ERROR:商品价格不能小于0!");
        }
        else if (type < 1 || type > 5){
            throw new IllegalArgumentException("ERROR:商品类型参数错误!");
        }
    }

    /**
     * 上架新商品
     * @param commodityJson 商品对象
     */
    public void addCommodity(CommodityJson commodityJson){

        // 参数检查
        if (commodityJson == null){
            throw new IllegalArgumentException("ERROR:传入对象为空!");
        }

        Commodity commodity = commodityJson.getCommodity();

        if (commodity == null){
            throw new IllegalArgumentException("ERROR:传入对象里的商品对象为空!");
        }

        if (commodityRepository.findByCidAndIsDelete(commodity.getCid(), false) != null){
            throw new IllegalArgumentException("ERROR:商品id存在,无法新增!");
        }
        checkArg(commodity.getCname(), commodity.getPrice(), commodity.getRepertory(), commodity.getDescription(), commodity.getType());

        // 先存储商品
        commodityRepository.save(commodity);

        List<String> imgList = commodityJson.getImgList();
        if (imgList == null){
            throw new IllegalArgumentException("ERROR:传入对象里的图片对象为空!");
        }

        // 再存储图片
        for (String img : imgList){
            commoImageRepository.save(new CommoImage(img, commodity.getCid()));
        }
    }

    /**
     * 下架商品
     * @param cid 商品id
     */
    public void deleteCommodity(String cid){
        // 检查商品id
        Commodity commodity = checkCid(cid);
        commodity.setDelete(true);
        commodityRepository.save(commodity);
    }

    /**
     * 获取商品的月销量
     * @param cid 商品id
     * @return 商品销量
     */
    private int getMonthSaleVolume(String cid){

        checkCid(cid);
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
     * @param c 商品对象
     * @return 商品数据传输对象列表
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
     * @return 商品数据传输对象列表
     */
    private List<CommodityDTO> getAllCommodities(){
        // 获取整个商品列表
        // 未被删除的
        List<Commodity> commodities = commodityRepository.findByIsDelete(false);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();

        // 对于每个商品，构造一个DTO对象
        for (Commodity c : commodities){
            commodityDTOS.add(transferDTO(c));
        }

        return commodityDTOS;
    }

    /**
     * 返回系统中销量前十的商品（首页推荐）
     * @return 商品数据传输对象列表
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
     * @param type 商品类型
     * @return 商品数据传输对象列表
     */
    public List<CommodityDTO> getCommoditiesByType(int type){

        if (type < 1 || type > 5){
            throw new IllegalArgumentException("类型参数非法！");
        }

        // 获取整个商品列表
        // 未被删除的
        List<Commodity> commodities = commodityRepository.findByTypeAndIsDelete(type, false);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();

        // 对于每个商品，构造一个DTO对象
        for (Commodity c : commodities){
            commodityDTOS.add(transferDTO(c));
        }

        // 根据月销量进行一个排序
        commodityDTOS.sort(Comparator.comparingInt(CommodityDTO::getSaleVolume));

        return commodityDTOS;
    }

    /**
     * 商品详情
     * @param cid 商品id
     * @return 商品数据传输对象
     */
    public CommodityDTO getCommodity(String cid){

        Commodity commodity = checkCid(cid);

        return transferDTO(commodity);
    }

    /**
     * 编辑商品（管理端）
     * @param commodityJson 商品数据传输对象
     */
    @Transactional
    public void updateCommodity(CommodityJson commodityJson){

        // 参数检查
        Commodity commodity = commodityJson.getCommodity();
        if (commodity == null){
            throw new IllegalArgumentException("ERROR:商品对象为空!");
        }

        String cid = commodity.getCid();
        checkCid(cid);
        checkArg(commodity.getCname(), commodity.getPrice(), commodity.getRepertory(), commodity.getDescription(), commodity.getType());

        List<String> imgList = commodityJson.getImgList();
        if (imgList == null || imgList.size() == 0){
            throw new IllegalArgumentException("ERROR:图片参数错误!");
        }

        // 先删除图片，再进行更新
        commoImageRepository.deleteByCid(cid);

        for (String img : imgList){
            commoImageRepository.save(new CommoImage(img, cid));
        }

        commodityRepository.save(commodity);
    }
}