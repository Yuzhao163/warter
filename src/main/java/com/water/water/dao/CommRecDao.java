package com.water.water.dao;

import com.water.water.pojo.CommRec;
import com.water.water.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommRecDao {
    int inserttotd_comm(CommRec commRec);

    void SendOrder(CommRec commRec);

    //String GetIp(String TmnID);
}
