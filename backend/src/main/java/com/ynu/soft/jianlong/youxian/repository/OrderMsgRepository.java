package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.OrderMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-20 下午 22:26
 */
@Repository
public interface OrderMsgRepository extends JpaRepository<OrderMsg, Integer> {
    List<OrderMsg> findByUidAndIsDelete(String uid, boolean delete);
    List<OrderMsg> findByUidAndIsRead(String uid, boolean read);
}
