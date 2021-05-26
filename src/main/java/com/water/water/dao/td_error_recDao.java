package com.water.water.dao;

import com.water.water.pojo.ErrordealRec;
import com.water.water.pojo.td_error_rec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface td_error_recDao {

    Integer getCountErrorMessage();

    List getSelectMessageByPage(Integer page, Integer size);

    List getAllErrorByTmnLeader(String TmnId);

    //5.19
    //List getErrorByTmnId(String TmnId);
//    List getErrorByTmnId(@Param("TmnId") String TmnId, @Param("page") Integer page, @Param("size") Integer size);
    List getErrorByTmnId(@Param("list") List TmnId, @Param("page") Integer page
            , @Param("size") Integer size);

    //5.19
    //td_error_rec getIfByTmnId(String TmnId);
    //5.19修改
    //5.19getIfByTmnId需要修改为getIfByErid，因为每一条异常都需要处理记录，而控制柜可能有很多异常部位
    td_error_rec getIfByErid(Short Erid);


    Integer updateByTmnId(String TmnId);

    Integer insertToNewError(ErrordealRec errordealRec);

    //5.19统计该员工名下控制柜异常条数
    Integer getCountNum(@Param("TmnID") String tmnID);

    //5.19更新对应异常的处理状态
    Integer updateByErid(@Param("ERId") Short erId);
}
