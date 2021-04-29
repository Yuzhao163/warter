package com.water.water.dao;
import com.water.water.pojo.Terminals;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TerminalsDao {
    List getTmnID();

    Terminals getNameByID(String TmnID);

    List getAllTmnList();

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
    Integer updateTmnByid(Integer id, String tmnName, String conPont1, String conPont2,
                      String conPont3, String TmnDesc, String tmnLeadName);

}
