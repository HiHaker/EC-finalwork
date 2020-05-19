package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-03 下午 21:40
 */
@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    // 一个用户拥有多个地址
    List<ShippingAddress> findByUidAndIsDelete(String uid, boolean delete);
    // 根据id查找
    ShippingAddress findBySidAndIsDelete(int sid, boolean value);
    // 每一个用户仅仅有一个默认地址
    ShippingAddress findByUidAndIsDefault(String uid, boolean value);
}
