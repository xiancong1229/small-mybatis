package com.zxc.mybatis.binding;

import com.zxc.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

//    public T newInstance(Map<String, String> sqlSession) {
//        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
//        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
//    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
