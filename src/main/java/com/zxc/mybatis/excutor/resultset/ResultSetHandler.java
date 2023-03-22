package com.zxc.mybatis.excutor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ResultSetHandler {

    <E> List<E> handleResultSet(Statement statement) throws SQLException;
}
