package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ErrordealRecDao {
    //ErrordealRec getAllError();
    List getAllError();

    List getAllErrorById(List AllError);


    Integer updateError(ErrordealRec errordealRec);
}
