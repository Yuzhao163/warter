package com.water.water.service;

import com.water.water.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.water.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getByusername(String username){
        return userDao.getByusername(username);
    }

    public User insertToUser(User user){
        userDao.insertToUser(user);
        userDao.insertTo_tdUser(user);
        System.out.println("测试一行");
        System.out.println(user.getPassword());
        return user;
    }
}
