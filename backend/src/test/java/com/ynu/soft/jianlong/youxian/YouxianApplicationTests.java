package com.ynu.soft.jianlong.youxian;

import com.ynu.soft.jianlong.youxian.repository.CommodityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YouxianApplicationTests {

    @Autowired
    CommodityRepository commodityRepository;

    @Test
    void testFindByKeyword(){
        System.out.println(commodityRepository.findByCnameContains("豆腐"));
    }

    @Test
    void contextLoads() {
    }

}
