package com.water.water.dao;
import com.water.water.pojo.Terminals;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TerminalsDao {
    List getTmnID();
    Terminals getNameByID(Integer TmnID);
}
