package com.water.water.service;
/*
    author：李小杰
    date:3/27/2021
    function:add/update
 */
import com.water.water.dao.*;
import com.water.water.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.sql.Timestamp;
//import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserManageService {
    @Autowired
    UserManageDao userManageDao;
    @Autowired
    td_AreasDao td_areasDao;
    @Autowired
    td_PipesDao td_pipesDao;
    @Autowired
    TerminalsDao terminalsDao;

    @Autowired
    Td_User_RightDao td_user_rightDao;

    //通过用户名称搜索展示用户信息
    public List<UserManage> getByUserManageName(String username){
        return userManageDao.getByUserManageName(username);
    }

    /*
     *   插入员工数据
     */
    public Integer insertTotd_user(UserRight userRight){
     //-----------------------------------------------------------------------------------------
        // 在usermanage表中插入新加入员工信息
        Date date = new Date();//获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);//时间存储为字符串
        Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

        UserManage userManage = new UserManage();
        userManage.setUserID(userRight.getUserID());
        userManage.setUserName(userRight.getUserName());
        userManage.setUserPswd(userRight.getUserPswd());
        userManage.setUClassID(userRight.getUClassID());
        userManage.setUstate(userRight.getUstate());
        userManage.setRegTime(userRight.getRegTime());
        userManage.setMoPhone(userRight.getMoPhone());
        userManage.setRealName(userRight.getRealName());
        userManage.setDPTName(userRight.getDPTName());
        userManage.setRegTime(now);
        userManage.setUstate(1);

        userManageDao.insertTotd_user(userManage);


    //------------------------------------------------------------------------------------------
       //td_areas表进行插入
        String areaID = userRight.getAreaID();

        if(areaID != null){
            String[] areaSplit = areaID.split(",");
            for(int i = 0; i < areaSplit.length; i++){
                //通过AreaID获取AreaLeader
                td_areasDao.updateAreaLeader(userRight.getUserName(),areaSplit[i]);
            }
        }

        //------------------------------------------------------------------------------------------
        //td_pips表进行插入
        String pipeID = userRight.getPipID();

        if(pipeID != null){
            String[] pipeSplit = pipeID.split(",");
            for(int i = 0; i < pipeSplit.length; i++){
                td_pipesDao.updatePipLeader(userRight.getUserName(),pipeSplit[i]);
            }
        }

        //------------------------------------------------------------------------------------------
        //td_terminals表进行插入
        String tmnID = userRight.getTmnId();

        if(tmnID != null){
            String[] tmnSplit = tmnID.split(",");
            for(int i = 0; i < tmnSplit.length; i++){
                terminalsDao.updateTmnLeader(userRight.getUserName(),tmnSplit[i]);
            }
        }

        //------------------------------------------------------------------------------------------
        //td_user_right表进行插入
        Integer right_pp = userRight.getRight_PP();

        UserManage user = userManageDao.getUserID(userRight.getUserName());//新增用户的用户名->用户ID
        Integer userID = user.getUserID();
        td_user_rightDao.addUserRights(userID,right_pp);

        //------------------------------------------------------------------------------------------





//        String PipID = "";
//        String tmnID = "";
//        String AreaID = "";
//
//        System.out.println(userRight);
//
//        //---------------------------------------------------------------------------------------
//        //确定AreaName的位置
//        List areas = td_areasDao.getAreas();
//        //确定PipsName的位置
//        List pips = td_pipesDao.getPipList();
//        //确定Terminals的位置
//        List tmnList = terminalsDao.getAllTmnList();
//
////        String s1 = areas.get(0).toString();
////        String[] split = s1.split(",");
////        for(int m = 0; m< split.length; m++){
////            System.out.println(split[m]);
////        }
////        System.out.println(split[1]);
////        String substring = split[1].substring(split[1].indexOf("\'")+1,split[1].lastIndexOf("\'"));
////        System.out.println(substring);
////        //---------------------------------------------------------------------------------------
//        //未解决的问题：如果说选择的是某一个分区下的某一管线，则该员工拥有管线的控制权，但此时分区的控制权依旧更新为该员工
//        //---------------------------------------------------------------------------------------
//        String[] are_pip_tmn = area_pip_tmn_leader.getArea_Pip_Tmn().split(",");
//
//        int i = 0;
//        int j = 0;
//        int k = 0;
//        int s = 0;
//
//        for(i = 0;i < are_pip_tmn.length;i++){
//            System.out.println("这是我所选中的分区/管线/控制柜名称"+are_pip_tmn[i]);
//
//            for(j = 0; j < areas.size();j++ ){
//                td_Areas td_areas = (td_Areas)areas.get(j);
//
//                System.out.println("这是一次测试的好机会"+td_areas.getAreaName());
//
////                if(are_pip_tmn[i] == split1[j].substring(split1[1].indexOf("\'")+1,split1[1].lastIndexOf("\'"))){
//                if(are_pip_tmn[i].equals(td_areas.getAreaName())){
//                    //涉及到td_Areas的数据插入
////                  td_Areas td_area = td_areasDao.getAreaIDByAreaName(are_pip_tmn[i]);
//                    AreaID = td_areas.getAreaID();
//                    td_areasDao.updateAreaLeader(area_pip_tmn_leader.getUserName(), AreaID);
//                    System.out.println("我这边分区插入完成啦~~~~·");
//                    break;
//                }
//
//                System.out.println(pips.size());
//            }
//
//            for(k = 0; k < pips.size(); k++){
//                td_PIPs td_pips = (td_PIPs) pips.get(k);
//
//                if(are_pip_tmn[i].equals(td_pips.getPipID())){
//                    //PipID = area_pip_tmn_leader.getPipName();
//                    PipID = are_pip_tmn[i];
//
//                    td_pipesDao.updatePipLeader(area_pip_tmn_leader.getUserName(),PipID);
//
//                    System.out.println("我这边管线插入完成啦~~~~·");
//                    System.out.println(area_pip_tmn_leader.getTmnName());
//                }
//
//            }
//
//            for(s = 0; s < tmnList.size(); s++){
//                Terminals terminals = (Terminals) tmnList.get(s);
//
//                if(are_pip_tmn[i].equals(terminals.getTmnName())){
//                    tmnID = terminals.getTmnId();
//                    terminalsDao.updateTmnLeader(area_pip_tmn_leader.getUserName(),tmnID);
//
//                    System.out.println("这边控制柜插入完成");
//                }
//            }
//
//        }
//
//
//
//
//
//
//        //将新增员工所拥有的分区/管线/控制柜权限添加到td_User_Right表
//        UserManage userManage1 = userManageDao.getByusername(userManage.getUserName());
//        System.out.println("这就是我真正的UserID" + userManage1.getUserID());
//        Integer userID = userManage1.getUserID();
//        td_user_rightDao.addUserRight(userID,1,tmnID,PipID,AreaID);
        return 200;
    }




    //删除员工数据
    public Integer deletetd_user(String UserID){
        userManageDao.deletetd_user(UserID);
        return 200;
    }

    //设置状态为0删除员工
    public Integer deleteUser(Integer UserID){
        userManageDao.deleteUser(UserID);
//        td_User_Right td_user_right =  td_user_rightDao.showID(UserID);
//        String areaID = td_user_right.getAreaID();
//        String PipID = td_user_right.getPipID();
//        String tmnID = td_user_right.getTmnID();
//        td_areasDao.updateAreaLeader("",areaID);
//        td_pipesDao.updatePipLeader("",PipID);
//        terminalsDao.updateTmnLeader("",tmnID);
//        td_user_rightDao.updateUserRight(UserID);


        return 200;
    }

    //获取所有员工信息
    public List getAllUserManage(){
//        System.out.println("这是Service层的输出："+userManageDao.getAllUserManage());
        return userManageDao.getAllUserManage();
    }


    /*
     *   修改员工数据
     */
    public Integer updatetd_user(UserRight userRight) {

        //获取修改名称时的新名称以及曾用名称
        String[] name_split = userRight.getUserName().split("\\+");//分割名称
        String origin_name = name_split[name_split.length - 1];//获取修改前名称
        String new_name = name_split[0];

        //-----------------------------------------------------------------------------------------
        //判断权限分配为0，清空所有权限
        if(userRight.getRight_PP() == 0){
            System.out.println("我要将您的权限全部清空");
            Integer userID = userRight.getUserID();
            System.out.println("这是需要进行清空权限的userID"+userID);
            td_User_Right pp = td_user_rightDao.getRight_PP(userID);
            Integer right_pp = pp.getRight_PP();

            if(right_pp == 1){
                //清空其所在分区权限
                List areaIDs = td_areasDao.getAreaID(origin_name);//获取修改前名称对应的分区ID
                for(int i = 0; i < areaIDs.size(); i++) {//设置该分区的AreaLeader为null
                    td_Areas td_areas = (td_Areas) areaIDs.get(i);
                    String ID = td_areas.getAreaID();
                    td_areasDao.updateLeaderByAreaID(ID);
                }
            }else if(right_pp == 2){
                //清空其所在分区权限
                List pipIDs = td_pipesDao.getPipID(origin_name);//获取修改前名称对应的管线ID
                for(int i = 0; i < pipIDs.size(); i++){//设置该管线的PipeLeader为null
                    td_PIPs td_pips = (td_PIPs) pipIDs.get(i);
                    String ID = td_pips.getPipID();
                    td_pipesDao.updateLeaderByPipID(ID);
                }
            }else if(right_pp == 3){
                //清空其所在分区权限
                List tmnIDs = terminalsDao.getTmnIDs(origin_name);//获取修改前名称对应的管线ID
                for(int i = 0; i < tmnIDs.size(); i++){//设置该控制柜的TmnLeader为null
                    Terminals terminals = (Terminals) tmnIDs.get(i);
                    String ID = terminals.getTmnId();
                    terminalsDao.updateLeaderByTmnID(ID);
                }
            }


            Date date = new Date();//获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(date);//时间存储为字符串
            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

            UserManage userManage = new UserManage();
            userManage.setUserID(userRight.getUserID());
            userManage.setUserName(new_name);
            userManage.setUserPswd(userRight.getUserPswd());
            userManage.setUClassID(userRight.getUClassID());
            userManage.setUstate(userRight.getUstate());
            userManage.setRegTime(userRight.getRegTime());
            userManage.setMoPhone(userRight.getMoPhone());
            userManage.setRealName(userRight.getRealName());
            userManage.setDPTName(userRight.getDPTName());
            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);

            //td_user_right表进行更新
            Integer rightpp = userRight.getRight_PP();
            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
            Integer userid = user.getUserID();
            td_user_rightDao.updateUserRights(userid, rightpp);

            return 200;
        } else if(userRight.getRight_PP() == -1){

//            if(userRight.getAreaID() == null && userRight.getPipID() == null && userRight.getTmnId() == null && userRight.getRight_PP() == -1){

                Date date = new Date();//获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(date);//时间存储为字符串
                Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

                UserManage userManage = new UserManage();
                userManage.setUserID(userRight.getUserID());
                userManage.setUserName(new_name);
                userManage.setUserPswd(userRight.getUserPswd());
                userManage.setUClassID(userRight.getUClassID());
                userManage.setUstate(userRight.getUstate());
                userManage.setRegTime(userRight.getRegTime());
                userManage.setMoPhone(userRight.getMoPhone());
                userManage.setRealName(userRight.getRealName());
                userManage.setDPTName(userRight.getDPTName());
                userManage.setModTime(now);
                userManage.setUstate(1);

                userManageDao.updatetd_user(userManage);

                //td_areas表进行更新
                List areaIDs = td_areasDao.getAreaID(origin_name);
                for(int i = 0; i < areaIDs.size(); i++){
                    td_Areas td_areas = (td_Areas) areaIDs.get(i);
                    String id = td_areas.getAreaID();
                    td_areasDao.updateAreaLeader(new_name,id);
                }

                //------------------------------------------------------------------------------------------
                //td_pips表进行更新
                List pipIDs = td_pipesDao.getPipID(origin_name);
                for(int i = 0; i < pipIDs.size(); i++){
                    td_PIPs td_piPs = (td_PIPs) pipIDs.get(i);
                    String id = td_piPs.getPipID();
                    td_pipesDao.updatePipLeader(new_name,id);
                }

                //------------------------------------------------------------------------------------------
                //td_terminals表进行更新
                List tmnIDs = terminalsDao.getTmnIDs(origin_name);
                for(int i = 0; i < tmnIDs.size(); i++){
                    Terminals terminals = (Terminals) tmnIDs.get(i);
                    String id = terminals.getTmnId();
                    terminalsDao.updateTmnLeader(new_name,id);
                }
//            //更新td_user_right表
//            Integer right_pp = userRight.getRight_PP();
//
//            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
//            Integer userID = user.getUserID();
//
//            td_user_rightDao.addUserRights(userID, right_pp);
            return 200;

        } else if(userRight.getAreaID() == null && userRight.getPipID() == null && userRight.getTmnId() == null){
            // 在usermanage表中更新员工信息
            Date date = new Date();//获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(date);//时间存储为字符串
            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

            UserManage userManage = new UserManage();
            userManage.setUserID(userRight.getUserID());
            userManage.setUserName(new_name);
            userManage.setUserPswd(userRight.getUserPswd());
            userManage.setUClassID(userRight.getUClassID());
            userManage.setUstate(userRight.getUstate());
            userManage.setRegTime(userRight.getRegTime());
            userManage.setMoPhone(userRight.getMoPhone());
            userManage.setRealName(userRight.getRealName());
            userManage.setDPTName(userRight.getDPTName());
            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);

            //-----------------------------------------------------------------------------------------
            //将原td_areas表中对应leader重置为null
            List areaIDs = td_areasDao.getAreaID(origin_name);//获取修改前名称对应的分区ID
            for(int i = 0; i < areaIDs.size(); i++){//设置该分区的AreaLeader为null
                td_Areas td_areas = (td_Areas) areaIDs.get(i);
                String ID = td_areas.getAreaID();
                td_areasDao.updateLeaderByAreaID(ID);
            }
            //-----------------------------------------------------------------------------------------
            //将原td_pips表中对应leader重置为null
            List pipIDs = td_pipesDao.getPipID(origin_name);//获取修改前名称对应的管线ID
            for(int i = 0; i < pipIDs.size(); i++){//设置该管线的PipeLeader为null
                td_PIPs td_pips = (td_PIPs) pipIDs.get(i);
                String ID = td_pips.getPipID();
                td_pipesDao.updateLeaderByPipID(ID);
            }
            //-----------------------------------------------------------------------------------------
            //将原td_terminals表中对应leader重置为null
            List tmnIDs = terminalsDao.getTmnIDs(origin_name);//获取修改前名称对应的管线ID
            for(int i = 0; i < tmnIDs.size(); i++){//设置该控制柜的TmnLeader为null
                Terminals terminals = (Terminals) tmnIDs.get(i);
                String ID = terminals.getTmnId();
                terminalsDao.updateLeaderByTmnID(ID);
            }
            //-----------------------------------------------------------------------------------------
            //td_user_right表进行更新
            Integer rightpp = userRight.getRight_PP();
            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
            Integer userid = user.getUserID();
            td_user_rightDao.updateUserRights(userid, rightpp);

        } else {
                System.out.println("修改用户权限呢");
                //------------------------------------------------------------------------------------------
                // 在usermanage表中更新员工信息
                Date date = new Date();//获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(date);//时间存储为字符串
                Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

                UserManage userManage = new UserManage();
                userManage.setUserID(userRight.getUserID());
                userManage.setUserName(new_name);
                userManage.setUserPswd(userRight.getUserPswd());
                userManage.setUClassID(userRight.getUClassID());
                userManage.setUstate(userRight.getUstate());
                userManage.setRegTime(userRight.getRegTime());
                userManage.setMoPhone(userRight.getMoPhone());
                userManage.setRealName(userRight.getRealName());
                userManage.setDPTName(userRight.getDPTName());
                userManage.setModTime(now);
                userManage.setUstate(1);

                userManageDao.updatetd_user(userManage);

                //-----------------------------------------------------------------------------------------
                //将原td_areas表中对应leader重置为null
                List areaIDs = td_areasDao.getAreaID(origin_name);//获取修改前名称对应的分区ID
                for(int i = 0; i < areaIDs.size(); i++){//设置该分区的AreaLeader为null
                    td_Areas td_areas = (td_Areas) areaIDs.get(i);
                    String ID = td_areas.getAreaID();
                    td_areasDao.updateLeaderByAreaID(ID);
                }
                //-----------------------------------------------------------------------------------------
                //将原td_pips表中对应leader重置为null
                List pipIDs = td_pipesDao.getPipID(origin_name);//获取修改前名称对应的管线ID
                for(int i = 0; i < pipIDs.size(); i++){//设置该管线的PipeLeader为null
                    td_PIPs td_pips = (td_PIPs) pipIDs.get(i);
                    String ID = td_pips.getPipID();
                    td_pipesDao.updateLeaderByPipID(ID);
                }
                //-----------------------------------------------------------------------------------------
                //将原td_terminals表中对应leader重置为null
                List tmnIDs = terminalsDao.getTmnIDs(origin_name);//获取修改前名称对应的管线ID
                for(int i = 0; i < tmnIDs.size(); i++){//设置该控制柜的TmnLeader为null
                    Terminals terminals = (Terminals) tmnIDs.get(i);
                    String ID = terminals.getTmnId();
                    terminalsDao.updateLeaderByTmnID(ID);
                }
                //-----------------------------------------------------------------------------------------

                //------------------------------------------------------------------------------------------
                //td_areas表进行更新
                String areaID = userRight.getAreaID();

                if(areaID != null){
                    String[] areaSplit = areaID.split(",");
                    for(int i = 0; i < areaSplit.length; i++){
                        //通过AreaID获取AreaLeader
                        td_areasDao.updateAreaLeader(new_name,areaSplit[i]);
                    }
                }

                //------------------------------------------------------------------------------------------
                //td_pips表进行更新
                String pipeID = userRight.getPipID();

                if(pipeID != null){
                    String[] pipeSplit = pipeID.split(",");
                    for(int i = 0; i < pipeSplit.length; i++){
                        td_pipesDao.updatePipLeader(new_name,pipeSplit[i]);
                    }
                }

                //------------------------------------------------------------------------------------------
                //td_terminals表进行更新
                String tmnID = userRight.getTmnId();

                if(tmnID != null){
                    String[] tmnSplit = tmnID.split(",");
                    for(int i = 0; i < tmnSplit.length; i++){
                        terminalsDao.updateTmnLeader(new_name,tmnSplit[i]);
                    }
                }
                //------------------------------------------------------------------------------------------
                //td_user_right表进行更新
                Integer rightpp = userRight.getRight_PP();
                UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
                Integer userid = user.getUserID();
                td_user_rightDao.updateUserRights(userid, rightpp);

            }

            return 200;
        }



//
//        //更新员工信息
//    public Integer updatetd_user(Area_Pip_Tmn_Leader area_pip_tmn_leader){
////        System.out.println(area_pip_tmn_leader.getUserName());
////        UserManage userManage = new UserManage();
////        userManage.setUserID(area_pip_tmn_leader.getUserID());
////        userManage.setUserName(area_pip_tmn_leader.getUserName());
////        userManage.setUserPswd(area_pip_tmn_leader.getUserPswd());
////        userManage.setUClassID(area_pip_tmn_leader.getUClassID());
////        userManage.setUstate(area_pip_tmn_leader.getUstate());
////        userManage.setRegTime(area_pip_tmn_leader.getRegTime());
////        userManage.setMoPhone(area_pip_tmn_leader.getMoPhone());
////        userManage.setRealName(area_pip_tmn_leader.getRealName());
////        userManage.setDPTName(area_pip_tmn_leader.getDPTName());
////        System.out.println(userManage);
////        userManageDao.updatetd_user(userManage);
//
//        String PipID = "";
//        String tmnID = "";
//        String AreaID = "";
//
//        //这个位置是用来测试修改员工数据的时候，新增权限和不新增权限所带来的差异
//        System.out.println("新增权限和不新增权限所带来的差异" + area_pip_tmn_leader.getArea_Pip_Tmn());
//
//        /*将员工信息更改前的用户所对应的分区/管线/控制柜的权限置空
//            1.分别查找td_areas,td_pips,td_terminals中对应Leader
//            2.与当前更改的员工的名称做对比
//            3.如果两者名称相同，则将对应leader字段值设置为null
//        */
//        System.out.println("这是在创建新用户之初就设定好的用户名");
//
//
//        //---------------------------------------------------------------------------------------
//        //确定AreaName的位置
//        List areas = td_areasDao.getAreas();
//        //确定PipsName的位置
//        List pips = td_pipesDao.getPipList();
//        //确定Terminals的位置
//        List tmnList = terminalsDao.getAllTmnList();
//
//
//        //4.25****************************************************************************************************
//        //将修改功能划分为两种不同的情况，
//        //第一种情况就是只修改除权限分配以外的，判断area_pip_tmn_leader.getArea_Pip_Tmn()为空
//        //将之前设置的每一个分区/管线/控制柜的leader字段修改为新名称即可
//
//        //第二种情况，修改了权限分配功能，判断area_pip_tmn_leader.getArea_Pip_Tmn()不为空
//        //此刻清空之前设置的对应分区/管线/控制柜对应的leader，如果修改了用户名，就用新的用户名取设置新的权限的leader，否则就用原用户名
//        //（如果可以，前端的用户名限制条件应该加入不可以使用+号）
//        if(area_pip_tmn_leader.getArea_Pip_Tmn() == null){
//            String[] name_split = area_pip_tmn_leader.getUserName().split("\\+");
//            System.out.println("这里输出分割后的name的名称是否是原名称"+name_split[name_split.length-1]);
//            System.out.println(area_pip_tmn_leader.getUserName());
//            UserManage userManage = new UserManage();
//            userManage.setUserID(area_pip_tmn_leader.getUserID());
////            userManage.setUserName(name_split[name_split.length-1]);
//            userManage.setUserName(name_split[0]);
//            System.out.println("UserManage之前的名称是:"+name_split[name_split.length-1]);
//            System.out.println("UserManage已经设定好的username是" + userManage.getUserName());
//            userManage.setUserPswd(area_pip_tmn_leader.getUserPswd());
//            userManage.setUClassID(area_pip_tmn_leader.getUClassID());
//            userManage.setUstate(area_pip_tmn_leader.getUstate());
//            userManage.setRegTime(area_pip_tmn_leader.getRegTime());
//            userManage.setMoPhone(area_pip_tmn_leader.getMoPhone());
//            userManage.setRealName(area_pip_tmn_leader.getRealName());
//            userManage.setDPTName(area_pip_tmn_leader.getDPTName());
//            System.out.println(userManage);
//            userManageDao.updatetd_user(userManage);
//
//            //已经可以获取到之前的名称和现在的名称了，将之前名称对应分区/管线/控制柜的leader设置为新的名称
//            List areaLeader = td_areasDao.getAreaLeader();
//
//            for(int i = 0; i < areaLeader.size(); i++){
//                td_Areas td_areas = (td_Areas) areaLeader.get(i);
//                String areasLeader = td_areas.getAreaLeader();
//                System.out.println("这里是areasLeader"+areasLeader);
//                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(areasLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    System.out.println(td_areas.getAreaID());
//                    List area = td_areasDao.getAreaID(name_split[name_split.length - 1]);
//                    for(int j = 0; j < area.size(); j++){
//                        td_Areas td_area = (td_Areas) area.get(j);
//                        System.out.println(td_area.getAreaID());
//                        td_areasDao.updateAreaLeader(userManage.getUserName(),td_area.getAreaID());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//
//
//            List pipLeader = td_pipesDao.getPipLeader();
//
//            for(int i = 0; i < pipLeader.size(); i++){
//                td_PIPs td_piPs = (td_PIPs) pipLeader.get(i);
//                String piPLeader = td_piPs.getPipLeader();
//                System.out.println("这里是piPLeader"+piPLeader);
//                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(piPLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    List pipID = td_pipesDao.getPipID(name_split[name_split.length - 1]);
//                    for(int q = 0; q < pipID.size(); q++){
//                        td_PIPs ps = (td_PIPs) pipID.get(q);
//                        System.out.println(ps.getPipID());
//                        td_pipesDao.updatePipLeader(userManage.getUserName(),ps.getPipID());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//
//            List terminalLeader = terminalsDao.getTerminalLeader();
//
//            for(int i = 0; i < terminalLeader.size(); i++){
//                System.out.println(terminalLeader.size());
//                Terminals terminals = (Terminals) terminalLeader.get(i);
//                String tmnLeader = terminals.getTmnLeader();
//                System.out.println("这里是tmnLeader"+tmnLeader);
//                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(tmnLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    List tmnid = terminalsDao.getTmnIDs(name_split[name_split.length - 1]);
//                    for(int w = 0; w < tmnid.size(); w++){
//                        System.out.println(tmnid.size());
//                        Terminals terminal = (Terminals) tmnid.get(w);
//                        System.out.println(terminal.getTmnId());
//                        terminalsDao.updateTmnLeader(userManage.getUserName(),terminal.getTmnId());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//            System.out.println("TmnLeader输出结束了");
//
//        }else{
//            //将之前的员工的权限设置为null
//            String[] name_split = area_pip_tmn_leader.getUserName().split("\\+");
//            System.out.println("这里输出分割后的name的名称是否是原名称"+name_split[name_split.length-1]);
//            System.out.println(area_pip_tmn_leader.getUserName());
//            UserManage userManage = new UserManage();
//            userManage.setUserID(area_pip_tmn_leader.getUserID());
////            userManage.setUserName(name_split[name_split.length-1]);
//            userManage.setUserName(name_split[0]);
//            System.out.println("UserManage之前的名称是:"+name_split[name_split.length-1]);
//            System.out.println("UserManage已经设定好的username是" + userManage.getUserName());
//            userManage.setUserPswd(area_pip_tmn_leader.getUserPswd());
//            userManage.setUClassID(area_pip_tmn_leader.getUClassID());
//            userManage.setUstate(area_pip_tmn_leader.getUstate());
//            userManage.setRegTime(area_pip_tmn_leader.getRegTime());
//            userManage.setMoPhone(area_pip_tmn_leader.getMoPhone());
//            userManage.setRealName(area_pip_tmn_leader.getRealName());
//            userManage.setDPTName(area_pip_tmn_leader.getDPTName());
//            System.out.println(userManage);
//            userManageDao.updatetd_user(userManage);
//
//            //已经可以获取到之前的名称和现在的名称了，将之前名称对应分区/管线/控制柜的leader设置为新的名称
//            List areaLeader = td_areasDao.getAreaLeader();
//
//            for(int i = 0; i < areaLeader.size(); i++){
//                td_Areas td_areas = (td_Areas) areaLeader.get(i);
//                String areasLeader = td_areas.getAreaLeader();
//                System.out.println("这里是areasLeader"+areasLeader);
//                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(areasLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    System.out.println(td_areas.getAreaID());
//                    List area = td_areasDao.getAreaID(name_split[name_split.length - 1]);
//                    for(int j = 0; j < area.size(); j++){
//                        td_Areas td_area = (td_Areas) area.get(j);
//                        System.out.println(td_area.getAreaID());
//                        td_areasDao.updateAreaLeader(null,td_area.getAreaID());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//
//
//            List pipLeader = td_pipesDao.getPipLeader();
//
//            for(int i = 0; i < pipLeader.size(); i++){
//                td_PIPs td_piPs = (td_PIPs) pipLeader.get(i);
//                String piPLeader = td_piPs.getPipLeader();
//                System.out.println("这里是piPLeader"+piPLeader);
//                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(piPLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    List pipID = td_pipesDao.getPipID(name_split[name_split.length - 1]);
//                    for(int q = 0; q < pipID.size(); q++){
//                        td_PIPs ps = (td_PIPs) pipID.get(q);
//                        System.out.println(ps.getPipID());
//                        td_pipesDao.updatePipLeader(null,ps.getPipID());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//
//            List terminalLeader = terminalsDao.getTerminalLeader();
//
//            for(int i = 0; i < terminalLeader.size(); i++){
////                System.out.println(terminalLeader.size());
//                Terminals terminals = (Terminals) terminalLeader.get(i);
//                String tmnLeader = terminals.getTmnLeader();
////                System.out.println("这里是tmnLeader"+tmnLeader);
////                System.out.println("..................................."+name_split[name_split.length-1]);
//                if(tmnLeader.equals(name_split[name_split.length-1])){
//                    System.out.println(userManage.getUserName());
//                    List tmnid = terminalsDao.getTmnIDs(name_split[name_split.length - 1]);
//                    for(int w = 0; w < tmnid.size(); w++){
//                        System.out.println(tmnid.size());
//                        Terminals terminal = (Terminals) tmnid.get(w);
////                        System.out.println(terminal.getTmnId());
//                        terminalsDao.updateTmnLeader(null,terminal.getTmnId());
//                        System.out.println("更新完成");
//                    }
//                }
//            }
//
//
//
//
//            //将前端传来的选择的权限分配给修改的员工
//            String[] are_pip_tmn = area_pip_tmn_leader.getArea_Pip_Tmn().split(",");
//
//            int i = 0;
//            int j = 0;
//            int k = 0;
//            int s = 0;
//
//            for(i = 0;i < are_pip_tmn.length;i++){
////                System.out.println("这是我所选中的分区/管线/控制柜名称"+are_pip_tmn[i]);
//
//                for(j = 0; j < areas.size();j++ ){
//                    td_Areas td_areas = (td_Areas)areas.get(j);
//
////                    System.out.println("这是一次测试的好机会"+td_areas.getAreaName());
//
////                if(are_pip_tmn[i] == split1[j].substring(split1[1].indexOf("\'")+1,split1[1].lastIndexOf("\'"))){
//                    if(are_pip_tmn[i].equals(td_areas.getAreaName())){
//                        //涉及到td_Areas的数据插入
////                  td_Areas td_area = td_areasDao.getAreaIDByAreaName(are_pip_tmn[i]);
//                        AreaID = td_areas.getAreaID();
//                        td_areasDao.updateAreaLeader(userManage.getUserName(), AreaID);
//                        System.out.println("我这边分区更新完成啦~~~~·");
//                        break;
//                    }
//
//                    System.out.println(pips.size());
//                }
//
//                for(k = 0; k < pips.size(); k++){
//                    td_PIPs td_pips = (td_PIPs) pips.get(k);
//
//                    if(are_pip_tmn[i].equals(td_pips.getPipID())){
//                        //PipID = area_pip_tmn_leader.getPipName();
//                        PipID = are_pip_tmn[i];
//
//                        td_pipesDao.updatePipLeader(userManage.getUserName(),PipID);
//
//                        System.out.println("我这边管线更新完成啦~~~~·");
////                        System.out.println(area_pip_tmn_leader.getTmnName());
//                    }
//
//                }
//
//                for(s = 0; s < tmnList.size(); s++){
//                    Terminals terminals = (Terminals) tmnList.get(s);
//
//                    if(are_pip_tmn[i].equals(terminals.getTmnName())){
//                        tmnID = terminals.getTmnId();
//                        terminalsDao.updateTmnLeader(userManage.getUserName(),tmnID);
//
//                        System.out.println("这边控制柜更新完成");
//                    }
//                }
//
//
//
//            }

        //********************************************************************************************************


//        String s1 = areas.get(0).toString();
//        String[] split = s1.split(",");
//        for(int m = 0; m< split.length; m++){
//            System.out.println(split[m]);
//        }
//        System.out.println(split[1]);
//        String substring = split[1].substring(split[1].indexOf("\'")+1,split[1].lastIndexOf("\'"));
//        System.out.println(substring);
//        //---------------------------------------------------------------------------------------
        //未解决的问题：如果说选择的是某一个分区下的某一管线，则该员工拥有管线的控制权，但此时分区的控制权依旧更新为该员工
        //---------------------------------------------------------------------------------------
//        String[] are_pip_tmn = area_pip_tmn_leader.getArea_Pip_Tmn().split(",");
//
//
//        int i = 0;
//        int j = 0;
//        int k = 0;
//        int s = 0;

//        for(i = 0;i < are_pip_tmn.length;i++){
//            System.out.println("这是我所选中的分区/管线/控制柜名称"+are_pip_tmn[i]);
//
//            for(j = 0; j < areas.size();j++ ){
//                td_Areas td_areas = (td_Areas)areas.get(j);
//
//                System.out.println("这是一次测试的好机会"+td_areas.getAreaName());
//
////                if(are_pip_tmn[i] == split1[j].substring(split1[1].indexOf("\'")+1,split1[1].lastIndexOf("\'"))){
//                if(are_pip_tmn[i].equals(td_areas.getAreaName())){
//                    //涉及到td_Areas的数据插入
////                  td_Areas td_area = td_areasDao.getAreaIDByAreaName(are_pip_tmn[i]);
//                    AreaID = td_areas.getAreaID();
//                    td_areasDao.updateAreaLeader(area_pip_tmn_leader.getUserName(), AreaID);
//                    System.out.println("我这边分区更新完成啦~~~~·");
//                    break;
//                }
//
//                System.out.println(pips.size());
//            }
//
//            for(k = 0; k < pips.size(); k++){
//                td_PIPs td_pips = (td_PIPs) pips.get(k);
//
//                if(are_pip_tmn[i].equals(td_pips.getPipID())){
//                    //PipID = area_pip_tmn_leader.getPipName();
//                    PipID = are_pip_tmn[i];
//
//                    td_pipesDao.updatePipLeader(area_pip_tmn_leader.getUserName(),PipID);
//
//                    System.out.println("我这边管线更新完成啦~~~~·");
//                    System.out.println(area_pip_tmn_leader.getTmnName());
//                }
//
//            }
//
//            for(s = 0; s < tmnList.size(); s++){
//                Terminals terminals = (Terminals) tmnList.get(s);
//
//                if(are_pip_tmn[i].equals(terminals.getTmnName())){
//                    tmnID = terminals.getTmnId();
//                    terminalsDao.updateTmnLeader(area_pip_tmn_leader.getUserName(),tmnID);
//
//                    System.out.println("这边控制柜更新完成");
//                }
//            }

//        }
//
//
////        return 200;
////    }

    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }

    //根据员工姓名获取员工具体信息
    public List getUserMessageByName(String UserName){
        return userManageDao.getByUserManageName(UserName);
    }
}
