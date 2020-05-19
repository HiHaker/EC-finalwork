package com.ynu.soft.jianlong.youxian.service;

import com.ynu.soft.jianlong.youxian.entity.User;
import com.ynu.soft.jianlong.youxian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-05-03 下午 21:48
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonService commonService;

    private void addUser(User user){

    }

//    /**
//     * 用户是否注册过
//     * @param userId
//     * @return
//     */
//    public boolean isRegistered(String userId){
//
//        User user = userRepository.findById(userId).orElse(null);
//        if (user == null){
//            return false;
//        } else{
//            return true;
//        }
//    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public void register(User user){
        // 判断参数的合法性
        if (user == null){
            throw new IllegalArgumentException("ERROR:用户对象为空!");
        } else if (user.getUid().equals("")){
            throw new IllegalArgumentException("ERROR:用户id格式不正确!");
        } else if (! commonService.timeFormatIsCorrect(user.getRegistrationDate())){
            throw new IllegalArgumentException("ERROR:日期时间格式不正确!");
        }

        userRepository.save(user);
    }
}