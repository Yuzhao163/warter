package com.water.water.dao;


import com.water.water.pojo.Rec_Detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PipeShowDao {
    List getPipe();

    Rec_Detail getRecByTmnID(String TmnId);
}
