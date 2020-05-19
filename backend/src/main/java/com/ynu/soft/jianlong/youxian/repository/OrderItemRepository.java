package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOid(String oid);
}
