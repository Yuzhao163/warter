package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ErrordealRecDao {
    //ErrordealRec getAllError();
    List getAllError();

    List getAllErrorById(List AllError);

    Integer updateError(ErrordealRec errordealRec);

    //  更新记录异常用户名称
    Integer updateErrorDealUser(String userName, String beforeName);

    Integer getCountMessage();

    List getSelectErrorMessageByPage(Integer page,Integer size);
}
