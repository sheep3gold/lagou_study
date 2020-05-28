package com.yx.sqlSession;

import com.yx.pojo.Configuration;
import com.yx.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public void addMapper(String statementid, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        simpleExecutor.add(configuration,mappedStatement,params);
//        return null;
    }

    @Override
    public void deleteMapper(String statementid, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        simpleExecutor.delete(configuration,mappedStatement,params);
//        return null;
    }

    @Override
    public void updateMapper(String statementid, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        simpleExecutor.update(configuration,mappedStatement,params);
//        return null;
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                Type genericReturnType = method.getGenericReturnType();

                /*if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }
                return selectOne(statementId, args);*/
                if (methodName.equals("findAll")) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                } else if (methodName.equals("findByCondition")) {
                    return selectOne(statementId, args);
                } else if (methodName.equals("addUser")) {
                    addMapper(statementId,args);
                    return null;
                } else if (methodName.equals("deleteUser")) {
                    deleteMapper(statementId,args);
                    return null;
                } else {
                    updateMapper(statementId,args);
                    return null;
                }
            }
        });
        return (T) proxyInstance;
    }


}
