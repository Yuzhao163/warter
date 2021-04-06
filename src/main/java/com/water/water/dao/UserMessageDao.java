package com.water.water.dao;

import com.water.water.pojo.UserManage;
import org.apache.ibatis.annotations.Mapper;
/*author：李小杰
        date:4/5/2021
        function:userMessage*/
@Mapper
public interface UserMessageDao {
    UserManage select_userMessage(String UserID);
    void update_userMessage(UserManage userManage);
    void update_user(UserManage userManage);
}
