package com.water.water.dao;

import java.util.List;

public interface td_error_recDao {

    Integer getCountErrorMessage();

    List getSelectMessageByPage(Integer page, Integer size);

    List getAllErrorByTmnLeader(String TmnId);

    List getErrorByTmnId(String TmnId);

}
