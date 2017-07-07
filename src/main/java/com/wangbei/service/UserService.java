package com.wangbei.service;

import com.wangbei.dao.UserDao;
import com.wangbei.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author yuyidi 2017-07-06 17:36:53
* @class com.wangbei.service.UserService
* @description 用户业务服务
*/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User addUser(User user){
        return userDao.createUser(user);
    }

    public User getUserByPhoneAndPassword(String phone, String password) {
        return userDao.fetchUserByPhoneAndPassword(phone, password);
    }
}
