package com.water.water.dao;

import com.water.water.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getByusername(String username);
    int insertToUser(User user);
}
