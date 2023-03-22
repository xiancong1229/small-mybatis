package com.zxc.mybatis.session;

import com.zxc.mybatis.binding.MapperRegistry;
import com.zxc.mybatis.datasource.druid.DruidDataSourceFactory;
import com.zxc.mybatis.excutor.Executor;
import com.zxc.mybatis.excutor.SimpleExecutor;
import com.zxc.mybatis.excutor.resultset.DefaultResultSetHandler;
import com.zxc.mybatis.excutor.resultset.ResultSetHandler;
import com.zxc.mybatis.excutor.statement.PreparedStatementHandler;
import com.zxc.mybatis.excutor.statement.StatementHandler;
import com.zxc.mybatis.mapping.BoundSql;
import com.zxc.mybatis.mapping.Environment;
import com.zxc.mybatis.mapping.MappedStatement;
import com.zxc.mybatis.transaction.Transaction;
import com.zxc.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.zxc.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    protected Environment environment;
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }


}
