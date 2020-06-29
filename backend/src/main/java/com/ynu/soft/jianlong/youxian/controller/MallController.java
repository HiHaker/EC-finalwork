package com.ynu.soft.jianlong.youxian.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.soft.jianlong.youxian.entity.CommodityDTO;
import com.ynu.soft.jianlong.youxian.entity.CommodityJson;
import com.ynu.soft.jianlong.youxian.service.CommodityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-19 上午 11:02
 */
@CrossOrigin
@RestController
@RequestMapping("/mall")
public class MallController {

    @Autowired
    CommodityService commodityService;

    JSONObject jsonObject;

    /**
     * 首页推荐的商品列表
     * @return 商品列表
     */
    @ApiOperation(value = "首页推荐商品列表", notes = "首页推荐商品列表")
    @GetMapping("/getRecommendCommodities")
    public List<CommodityDTO> getRecommendCommodities(){
        return commodityService.getRecommendCommodities();
    }

    /**
     * 根据类型码获取商品列表
     * @param type 类别码
     * @return 状态信息和商品列表
     */
    @ApiOperation(value = "根据类型码获取商品列表", notes = "根据类型码获取商品列表")
    @GetMapping("/getCommoditiesByType")
    public JSONObject getCommoditiesByType(@RequestParam int type){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", commodityService.getCommoditiesByType(type));
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 获取商品详情
     * @param cid 商品id
     * @return 状态信息和内容
     */
    @ApiOperation(value = "获取商品详情", notes = "获取商品详情")
    @GetMapping("/getCommodityDetail")
    public JSONObject getCommodityDetail(@RequestParam String cid){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", commodityService.getCommodity(cid));
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 上架商品
     * @param commodityJson 商品对象
     * @return 状态信息
     */
    @ApiOperation(value = "上架新商品", notes = "上架新商品")
    @ApiImplicitParam(name = "commodityJson", value = "商品对象", required = true, dataType = "CommodityJson")
    @PostMapping("/addCommodity")
    public JSONObject addCommodity(@RequestBody CommodityJson commodityJson){

        jsonObject = new JSONObject();

        try{
            commodityService.addCommodity(commodityJson);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 更新商品
     * @param commodityJson 商品数据传输对象
     * @return
     */
    @ApiOperation(value = "维护商品", notes = "维护商品")
    @ApiImplicitParam(name = "commodityJson", value = "商品对象", required = true, dataType = "CommodityJson")
    @PostMapping("/updateCommodity")
    public JSONObject updateCommodity(@RequestBody CommodityJson commodityJson){
        jsonObject = new JSONObject();

        try{
            commodityService.updateCommodity(commodityJson);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 下架商品
     * @param cid 商品id
     * @return 状态信息
     */
    @ApiOperation(value = "下架商品", notes = "下架商品")
    @DeleteMapping("/deleteCommodity")
    public JSONObject deleteCommodity(@RequestParam String cid){
        jsonObject = new JSONObject();

        try{
            commodityService.deleteCommodity(cid);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

}
