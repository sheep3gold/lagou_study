package com.yx.test;

import com.yx.dao.IUserDao;
import com.yx.io.Resources;
import com.yx.pojo.User;
import com.yx.sqlSession.SqlSession;
import com.yx.sqlSession.SqlSessionFactory;
import com.yx.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {
    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test() throws Exception {

        User user = new User();
        user.setId(1);
        user.setUsername("张三");

        /*User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);

        List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

    }

    @Test
    public void test1() throws Exception {

        User user = new User();
        user.setId(10);
        user.setUsername("test1");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.addUser(user);
    }

    @Test
    public void test2() throws Exception {

        User user = new User();
        user.setId(10);

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.deleteUser(user);
    }

    @Test
    public void test3() throws Exception {

        User user = new User();
        user.setId(10);
        user.setUsername("update");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        userDao.updateUser(user);
    }
}
