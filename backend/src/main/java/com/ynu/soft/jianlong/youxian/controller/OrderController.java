package com.ynu.soft.jianlong.youxian.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.soft.jianlong.youxian.entity.Order;
import com.ynu.soft.jianlong.youxian.entity.OrderJson1;
import com.ynu.soft.jianlong.youxian.entity.OrderJson2;
import com.ynu.soft.jianlong.youxian.entity.StatusJson;
import com.ynu.soft.jianlong.youxian.service.ShoppingCartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-01 下午 19:40
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ShoppingCartService shoppingCartService;

    JSONObject jsonObject;

    /**
     * 在点击下单时候使用，生成订单（不存入数据库）
     * @param json 参数对象
     * @return
     */
    @ApiOperation(value = "在点击下单时候使用，生成订单（不存入数据库）", notes = "在点击下单时候使用，生成订单（不存入数据库）")
    @ApiImplicitParam(name = "orderJson1", value = "参数对象", required = true, dataType = "OrderJson1")
    @GetMapping("/getOrderBody")
    public JSONObject getOrderBody(@RequestParam OrderJson1 json){

        jsonObject = new JSONObject();

        try{
            String uid = json.getUid();
            List<Integer> itemList = json.getItemList();
            jsonObject.put("content", shoppingCartService.getOrder(uid, itemList));
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
     * 在点击确认订单时候使用，生成订单（存入数据库）
     * @param json 参数对象
     * @return 状态信息
     */
    @ApiOperation(value = "在点击确认订单时候使用，生成订单（存入数据库）", notes = "在点击确认订单时候使用，生成订单（存入数据库）")
    @ApiImplicitParam(name = "orderJson2", value = "参数对象", required = true, dataType = "OrderJson2")
    @PostMapping("/generateOrder")
    public JSONObject generateOrder(@RequestParam OrderJson2 json){

        jsonObject = new JSONObject();

        try{
            Order order = json.getOrder();
            List<Integer> itemList = json.getItemList();
            shoppingCartService.generateOrder(order, itemList);
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
     * 根据状态获取订单列表
     * @param uid 用户id
     * @param status 状态参数
     * @return 状态信息
     */
    @ApiOperation(value = "根据状态获取订单列表", notes = "根据状态获取订单列表")
    @GetMapping("/getOrderByStatus")
    public JSONObject getOrderByStatus(@RequestParam String uid, @RequestParam int status){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", shoppingCartService.getOrderByUidAndStatus(uid, status));
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
     * 获取订单详情
     * @param oid 订单id
     * @return 状态信息
     */
    @ApiOperation(value = "获取订单详情", notes = "获取订单详情")
    @GetMapping("/getOrder")
    public JSONObject getOrder(@RequestParam String oid){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", shoppingCartService.getOrder(oid));
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
     * 获取全部类别的订单数量
     * @param uid 用户id
     * @return 状态信息
     */
    @ApiOperation(value = "获取订单详情", notes = "获取订单详情")
    @GetMapping("/getOrder")
    public JSONObject getAllTypeNumberOfOrder(@RequestParam String uid){

        jsonObject = new JSONObject();

        try{
            StatusJson json = new StatusJson();
            json.setWaitingPay(shoppingCartService.getOrderByUidAndStatus(uid, 1).size());
            json.setWaitingDelivery(shoppingCartService.getOrderByUidAndStatus(uid, 2).size());
            json.setWaitingReceipt(shoppingCartService.getOrderByUidAndStatus(uid, 3).size());
            json.setFinished(shoppingCartService.getOrderByUidAndStatus(uid, 4).size());
            jsonObject.put("content", json);
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
