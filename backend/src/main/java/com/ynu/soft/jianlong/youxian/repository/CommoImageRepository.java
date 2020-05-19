package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.CommoImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
public interface CommoImageRepository extends JpaRepository<CommoImage, String> {
    List<CommoImage> findByCid(String cid);
}
