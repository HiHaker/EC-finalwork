package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {
    List<Commodity> findByIsDelete(boolean value);
    List<Commodity> findByTypeAndIsDelete(int type, boolean value);
    Commodity findByCidAndIsDelete(String cid, boolean value);
    List<Commodity> findByCnameContains(String keyword);
}
