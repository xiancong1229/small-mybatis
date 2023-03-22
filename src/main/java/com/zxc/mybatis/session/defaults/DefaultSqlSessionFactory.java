package com.zxc.mybatis.session.defaults;

import com.zxc.mybatis.binding.MapperRegistry;
import com.zxc.mybatis.excutor.Executor;
import com.zxc.mybatis.mapping.Environment;
import com.zxc.mybatis.session.Configuration;
import com.zxc.mybatis.session.SqlSession;
import com.zxc.mybatis.session.SqlSessionFactory;
import com.zxc.mybatis.session.TransactionIsolationLevel;
import com.zxc.mybatis.transaction.Transaction;
import com.zxc.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
