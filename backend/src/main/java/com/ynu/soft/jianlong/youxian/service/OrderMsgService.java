package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.Order;
import com.ynu.soft.jianlong.youxian.entity.OrderMsg;
import com.ynu.soft.jianlong.youxian.repository.OrderMsgRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderRepository;
import com.ynu.soft.jianlong.youxian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-20 下午 22:34
 */
@Service
public class OrderMsgService {

    @Autowired
    OrderMsgRepository orderMsgRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 检查oid参数
     * @param oid 订单id
     */
    private void checkArgOid(String oid){
        Order order = orderRepository.findById(oid).orElse(null);
        if (order == null){
            throw new IllegalArgumentException("ERROR:参数非法!oid不存在!");
        }
    }

    /**
     * 检查uid
     * @param uid 用户id
     */
    private void checkArgUid(String uid){
        if (userRepository.findById(uid).orElse(null) == null){
            throw new IllegalArgumentException("ERROR:参数非法!uid不存在!");
        }
    }

    public void addOrderMsg(OrderMsg orderMsg){

        if (orderMsg == null){
            throw new IllegalArgumentException("ERROR:参数非法!数据为空!");
        }

        checkArgOid(orderMsg.getOid());
        checkArgUid(orderMsg.getUid());

        orderMsgRepository.save(orderMsg);
    }

    public List<OrderMsg> getNotDeleteOrderMsg(String uid){
        return orderMsgRepository.findByUidAndIsDelete(uid, false);
    }

    public List<OrderMsg> getNotReadOrderMsg(String uid){
        return orderMsgRepository.findByUidAndIsRead(uid, false);
    }

    public void updateOrderMsg(OrderMsg orderMsg){

        if (orderMsg == null){
            throw new IllegalArgumentException("ERROR:参数非法!数据为空!");
        }

        if (orderMsgRepository.findById(orderMsg.getId()).orElse(null) == null){
            throw new IllegalArgumentException("ERROR:参数非法!消息id不存在!");
        }

        checkArgUid(orderMsg.getUid());

        orderMsgRepository.save(orderMsg);
    }
}
