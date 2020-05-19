package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByStatus(int status);
}
