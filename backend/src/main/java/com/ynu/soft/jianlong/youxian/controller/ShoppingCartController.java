package com.ynu.soft.jianlong.youxian.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.soft.jianlong.youxian.service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-01 下午 18:55
 */
@CrossOrigin
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    JSONObject jsonObject;

    /**
     * 获取购物车中商品的数量
     * @param uid 用户id
     * @return 状态信息
     */
    @ApiOperation(value = "获取购物车中商品的数量", notes = "获取购物车中商品的数量")
    @GetMapping("/getItemCount")
    public JSONObject getItemCount(@RequestParam String uid){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", shoppingCartService.getItemNumber(uid));
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
     * 添加商品到购物车
     * @param uid 用户id
     * @param cid 商品id
     * @param number 商品数量
     * @return 状态信息
     */
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
    @PostMapping("/addShoppingItem")
    public JSONObject addShoppingItem(
            @RequestParam String uid,
            @RequestParam String cid,
            @RequestParam int number){

        jsonObject = new JSONObject();

        try{
            shoppingCartService.addItem(uid, cid, number);
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
     * 获取购物项列表
     * @param uid 用户id
     * @return 状态信息
     */
    @ApiOperation(value = "获取购物项列表", notes = "获取购物项列表")
    @GetMapping("/getShoppingItems")
    public JSONObject getShoppingItems(@RequestParam String uid){

        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", shoppingCartService.getShoppingItems(uid));
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
     * 删除购物项
     * @param itemId 购物项id
     * @return 状态信息
     */
    @ApiOperation(value = "删除购物项", notes = "删除购物项")
    @DeleteMapping("/deleteShoppingItem")
    public JSONObject deleteShoppingItem(@RequestParam int itemId){

        jsonObject = new JSONObject();

        try{
            shoppingCartService.deleteItem(itemId);
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
     * 更新购物项的数量
     * @param itemId 购物项id
     * @param number 购物项数量
     * @return 状态信息
     */
    @ApiOperation(value = "更新购物项的数量", notes = "更新购物项的数量")
    @DeleteMapping("/updateShoppingItem")
    public JSONObject updateShoppingItem(@RequestParam int itemId, @RequestParam int number){
        jsonObject = new JSONObject();

        try{
            shoppingCartService.updateItem(itemId, number);
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
