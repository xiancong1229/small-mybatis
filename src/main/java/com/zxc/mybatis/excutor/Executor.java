package com.zxc.mybatis.excutor;

import com.zxc.mybatis.mapping.BoundSql;
import com.zxc.mybatis.mapping.MappedStatement;
import com.zxc.mybatis.session.ResultHandler;
import com.zxc.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
