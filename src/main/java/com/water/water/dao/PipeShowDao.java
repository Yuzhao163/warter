package com.water.water.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PipeShowDao {
    List getPipe();
}
