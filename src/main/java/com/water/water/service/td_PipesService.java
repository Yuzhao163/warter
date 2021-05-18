package com.water.water.service;

import com.water.water.dao.*;
import com.water.water.pojo.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class td_PipesService {
    @Autowired
    private td_PipesDao td_pipesDao;
    @Autowired
    private td_ApDao td_apDao;
    @Autowired
    private td_AreasDao td_areasDao;
    @Autowired
    private td_Pip_LeaderDao td_pip_leaderDao;
    @Autowired
    private UserManageDao userManageDao;
    @Autowired
    private Td_User_RightDao td_user_rightDao;

    public List getPips() {
        return td_pipesDao.getPips();
    }

//    td_PIPs getAreasIdByPips(String PipsID) {
//        return td_pipesDao.getAreasIdByPips(PipsID);
//    }

    public Integer getPipSize() {return td_pipesDao.getPipSize();}

    public List getAllPips(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }

        List<JSONObject> PipLists = new ArrayList<JSONObject>();
        List<JSONObject> userList = new ArrayList<JSONObject>();
        List PipList = td_pipesDao.getAllPips(page, size);
        for (int i = 0; i < PipList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            td_PIPs Pip = (td_PIPs)PipList.get(i);
            String PipID = Pip.getPipID();
            jsonObject.put("PipID",Pip.getPipID());
            jsonObject.put("PipName",Pip.getPipName());
            jsonObject.put("PipDesc",Pip.getPipDesc());
            jsonObject.put("PipCreateTime",Pip.getPipCreateDate());
            // 根据管线id获取分区id
            List AIDList = td_apDao.getAIDByPID(PipID);
            String AreaID = null;
            if (AIDList.size() != 0) {
                td_Ap ap = (td_Ap)AIDList.get(0);
                AreaID = ap.getAreaID();
            }
            if(AreaID != null) {
                jsonObject.put("AreaID",AreaID);
                td_Areas area = td_areasDao.getAreaNameByAreaID(AreaID);
                if (area.getAreaName() != null) {
                    jsonObject.put("AreaName",area.getAreaName());
                }
            }
            // 根据管线id获得用户id
            List UIDList = td_pip_leaderDao.getUIDByPID(PipID);
            userList.clear();
            for (int j = 0; j < UIDList.size(); j++) {
                // 根据用户id找用户名称与电话号码
                td_Pip_Leader UID = (td_Pip_Leader)UIDList.get(j);
                Integer userID = UID.getLeader();
                UserManage userManage = userManageDao.getUserByID(userID);
                String userName = userManage.getUserName();
                String MoPhone = userManage.getMoPhone();
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
            jsonObject.put("PipLeader",userList);
            PipLists.add(i,jsonObject);
        }
        return PipLists;
    }

    public List getPipLeader() {

        List UIDlist = td_user_rightDao.getPIDByRight();
        List PipLeaderList = new ArrayList();
        for (int i=0; i<UIDlist.size(); i++) {
            td_User_Right user_right = (td_User_Right)UIDlist.get(i);
            Integer userID = user_right.getUserID();
            String userName = userManageDao.getUserNameByID(userID);
            UserManage userManage = new UserManage();
            userManage.setUserID(userID);
            userManage.setUserName(userName);
            PipLeaderList.add(i,userManage);
        }
        return PipLeaderList;
    }


    //5.9生成随机数
    public String createRan(){
        Integer ran_num = (int)(Math.random() * 999999999);
        String PipID = Integer.toString(ran_num);
        return PipID;
    }

    //5.9判断是否该管线ID是否已经存在
    public boolean ifexitPipID(String PipID){
        if(td_pipesDao.getByPipId(PipID) == null){
            return false;
        }else{
            return true;
        }
    }

    //5.9增加管线
    public Integer addPips(String PipName,String AreaID,List<Integer> PipLeader) {
        //插入td_Pips表
        //获取管线随机ID
        Integer ran_num = (int)(Math.random() * 999999999);
        String PipID = Integer.toString(ran_num);
        System.out.println(PipID);
        while(ifexitPipID(PipID)){
            PipID = createRan();
        }

        if(td_pipesDao.ifSameNamePip(PipName) != null){
            return 201;//名称重复，不能添加
        }else{

            td_pipesDao.addPips(PipID,PipName);
        }

        if (AreaID!=null) {
            //插入td_Ap表
            td_apDao.addID(AreaID,PipID);
        }


        if (PipLeader.get(0)!=-1) {
            //插入td_pip_leader表
            for (int k = 0; k < PipLeader.size(); k++) {
                td_pip_leaderDao.addPipLeader(PipLeader.get(k),PipID);
            }
        }
        return 200;
    }

    //5.9删除管线
    public Integer deletePip(String pipID) {

        //删除td_Pips
        td_pipesDao.deletePip(pipID);
        //删除td_Ap
        td_apDao.deletePip(pipID);
        //删除td_pip_leader
        td_pip_leaderDao.deletePip(pipID);

        return 200;
    }

    //5.9更新管线
    public Integer updatePip(String pipID, String pipName, List<Integer> pipLeader, String areaID) {
        // 修改管线名称 修改分区管线表 修改管线管理员表的管理员id
        String name = td_pipesDao.getPipNameByPipID(pipID);


        // 修改
        // 是否修改管线名称
        // 修改管线名称
            // 判断管线名称是否存在
            // 存在 返回201
            // 不存在 修改名称
        if(!pipName.equals(name)) {
            if (td_pipesDao.getPipNameByName(pipName) != null) {
                // 分区名称存在 不能修改
                return 201;
            } else {
                td_pipesDao.updatePips(pipID, pipName);
            }
        }

        System.out.println("分区名称"+areaID);

        // 修改后的分区为空 把先前的ap表中有这个分区的数据删除
        if (areaID.equals("")||areaID==null) {
            td_apDao.deletePip(pipID);
        } else {
            // 修改后的分区不为空 先判断原来的分区存不存在 存在就更新 不存在就插入
            List areaid = td_apDao.getAIDByPID(pipID);
            if (areaid.size() == 0) {
                if (areaID != null) {
                    td_apDao.addID(areaID, pipID);
                }
            } else {
                for (int i = 0; i < areaid.size(); i++) {
                    if (areaID == null) {
                        td_apDao.deletePip(pipID);
                    } else {
                        td_apDao.updateID(areaID, pipID);
                    }
                }
            }
        }

        //修改td_pip_leader表
        td_pip_leaderDao.deletePip(pipID);
        if (pipLeader.get(0)!=-1) {
            for (int i = 0; i < pipLeader.size(); i++) {
                td_pip_leaderDao.addPipLeader(pipLeader.get(i), pipID);
            }
        }


        return 200;



//            if(!pipName.equals(name)) {
//            if (td_pipesDao.getPipNameByName(pipName) != null) {
//                // 分区名称存在 不能修改
//                return 201;
//            } else {
//                td_pipesDao.updatePips(pipID,pipName);
//            }
//
//            //修改td_ap表
//            List areaid = td_apDao.getAIDByPID(pipID);
//            if (areaid.size() == 0) {
//                if (areaID == null) {
//                } else {
//                    td_apDao.addID(areaID, pipID);
//                }
//            } else {
//                for (int i = 0; i < areaid.size(); i++) {
//                    if (areaID == null) {
//                        td_apDao.deletePip(pipID);
//                    } else {
//                        td_apDao.updateID(areaID, pipID);
//                    }
//                }
//            }
//            //修改td_pip_leader表
//            td_pip_leaderDao.deletePip(pipID);
//            if (pipLeader.get(0)!=-1) {
//                for (int i = 0; i < pipLeader.size(); i++) {
//                    td_pip_leaderDao.addPipLeader(pipLeader.get(i), pipID);
//                }
//            }
//
//            return 200;
//        }else{
//            //修改td_ap表
//            List areaid = td_apDao.getAIDByPID(pipID);
//
//            if (areaid.size() == 0) {
//                if (areaID == null) {
////                    td_apDao.addID("0", pipID);
//                } else {
//                    td_apDao.addID(areaID, pipID);
//                }
//            } else {
//                for (int i = 0; i < areaid.size(); i++) {
//                    if (areaID == null) {
////                        td_apDao.updateID("0", pipID);
//                        td_apDao.deletePip(pipID);
//                    } else {
//                        td_apDao.updateID(areaID, pipID);
//                    }
//                }
//            }
//            //修改td_pip_leader表
//            td_pip_leaderDao.deletePip(pipID);
//            if (pipLeader.get(0)!=-1) {
//                for (int i = 0; i < pipLeader.size(); i++) {
//                    td_pip_leaderDao.addPipLeader(pipLeader.get(i), pipID);
//                }
//            }
//
//            return 200;
//        }
    }







}
