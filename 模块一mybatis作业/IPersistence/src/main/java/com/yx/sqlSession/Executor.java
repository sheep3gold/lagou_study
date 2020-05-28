package com.yx.sqlSession;

import com.yx.pojo.Configuration;
import com.yx.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws Exception;

    public void add(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    public void delete(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception;

    public void update(Configuration configuration,MappedStatement mappedStatement, Object... params) throws Exception;
}
