package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ErrordealRecDao {
    //ErrordealRec getAllError();
    List getAllError();

    List getAllErrorById(List AllError);

    Integer updateError(ErrordealRec errordealRec);

    Integer getCountMessage();

    List getSelectErrorMessageByPage(Integer page,Integer size);

    List getErrorByErId(Short ERID);

    Integer InsertToError(ErrordealRec errordealRec);

    Integer appendToError(ErrordealRec errordealRec);

    //5.8
    List getAllErrorByTmnID(String TmnID);
}
