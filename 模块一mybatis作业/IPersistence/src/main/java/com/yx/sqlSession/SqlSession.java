package com.yx.sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
    public <E> List<E> selectList(String statementid, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, Exception;

    public <T> T selectOne(String statementid, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException, Exception;

    public <T> T getMapper(Class<?> mapperClass);

    public void addMapper(String statementid, Object... params) throws Exception;

    public void deleteMapper(String statementid, Object... params) throws Exception;

    public void updateMapper(String statementid, Object... params) throws Exception;
}
