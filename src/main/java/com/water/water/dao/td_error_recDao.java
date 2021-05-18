package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.td_error_rec;

import java.util.List;

public interface td_error_recDao {

    Integer getCountErrorMessage();

    List getSelectMessageByPage(Integer page, Integer size);

    List getAllErrorByTmnLeader(String TmnId);

    List getErrorByTmnId(String TmnId);

    td_error_rec getIfByTmnId(String TmnId);

    Integer updateByTmnId(String TmnId);

    Integer insertToNewError(ErrordealRec errordealRec);
}
