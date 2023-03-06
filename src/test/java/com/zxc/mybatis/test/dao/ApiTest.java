package com.zxc.mybatis.test.dao;

import com.zxc.mybatis.binding.MapperProxyFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();

        sqlSession.put("com.zxc.mybatis.test.dao.IUserDao.queryUserName", "模拟执行mapper.xml中SQL语句的操作：查询用户姓名");
        sqlSession.put("com.zxc.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行mapper.xml中SQL语句的操作：查询用户年龄");

        IUserDao userDao = factory.newInstance(sqlSession);
        String result = userDao.queryUserName("1");
        System.out.println("测试结果：" + result);
        //logger.info("测试结果：{}", result);
    }
}
