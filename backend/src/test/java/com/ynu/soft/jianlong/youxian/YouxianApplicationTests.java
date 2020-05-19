package com.ynu.soft.jianlong.youxian;

import com.ynu.soft.jianlong.youxian.entity.Commodity;
import com.ynu.soft.jianlong.youxian.repository.CommodityRepository;
import com.ynu.soft.jianlong.youxian.service.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YouxianApplicationTests {

    @Autowired
    CommonService commonService;

    @Test
    void testTimeFormatCheck(){
        String testTime = "2017-12-04 14:15:16";
        System.out.println(commonService.timeFormatIsCorrect(testTime));
    }

    @Test
    void testPhoneFormatCheck(){
        String test = "18387161051";
        System.out.println(commonService.phoneFormatIsCorrect(test));
    }

    @Test
    void contextLoads() {
    }

}
