package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-03 下午 21:40
 */
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    // 一个用户拥有多个地址
    List<ShippingAddress> findByUidAndDelete(String uid, boolean delete);
    // 每一个用户仅仅有一个默认地址
    ShippingAddress findByUidAndDefault(String uid, boolean value);
}
