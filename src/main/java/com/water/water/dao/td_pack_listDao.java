package com.water.water.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/10 20:53
 */
@Mapper
public interface td_pack_listDao {
    String GetIp(String TmnID);
}
