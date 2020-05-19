package com.ynu.soft.jianlong.youxian.repository;

import com.ynu.soft.jianlong.youxian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020/4/14 0014
 * BY Jianlong
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
