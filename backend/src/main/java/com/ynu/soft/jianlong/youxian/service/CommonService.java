package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.CommoImage;
import com.ynu.soft.jianlong.youxian.repository.CommoImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-03 下午 22:30
 */
@Service
public class CommonService {

    @Autowired
    CommoImageRepository commoImageRepository;

    /**
     * 判断给定的日期格式是否正确
     * @param time
     * @return
     */
    public boolean timeFormatIsCorrect(String time){
        // 日期格式为 yyyy-MM-dd HH:mm:ss (小时数：0~23)
        return time.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    /**
     * 判断电话号码格式是否正确
     * @param telephone
     * @return
     */
    public boolean phoneFormatIsCorrect(String telephone){
        return telephone.matches("^1[3|4|5|7|8][0-9]\\d{4,8}$");
    }

    /**
     * 判断给定的时间是不是在当前月内
     * @param date
     * @return
     */
    public boolean isInMonth(String date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH,0);

        String firstDay = format.format(calendar.getTime());
        firstDay = firstDay.split(" ")[0] + " 00:00:00";

        calendar.add(Calendar.MONTH,1);
        String lastDay = format.format(calendar.getTime());
        lastDay = lastDay.split(" ")[0] + " 00:00:00";

        return date.compareTo(firstDay)>=0 && date.compareTo(lastDay)<=0;
    }

    /**
     * 判断给定的时间是不是在当天
     * @param date
     * @return
     */
    public boolean isInDay(String date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start = format.format(new Date());
        start = start.split(" ")[0] + " 00:00:00";
        System.out.println(start);

        String end = format.format(new Date());
        end = end.split(" ")[0] + " 23:59:59";
        System.out.println(end);

        return date.compareTo(start)>=0 && date.compareTo(end)<=0;
    }

    /**
     * 获取商品的图片列表
     * @param cid
     * @return
     */
    public List<String> getImgList(String cid){

        List<String> imgList = new ArrayList<>();
        List<CommoImage> commoImageList = commoImageRepository.findByCid(cid);

        for (CommoImage ci : commoImageList){
            imgList.add(ci.getId());
        }

        return imgList;
    }


}