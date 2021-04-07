package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ErrordealRecDao {
    //ErrordealRec getAllError();
    List getAllError();

    List getAllErrorById(List AllError);

//    void show_detail_deal(String TmnId);
//
//    void update_detail_deal(String TmnId);

//    List getError();
//    List getErrorById(List Error);
    void updateError(ErrordealRec errordealRec);
}
