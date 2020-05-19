package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.ShippingAddress;
import com.ynu.soft.jianlong.youxian.repository.ShippingAddressRepository;
import com.ynu.soft.jianlong.youxian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 收获地址管理服务
 * @Author Jianlong
 * @Date 2020-05-12 下午 15:16
 */
@Service
public class AddressService {

    @Autowired
    ShippingAddressRepository shippingAddressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonService commonService;

    /**
     * 检查sid
     * @param sid 地址id
     * @return 地址对象
     */
    private ShippingAddress checkSid(int sid){
        ShippingAddress deleteAddress = shippingAddressRepository.findById(sid).orElse(null);

        // 检查sid的合法性
        if (deleteAddress == null){
            throw new IllegalArgumentException("ERROR:sid不存在!");
        }
        else{
            return deleteAddress;
        }
    }

    /**
     * 检查地址的参数
     * @param name 收获人姓名
     * @param telephone 手机号码
     * @param address 地址
     */
    private void checkArg(String name, String telephone, String address){

        if (name.equals("") || telephone.equals("") || address.equals("")){
            throw new IllegalArgumentException("ERROR:字段为空串!");
        }
        if (!commonService.phoneFormatIsCorrect(telephone)){
            throw new IllegalArgumentException("ERROR:手机号码格式不正确!");
        }
    }

    /**
     * 增加收货地址
     * @param uid 用户id
     * @param name 收获人姓名
     * @param telephone 手机号码
     * @param address 地址
     */
    public void addAddress(String uid, String name, String telephone, String address){

        ShippingAddress newAddress = new ShippingAddress();

        // 检查uid的合法性（外键）
        if (userRepository.findById(uid).orElse(null) == null){
            throw new IllegalArgumentException("ERROR:uid不存在!");
        }
        newAddress.setUid(uid);

        // 检查地址参数
        checkArg(name, telephone, address);

        newAddress.setName(name);
        newAddress.setTelephone(telephone);
        newAddress.setAddress(address);
        newAddress.setDelete(false);

        // 如果是此用户的唯一一个地址，就设置为默认地址
        // 找到未删除的用户的地址
        if (shippingAddressRepository.findByUidAndDelete(uid, false).size() == 0){
            newAddress.setDefault(true);
        }
        else{
            newAddress.setDefault(false);
        }

        shippingAddressRepository.save(newAddress);
    }

    /**
     * 删除收货地址
     * @param sid 地址id
     */
    public void deleteAddress(int sid){

        ShippingAddress deleteAddress = checkSid(sid);
        String uid = deleteAddress.getUid();

        // 并不是真正删除，而是置是否删除字段为true
        deleteAddress.setDelete(true);
        deleteAddress.setDefault(false);
        shippingAddressRepository.save(deleteAddress);

        // 如果删除地址后仅剩下一个地址，就要将其设置为默认地址
        List<ShippingAddress> addresses = shippingAddressRepository.findByUidAndDelete(uid, false);
        if (addresses.size() == 1){
            ShippingAddress address = addresses.get(0);
            address.setDefault(true);
            shippingAddressRepository.save(address);
        }
    }

    /**
     * 修改收货地址
     * @param sid 地址id
     * @param name 收货人姓名
     * @param telephone 电话号码
     * @param address 地址
     */
    public void updateAddress(int sid, String name, String telephone, String address){

        // 检查sid
        ShippingAddress newAddress = checkSid(sid);

        // 检查地址参数
        checkArg(name, telephone, address);

        newAddress.setName(name);
        newAddress.setTelephone(telephone);
        newAddress.setAddress(address);

        shippingAddressRepository.save(newAddress);
    }

    /**
     * 获得用户的地址列表
     * @param uid 用户id
     * @return 用户的地址列表
     */
    public List<ShippingAddress> getAddresses(String uid){
        return shippingAddressRepository.findByUidAndDelete(uid, false);
    }

    /**
     * 设置默认的收货地址
     * @param sid 地址id
     */
    public void setDefaultAddress(int sid){

        // 检查sid
        ShippingAddress newAddress = checkSid(sid);

        String uid = newAddress.getUid();

        // 将原来的默认地址设置为false
        ShippingAddress defaultAddress = shippingAddressRepository.findByUidAndDefault(uid, true);
        defaultAddress.setDefault(false);
        shippingAddressRepository.save(defaultAddress);

        // 设置当前地址为默认
        newAddress.setDefault(true);
        shippingAddressRepository.save(newAddress);
    }
}
