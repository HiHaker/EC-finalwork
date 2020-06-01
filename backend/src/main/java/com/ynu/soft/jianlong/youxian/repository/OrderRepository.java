package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findBySidAndStatus(int sid, int status);
}
