package com.zxc.mybatis.session.defaults;

import com.zxc.mybatis.binding.MapperRegistry;
import com.zxc.mybatis.session.Configuration;
import com.zxc.mybatis.session.SqlSession;
import com.zxc.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
