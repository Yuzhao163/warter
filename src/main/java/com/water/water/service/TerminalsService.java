package com.water.water.service;

import com.water.water.Result.Result;
import com.water.water.dao.TerminalsDao;
import com.water.water.dao.td_PipesDao;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.td_PIPs;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.water.water.util.PrintClassName;

@Service
public class TerminalsService {
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private td_PipesDao td_pipesDao;


    public List getTmnID(){
        return terminalsDao.getTmnID();
    }

    public List getNameByID(List selectmessage){
        System.out.println(selectmessage);
        PrintClassName printClassName = new PrintClassName();
        for (int i = 0;i<selectmessage.size();i++){
            Rec_Detail message = (Rec_Detail)selectmessage.get(i);
            String TmnID = message.getTmnID();
            Terminals terminals = terminalsDao.getNameByID(TmnID);
            String TmnName = terminals.getTmnName();
            message.setTmnID(TmnName);
        }
        System.out.println(selectmessage);
        return selectmessage;
    }

//  获取全部控制柜
    public List getAllTmnList() {
        return terminalsDao.getAllTmnList();

    }

//  增加一个控制柜
    public Integer addTmn(Terminals requestTmn) {
        return terminalsDao.addTmn(requestTmn);

    }

//  根据控制柜id删除一个控制柜
    public Integer deleteTmnByID(Terminals tmn) {
        String tmnID = tmn.getTmnId();
        return terminalsDao.deleteTmnByID(tmnID);
    }

//  编辑控制柜
    public Integer modifyTmn(Terminals tmn) {
        return terminalsDao.modifyTmn(tmn);
    }

//  根据id查询 管线名称和控制柜名称 将合并信息一起返回给前端
    public JSONArray getTmnList() {
        List tmnList = terminalsDao.getAllTmnList();
        JSONArray jsonArray = JSONArray.fromObject(tmnList);
        for (int i=0; i<jsonArray.size(); i++) {
            // 循环获得每一个控制柜
            Terminals tmn = (Terminals) tmnList.get(i);
            // 获得每一个控制柜的管线id
            String PipID = tmn.getPipID();
            // 根据管线id查询管线
            String PipName = td_pipesDao.getPipsByPipID(PipID);
            // 查询控制柜相关信息
            String U1TmnID = tmn.getU1TmnID();
            String U2TmnID = tmn.getU2TmnID();
            String D1TmnID = tmn.getD1TmnID();
            String D2TmnID = tmn.getD2TmnID();
            String U1TmnName = terminalsDao.getTmnNameByTmnID(U1TmnID);
            String U2TmnName = terminalsDao.getTmnNameByTmnID(U2TmnID);
            String D1TmnName = terminalsDao.getTmnNameByTmnID(D1TmnID);
            String D2TmnName = terminalsDao.getTmnNameByTmnID(D2TmnID);
            JSONObject item = jsonArray.getJSONObject(i);
            item.put("pipName",PipName);
            item.put("u1TmnName",U1TmnName);
            item.put("u2TmnName",U2TmnName);
            item.put("d1TmnName",D1TmnName);
            item.put("d2TmnName",D2TmnName);
        }
        return jsonArray;
    }

}
