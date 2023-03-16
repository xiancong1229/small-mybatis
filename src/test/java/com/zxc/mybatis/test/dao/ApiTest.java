package com.zxc.mybatis.test.dao;

import com.zxc.mybatis.binding.MapperProxyFactory;
import com.zxc.mybatis.binding.MapperRegistry;
import com.zxc.mybatis.io.Resources;
import com.zxc.mybatis.session.SqlSession;
import com.zxc.mybatis.session.SqlSessionFactory;
import com.zxc.mybatis.session.SqlSessionFactoryBuilder;
import com.zxc.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    @Test
    public void test_MapperProxyFactory() {
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//        Map<String, String> sqlSession = new HashMap<>();
//
//        sqlSession.put("com.zxc.mybatis.test.dao.IUserDao.queryUserName", "模拟执行mapper.xml中SQL语句的操作：查询用户姓名");
//        sqlSession.put("com.zxc.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行mapper.xml中SQL语句的操作：查询用户年龄");
//
//        IUserDao userDao = factory.newInstance(sqlSession);
//        String result = userDao.queryUserName("1");
//        System.out.println("测试结果：" + result);
        //logger.info("测试结果：{}", result);

//        //1. 注册Mapper
//        MapperRegistry registry = new MapperRegistry();
//        registry.addMappers("com.zxc.mybatis.test.dao");
//
//        //2. 从SqlSession工厂中获取Session
//        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        //3. 获取映射器对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        //4. 测试验证
//        String result = userDao.queryUserName("10001");
//        logger.info("测试结果：{}", result);
    }

    @Test
    public void test_SqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession= sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        String result = userDao.queryUserInfoById("10001");
        logger.info("test result: {}", result);
    }
}
