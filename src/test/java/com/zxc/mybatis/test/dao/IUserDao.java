package com.zxc.mybatis.test.dao;

import com.zxc.mybatis.test.po.User;

public interface IUserDao {
    String queryUserName(String userId);
    Integer queryUserAge(String userId);

    User queryUserInfoById(Long userId);
}
