package com.water.water.dao;
import com.water.water.pojo.Terminals;
import org.apache.ibatis.annotations.Mapper;

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
}
