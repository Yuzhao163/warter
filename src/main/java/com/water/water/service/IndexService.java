package com.water.water.service;

import com.water.water.dao.DetailDao;
import com.water.water.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public List getAllMessage(){
        return indexDao.getAllMessage();
    }

    public List getMessageDesc(){
        return indexDao.getMessageDesc();
    }

    public List getSelectMessage(Map params){
        String W_work = "";
        String defult = "";
        String W_Upline = "";
        String W_Downline = "";
        for (Object key : params.keySet()){
            System.out.println("key:=" + key + "  and value:=   " +  params.get(key)+"  \n");
            if (key.equals("W_work")){
                W_work = (params.get("W_work")).toString();
            }
            if (key.equals("defult")){
                defult = (params.get("defult")).toString();
                System.out.println("default:"+defult);
                if(defult.equals("601")){
                    W_Upline = "25";
                    W_Downline = "0";
                }else if (defult.equals("602")){
                    W_Upline = "50";
                    W_Downline = "25";
                }else if (defult.equals("603")){
                    W_Upline = "75";
                    W_Downline = "50";
                }else if(defult.equals("604")){
                    W_Upline = "100";
                    W_Downline = "75";
                }
            }
        }
        return indexDao.getSelectMessage(W_work,W_Upline,W_Downline);
    }

    public Integer getCountMessage(){
        return indexDao.getCountMessage();
    }

    public List getSelectMessageByPage(Integer page,Integer size){
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        return indexDao.getSelectMessageByPage(page,size);
    }
}
