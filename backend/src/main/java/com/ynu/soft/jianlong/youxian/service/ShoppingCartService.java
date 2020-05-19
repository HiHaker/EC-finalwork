package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.*;
import com.ynu.soft.jianlong.youxian.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-10 上午 11:53
 */
@Service
public class ShoppingCartService {

    @Autowired
    ShoppingItemRepository shoppingItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommoImageRepository commoImageRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ShippingAddressRepository shippingAddressRepository;

    @Autowired
    CommonService commonService;

    /**
     * 检查uid
     * @param uid 用户id
     */
    private void checkArgUid(String uid){
        if (userRepository.findById(uid).orElse(null) == null){
            throw new IllegalArgumentException("参数非法!uid不存在!");
        }
    }

    /**
     * 检查cid
     * @param cid 商品id
     */
    private void checkArgCid(String cid){
        if (commodityRepository.findById(cid).orElse(null) == null){
            throw new IllegalArgumentException("参数非法!cid不存在!");
        }
    }

    /**
     * 检查商品数量
     * @param number 商品数量
     */
    private void checkArgNumber(int number){
        if (number < 1){
            throw new IllegalArgumentException("参数非法!购买的商品的数量不能小于1!");
        }
    }

    /**
     * 获取购物车中的商品类别数
     * @param uid 用户的id
     * @return 购物项的数量
     */
    public int getItemNumber(String uid){
        checkArgUid(uid);
        return shoppingItemRepository.findByUid(uid).size();
    }

    /**
     * 转换为DTO对象
     * @param itemId 购物项的id
     * @param cid 商品的id
     * @param number 购物项的商品的数量
     * @return 购物项的数据传输对象
     */
    private ShoppingItemDTO transferDTO(int itemId, String cid, int number){

        ShoppingItemDTO itemDTO = new ShoppingItemDTO();

        itemDTO.setItemId(itemId);
        itemDTO.setNumber(number);
        itemDTO.setItemPrice(Objects.requireNonNull(commodityRepository.findById(cid).orElse(null)).getPrice());
        itemDTO.setType(Objects.requireNonNull(commodityRepository.findById(cid).orElse(null)).getType());
        itemDTO.setImgId(commonService.getImgList(cid));
        return itemDTO;
    }

    /**
     * 返回购物车列表
     * @param uid 用户id
     * @return 购物项数据传输对象的列表
     */
    public List<ShoppingItemDTO> getShoppingItems(String uid){

        // 检查uid的合法性（外键）
        checkArgUid(uid);

        List<ShoppingItem> items = shoppingItemRepository.findByUid(uid);
        List<ShoppingItemDTO> itemDTOS = new ArrayList<>();

        for (ShoppingItem item : items){
            itemDTOS.add(transferDTO(item.getId(), item.getCid(), item.getNumber()));
        }

        return itemDTOS;
    }

    /**
     * 添加数量为number的商品到购物车
     * @param uid 用户id
     * @param cid 商品id
     */
    public void addItem(String uid, String cid, int number){

        ShoppingItem item = shoppingItemRepository.findByCidAndUid(cid, uid);

        if (item == null){
            item = new ShoppingItem();

            checkArgUid(uid);
            item.setUid(uid);
            checkArgCid(cid);
            item.setCid(cid);
            checkArgNumber(number);
            item.setNumber(number);
            shoppingItemRepository.save(item);
        } else{
            // 数量加number
            item.setNumber(item.getNumber()+number);
        }
    }

    /**
     * 修改购物项的参数
     * @param itemId 购物项id
     * @param number 修改的数量
     */
    public void updateItem(int itemId, int number){

        ShoppingItem item = shoppingItemRepository.findById(itemId).orElse(null);

        if (item == null){
            throw new IllegalArgumentException("itemId不存在，不存在此购物项!");
        }
        else{
            item.setNumber(number);
            shoppingItemRepository.save(item);
        }
    }

    /**
     * 删除对应的购物车项
     * @param itemId 购物项id
     */
    public void deleteItem(int itemId){
        shoppingItemRepository.deleteById(itemId);
    }

    /**
     * 获取订单（点击结算按钮后）
     * @param uid 用户id
     * @param itemList 选中的购物项的列表
     * @return 返回订单的数据传输对象
     */
    public OrderDTO getOrder(String uid, List<Integer> itemList){

        OrderDTO orderDTO = new OrderDTO();

        checkArgUid(uid);
        // 首先获取默认地址
        ShippingAddress address = shippingAddressRepository.findByUidAndIsDefault(uid, true);
        List<ShoppingItemDTO> orderItems = new ArrayList<>();

        float totalPrice = 0;

        for (Integer itemId : itemList){

            ShoppingItem item = shoppingItemRepository.findById(itemId).orElse(null);

            if (item == null){
                throw new IllegalArgumentException("itemId不存在，不存在此购物项!");
            }

            ShoppingItemDTO itemDTO = transferDTO(itemId, item.getCid(), item.getNumber());

            totalPrice += itemDTO.getItemPrice() * item.getNumber();

            orderItems.add(itemDTO);
        }

        orderDTO.setAddress(address);
        orderDTO.setTotalPrice(totalPrice);
        orderDTO.setOrderItems(orderItems);

        return orderDTO;
    }

    /**
     * 生成订单
     * @param order 订单
     * @param itemList 购物项id列表
     */
    public void generateOrder(Order order, List<Integer> itemList){

        // check
        if (order == null){
            throw new IllegalArgumentException("参数order为null");
        }

        if (orderRepository.findById(order.getOid()).orElse(null) != null){
            throw new IllegalArgumentException("oid已存在!");
        }

        if (shippingAddressRepository.findById(order.getSid()).orElse(null) == null){
            throw new IllegalArgumentException("sid不存在，不存在该地址!");
        }

        if (order.getStatus() < 1 || order.getStatus() > 4){
            throw new IllegalArgumentException("状态参数错误!");
        }

        if (!commonService.timeFormatIsCorrect(order.getOrderTime())){
            throw new IllegalArgumentException("时间日期格式错误!");
        }

        // 存储订单
        orderRepository.save(order);

        // 存储订单项
        String oid = order.getOid();
        for (Integer itemId : itemList){

            ShoppingItem item = shoppingItemRepository.findById(itemId).orElse(null);

            if (item == null){
                throw new IllegalArgumentException("itemId不存在，不存在此购物项!");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOid(oid);
            orderItem.setCid(item.getCid());
            orderItem.setNumber(item.getNumber());
            // 加入到订单项
            orderItemRepository.save(orderItem);
            // 删除购物项
            shoppingItemRepository.deleteById(itemId);
        }

    }

    /**
     * 将订单转换为订单的数据传输对象（根据id查看订单详情）
     * @param order 订单
     * @return
     */
    private OrderDTO transferDTO(Order order){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOid(order.getOid());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setDeliveryTime(order.getDeliveryTime());
        orderDTO.setAddress(shippingAddressRepository.findById(order.getSid()).orElse(null));

        List<OrderItem> items = orderItemRepository.findByOid(order.getOid());
        List<ShoppingItemDTO> orderItems = new ArrayList<>();
        for (OrderItem item : items){
            orderItems.add(transferDTO(item.getId(), item.getCid(), item.getNumber()));
        }

        orderDTO.setOrderItems(orderItems);

        return orderDTO;
    }

    /**
     * 查看订单详情
     * @param oid
     * @return
     */
    public OrderDTO getOrder(String oid){

        Order order = orderRepository.findById(oid).orElse(null);

        if (order == null){
            throw new IllegalArgumentException("oidb不存在!");
        }

        return transferDTO(order);

    }

    /**
     * 根据状态参数找到订单
     * @param status
     * @return
     */
    public List<OrderDTO> getOrderByStatus(int status){

        if (status < 1 || status > 4){
            throw new IllegalArgumentException("状态参数错误!");
        }

        List<Order> orders = orderRepository.findByStatus(status);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders){
            orderDTOS.add(transferDTO(order));
        }

        return orderDTOS;
    }


}