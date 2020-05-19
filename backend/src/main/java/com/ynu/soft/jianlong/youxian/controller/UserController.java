package com.ynu.soft.jianlong.youxian.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.soft.jianlong.youxian.entity.User;
import com.ynu.soft.jianlong.youxian.service.AddressService;
import com.ynu.soft.jianlong.youxian.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-19 上午 11:02
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    JSONObject jsonObject;

    /**
     * 用户注册/登录
     * 如果已经注册就登录
     * 如果没注册就注册并登录
     * @param user 用户对象
     * @return 状态信息
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParam(name = "user", value = "用户注册参数", required = true, dataType = "User")
    @PostMapping("/register")
    public JSONObject register(@RequestBody User user){

        jsonObject = new JSONObject();

        try{
            userService.register(user);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 新增收货地址
     * @param uid 用户id
     * @param name 收货人姓名
     * @param telephone 手机号码
     * @param address 地址
     * @return 状态信息
     */
    @ApiOperation(value = "用户新增地址", notes = "用户新增地址")
    @PostMapping("/addAddress")
    public JSONObject addAddress(
            @RequestParam String uid,
            @RequestParam String name,
            @RequestParam String telephone,
            @RequestParam String address){
        jsonObject = new JSONObject();

        try{
            addressService.addAddress(uid, name, telephone, address);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 用户删除地址
     * @param sid 地址id
     * @return 状态信息
     */
    @ApiOperation(value = "用户删除地址", notes = "用户删除地址")
    @DeleteMapping("/deleteAddress")
    public JSONObject deleteAddress(@RequestParam int sid){
        jsonObject = new JSONObject();

        try{
            addressService.deleteAddress(sid);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 用户修改地址
     * @param sid 地址id
     * @param name 收货人姓名
     * @param telephone 电话号码
     * @param address 地址
     * @return 状态信息
     */
    @ApiOperation(value = "用户修改地址", notes = "用户修改地址")
    @PostMapping("/updateAddress")
    public JSONObject updateAddress(
            @RequestParam int sid,
            @RequestParam String name,
            @RequestParam String telephone,
            @RequestParam String address){
        jsonObject = new JSONObject();

        try{
            addressService.updateAddress(sid, name, telephone, address);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 获取用户收货地址
     * @param uid 用户的id
     * @return 状态信息
     */
    @ApiOperation(value = "获取用户收货地址", notes = "获取用户收货地址")
    @GetMapping("/getAddresses")
    public JSONObject getAddresses(@RequestParam String uid){
        jsonObject = new JSONObject();

        try{
            jsonObject.put("content", addressService.getAddresses(uid));
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("content", null);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

    /**
     * 设置为默认地址
     * @param sid 地址id
     * @return 状态信息
     */
    @ApiOperation(value = "设置用户默认收货地址", notes = "设置用户默认收货地址")
    @PostMapping("/setDefault")
    public JSONObject setDefaultAddress(@RequestParam int sid){
        jsonObject = new JSONObject();

        try{
            addressService.setDefaultAddress(sid);
            jsonObject.put("isSuccess", true);
            jsonObject.put("message", null);
        }
        catch (IllegalArgumentException e){
            String message = e.getMessage();
            System.out.println(message);
            e.printStackTrace();
            jsonObject.put("isSuccess", false);
            jsonObject.put("message", message);
        }

        return jsonObject;
    }

}
