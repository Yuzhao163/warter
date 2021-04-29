package com.water.water.dao;
import com.water.water.Result.Result;
import com.water.water.pojo.Terminals;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TerminalsDao {
    List getTmnID();

    Terminals getNameByID(String TmnID);

    List getAllTmnList();

    Integer addTmn(Terminals requestTmn);

    Integer deleteTmnByID(String tmnID);

    Integer modifyTmn(Terminals tmn);

    String getTmnNameByTmnID(String tmnID);

    List getTerminalsByUserName(String UserName);


    //更新新增用户分配所属控制柜
    void updateTmnLeader(@Param("TmnLeader") String TmnLeader,@Param("TmnId") String TmnId);

    //根据控制柜名称查找控制柜ID
    Terminals getTmnIDByTmnName(@Param("TmnName") String TmnName);

    Terminals getNameByID2(String TmnID);

    //获取所有控制柜领导名称
    List getTerminalLeader();

    //通过TmnLeder获取对应TmnID
    List getTmnIDs(@Param("TmnLeader") String TmnLeader);

    //获取控制柜--4.27/16.01------------------------------------------------------
    List getTerminals();

    void updateLeaderByTmnID(String id);
}
