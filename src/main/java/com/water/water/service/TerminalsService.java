package com.water.water.service;

import com.water.water.dao.TerminalsDao;
import com.water.water.pojo.Rec_Detail;
import com.water.water.pojo.Terminals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.water.water.util.PrintClassName;

@Service
public class TerminalsService {
    @Autowired
    private TerminalsDao terminalsDao;

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
}
