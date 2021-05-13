package com.water.water.dao;
import com.water.water.pojo.Terminals;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TerminalsDao {
    List getTmnID();

    Terminals getNameByID(String TmnID);

    List getAllTmnList();

    Integer getTmnSize();

    // 分页获取控制柜信息
    List getAllTmnListByPage(Integer page, Integer size);

    Integer addTmn(Terminals requestTmn);

    Integer deleteTmnByID(String tmnID);

    Integer modifyTmn(Terminals tmn);

    String getTmnNameByTmnID(String tmnID);

    List getTerminalsByUserName(String UserName);

    List getTNameByUserName(String UserName);

//  更改控制柜管理员名称
    Integer updateTmnLeader(String tmnLeader, String beforeName);

    List getTINByTID(String tmnID);

    List getTmnByID(String tmnID);

//  解决为解决的bug写的
    Terminals getTmnByid(Integer id);

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

}
