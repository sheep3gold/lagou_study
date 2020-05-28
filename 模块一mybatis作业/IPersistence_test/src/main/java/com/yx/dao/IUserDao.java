package com.yx.dao;


import com.yx.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;

    public void addUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);


}
