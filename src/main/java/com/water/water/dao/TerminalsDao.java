package com.water.water.dao;
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

    List getTNameByUserName(String UserName);

//    没用了
//    //更新新增用户分配所属控制柜
//    void updateTmnLeader(@Param("TmnLeader") String TmnLeader,@Param("TmnId") String TmnId);
    //根据控制柜名称查找控制柜ID
//    Terminals getTmnIDByTmnName(@Param("TmnName") String TmnName);
//    Terminals getNameByID2(String TmnID);
    //获取所有控制柜领导名称
//    List getTerminalLeader();
    //通过TmnLeder获取对应TmnID
//    List getTmnIDs(@Param("TmnLeader") String TmnLeader);
    //获取控制柜--4.27/16.01--------------5.2解决和管线分布的冲突----------------------------------------
    List getTerminal();
//    void updateLeaderByTmnID(String id);
//    5.2获取TmnLeader为空的所有TmnID
//    List getLeaderIsNull();
    //5.2带有追加功能的update，用于添加多个leader
//    void appendTmnLeader(@Param("TmnLeader") String TmnLeader, @Param("TmnID") String TmnID);


    List getTINByTID(String tmnID);

    List getTmnByID(String tmnID);

    Integer getTmnSize();

    // 分页获取控制柜信息
    List getAllTmnListByPage(Integer page, Integer size);

    //  更新控制柜信息
    Integer updateTmnByid(String TmnID, String TmnName, String ConPont1, String ConPont2,
                          String ConPont3, String TmnDesc);

    //  获取id最大值
    Integer getMaxID();

    //  修改控制柜的下一控制柜id
    Integer setD1TmnID(String TmnID,String D1TmnID);
    Integer setD1TmnIDNull(String TmnID);

    //  修改控制柜的上一控制柜id
    Integer setU1TmnID(String TmnID,String U1TmnID);
    Integer setU1TmnIDNull(String TmnID);

    //  判断控制柜名称是否存在
    String existname(String TmnName);


    //5.17修改，通过控制柜名称搜索控制柜
    Terminals getTmnByTmnName(String TmnName);
}
