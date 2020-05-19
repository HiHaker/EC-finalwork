package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Integer> {
    List<ShoppingItem> findByUid(String uid);
    ShoppingItem findByCidAndUid(String cid, String uid);
}