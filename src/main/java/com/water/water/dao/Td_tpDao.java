package com.water.water.dao;

import com.water.water.pojo.td_Tp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Td_tpDao {
    td_Tp getAlltdById(String TmnId);
    List getAlltdByPipID(String PipID);

    //  根据管线id查询控制柜id并且要按照顺序排列
    List getAlltdByTmnID(String PipID);
    //  根据管线id控制柜id找线内编号
    Integer getPtid(String TmnID, String PipID);
    //  设置某个控制柜在第几个位置后
    Integer insertPtid(String TmnID, String PipID, Integer PTid);
    //  将线内编号后的+1
    Integer ptidAdd(String PipID, Integer PTid);
    //  根据tmnid找pipid
    String getPIDByTID(String TmnID);
    //  根据tmnid删除一条数据
    Integer deleteTP(String TmnID);
    //  修改线内id
    Integer ptidMinus(String PipID,Integer PTid);
    //  根据控制柜id找到所有数据
    List getTpByTID(String TmnID);
    //  根据管线查询是否存在控制柜
    List getTmnByPID(String PipID);
    //  更新控制柜线内位置
    Integer updatePTid(String PipID,Integer PTid);
    // 得到管线内的第一个控制柜id
    String getFirstTmn(String PipID);
    // 得到管线内控制柜个数
    Integer getPipSize(String PipID);
}
