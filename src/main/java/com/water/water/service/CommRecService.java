package com.water.water.service;

import com.water.water.Result.Result;
import com.water.water.dao.CommRecDao;
import com.water.water.dao.td_pack_listDao;
import com.water.water.pojo.CommRec;
import com.water.water.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.water.dao.UserDao;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommRecService {

    @Autowired
    private CommRecDao commRecDao;

    @Autowired
    private td_pack_listDao td_pack_listDao;


    public CommRec inserttotd_comm(CommRec commRec){
        commRecDao.inserttotd_comm(commRec);
        return commRec;
    }
    //packageid生成，当前时间生成。
    //TmnID前端传回
    public Result SendOrder(CommRec commRec){
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        Long PackageID = Long.parseLong(createdate);
        commRec.setPackageID(PackageID);
        commRec.setCmdStatus(1);
        try {
            commRecDao.SendOrder(commRec);
            return new Result(200);
        }catch (Exception e){
            return new Result(400);
        }
    }

    public String GetIp(CommRec commRec){
        String TmnID = commRec.getTmnID();
        return td_pack_listDao.GetIp(TmnID);
    }
}