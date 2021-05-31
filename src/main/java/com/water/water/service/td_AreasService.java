package com.water.water.service;

import com.water.water.dao.*;
import com.water.water.pojo.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class td_AreasService {
    @Autowired
    private td_AreasDao td_areasDao;
    @Autowired
    private td_Area_LeaderDao td_area_leaderDao;
    @Autowired
    private UserManageDao userManageDao;
    @Autowired
    private Td_User_RightDao td_user_rightDao;
    @Autowired
    private td_ApDao td_apDao;

    public List getAreas(){
        return td_areasDao.getAreas();
    }

    //5.2 应用到人员管理的getAreas方法
    public List getArea(){
        return td_areasDao.getArea();
    }


    // 获取分区的总个数
    public Integer getAreaSize() {
        return td_areasDao.getAreaSize();
    }

    // 获取分区的全部信息
    public List getAllAreas(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        // 这里创建一个前端需要的jsonObject对象返回
        // 存放分区数据的列表 AreaList
        List<JSONObject> AreaLists = new ArrayList<JSONObject>();
        List<JSONObject> userList = new ArrayList<JSONObject>();
        List AreaList = td_areasDao.getAllAreas(page, size);
        for (int i=0; i<AreaList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            td_Areas Area = (td_Areas)AreaList.get(i);
            String AreaID = Area.getAreaID();
            String AreaName = Area.getAreaName();
            Timestamp CreateTime = Area.getAreaCreateDate();
            jsonObject.put("AreaID",AreaID);
            jsonObject.put("AreaName", AreaName);
            jsonObject.put("CreateTime",CreateTime);
            // 根据分区id获取用户id
            List UIDList = td_area_leaderDao.getUIDByAID(AreaID);
            userList.clear();
            for (int j=0; j<UIDList.size(); j++) {
                // 根据用户id查找用户名称
                td_Area_Leader UID = (td_Area_Leader)UIDList.get(j);
                Integer userID = UID.getLeader();
                UserManage userManage = userManageDao.getUserByID(userID);
                String userName = userManage.getUserName();
                String MoPhone = userManage.getMoPhone();
                System.out.println("===="+userName);
                // 再根据用户id获取用户名称
                JSONObject user = new JSONObject();
                user.put("userID",userID);
                user.put("userName",userName);
                if (MoPhone!=null){
                    user.put("MoPhone",MoPhone);
                }  else {
                    user.put("MoPhone","无");
                }
                userList.add(user);
            }
            jsonObject.put("AreaLeader",userList);
            AreaLists.add(i,jsonObject);
            System.out.println(userList);
        }
        System.out.println(AreaLists);
        return AreaLists;
    }

    // 添加分区名称与管理人员
    public Integer addArea(String areaName, List<Integer> areaLeader){
        // 判断分区名称是否存在
        if(td_areasDao.getAreaNameByName(areaName)!=null) {
            // 分区名称存在 不能添加
            return  201;
        } else {
            // 添加分区id、名称
            Integer ran_num = (int)(Math.random() * 999999999);
            String AreaID = Integer.toString(ran_num);
            while(ifexitAreaID(AreaID)){
                AreaID = createRan();
            }
            td_areasDao.addArea(AreaID, areaName);
            if (areaLeader.get(0)!=-1) {
                // 添加分区管理员信息  得到分区管理员id列表 循环遍历的同时进行分区管理员表的添加
                for (int k = 0; k < areaLeader.size(); k++) {
                    td_area_leaderDao.addAreaLeader(areaLeader.get(k),AreaID);
                }
            }

            return 200;
        }
    }

    public boolean ifexitAreaID(String AreaID){
        if(td_areasDao.getByAreaId(AreaID) == null){
            return false;
        }else{
            return true;
        }
    }

    public String createRan(){
        Integer ran_num = (int)(Math.random() * 999999999);
        String AreaID = Integer.toString(ran_num);
        return AreaID;
    }

    // 获取具有管理分区权限的用户名与id
    public List getAreaLeader() {
        //List UIDlist = td_user_rightDao.getAIDByRight();
        //5.17 替换td_user_right表中Right_pp

        List UIDlist = userManageDao.getAIDByRight();
        List AreaLeaderList = new ArrayList();
        for (int i=0; i<UIDlist.size(); i++) {
            UserManage us = (UserManage)UIDlist.get(i);
            Integer userID = us.getUserID();
            String userName = userManageDao.getUNameByID(userID);
            UserManage userManage = new UserManage();
            userManage.setUserID(userID);
            userManage.setUserName(userName);
            AreaLeaderList.add(i,userManage);
        }
        return AreaLeaderList;
    }

    public Integer modifyArea(String areaID, String areaName, List<Integer> areaLeader) {
        // 修改分区名称 修改分区管理员表的管理员id
        String beareaName = td_areasDao.getNameByID(areaID);
//        System.out.println("areaLeader"+areaLeader);
        if(!areaName.equals(beareaName)) {
            if(td_areasDao.getAreaNameByName(areaName)!=null) {
                // 分区名称存在 不能修改
                return  201;
            } else {
                td_areasDao.modifyAreaNameByID(areaID, areaName);
                td_area_leaderDao.deleteLeader(areaID);
                if (areaLeader.get(0)!=-1) {
                    for (int i = 0; i < areaLeader.size(); i++) {
                        td_area_leaderDao.addAreaLeader(areaLeader.get(i),areaID);
                    }
                }
                return 200;
            }
        } else {
            td_area_leaderDao.deleteLeader(areaID);
            if (areaLeader.get(0)!=-1) {
                for (int i = 0; i < areaLeader.size(); i++) {
                    td_area_leaderDao.addAreaLeader(areaLeader.get(i),areaID);
                }
            }
            return 200;
        }
    }

    public Integer deleteArea(String areaID) {
        td_areasDao.deleteArea(areaID);
        if (td_apDao.getPipIDByAreaID(areaID).size()!=0) {
            td_apDao.clearAreaID(areaID);
        }

        if (td_area_leaderDao.getUIDByAID(areaID).size()!=0) {
            td_area_leaderDao.deleteLeader(areaID);
        }
        return 200;
    }

}
