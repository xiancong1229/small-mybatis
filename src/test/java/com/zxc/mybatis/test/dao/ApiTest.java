package com.zxc.mybatis.test.dao;

import com.alibaba.fastjson.JSON;
import com.zxc.mybatis.binding.MapperProxyFactory;
import com.zxc.mybatis.binding.MapperRegistry;
import com.zxc.mybatis.builder.xml.XMLConfigBuilder;
import com.zxc.mybatis.datasource.pooled.PooledDataSource;
import com.zxc.mybatis.io.Resources;
import com.zxc.mybatis.session.Configuration;
import com.zxc.mybatis.session.SqlSession;
import com.zxc.mybatis.session.SqlSessionFactory;
import com.zxc.mybatis.session.SqlSessionFactoryBuilder;
import com.zxc.mybatis.session.defaults.DefaultSqlSession;
import com.zxc.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.zxc.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.zxc.mybatis.test.po.User");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println(clazz.getMethod("setCreateTime", Date.class));



    }
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

//    @Test
//    public void test_SqlSessionFactory() throws IOException {
//        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        SqlSession sqlSession= sqlSessionFactory.openSession();
//
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        String result = userDao.queryUserInfoById("10001");
//        logger.info("test result: {}", result);
//    }

    @Test
    public void test_SqlSessionFactory() throws IOException {
//        // 1. 从SqlSessionFactory中获取SqlSession
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 2. 获取映射器对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        // 3. 测试验证
//        User user = userDao.queryUserInfoById(1L);
//        logger.info("测试结果：{}", JSON.toJSONString(user));

        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        for (int i = 0; i < 50; i++) {
            User user = userDao.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }
    }

//    @Test
//    public void test_selectOne() throws IOException {
//        // 解析 XML
//        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
//        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
//        Configuration configuration = xmlConfigBuilder.parse();
//
//        // 获取 DefaultSqlSession
//        SqlSession sqlSession = new DefaultSqlSession(configuration);
//
//        // 执行查询：默认是一个集合参数
//        Object[] req = {1L};
//        Object res = sqlSession.selectOne("com.zxc.mybatis.test.dao.IUserDao.queryUserInfoById", req);
//        logger.info("测试结果：{}", JSON.toJSONString(res));
//    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://192.168.50.132:3306/mybatis?useUnicode=true");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("root");
        // 持续获得链接
        while (true){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
        }
    }
}
