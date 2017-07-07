package com.wangbei.dao;

import com.wangbei.entity.User;

/**
* @author yuyidi 2017-07-06 17:33:23
* @class com.wangbei.dao.UserDao
* @description 用户数据访问接口
*/
public interface UserDao {
    public User createUser(User user);

    User fetchUserByPhoneAndPassword(String phone, String password);
}
