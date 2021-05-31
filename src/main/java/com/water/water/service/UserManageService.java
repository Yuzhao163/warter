package com.water.water.service;
/*
    author：李小杰
    date:3/27/2021
    function:add/update
 */
import com.water.water.dao.*;
import com.water.water.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserManageService {
    @Autowired
    UserManageDao userManageDao;
    @Autowired
    td_Area_LeaderDao td_area_leaderDao;
    @Autowired
    td_Pip_LeaderDao td_pip_leaderDao;
    @Autowired
    td_Tmn_LeaderDao td_tmn_leaderDao;
    @Autowired
    Td_User_RightDao td_user_rightDao;

    //通过用户名称搜索展示用户信息
    public List<UserManage> getByUserManageName(String username){
        return userManageDao.getByUserManageName(username);
    }

    /*
     *   插入员工数据
     */
    /*
     *   插入员工数据
     */
    public Integer insertTotd_user(UserRight userRight){
        //-----------------------------------------------------------------------------------------
        // 在usermanage表中插入新加入员工信息
//        Date date = new Date();//获取当前时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str = sdf.format(date);//时间存储为字符串
//        Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

        UserManage userManage = new UserManage();
        userManage.setUserID(userRight.getUserID());
        userManage.setUserName(userRight.getUserName());
        userManage.setUserPswd(userRight.getUserPswd());
        userManage.setUClassID(userRight.getUClassID());
        //userManage.setUstate(userRight.getUstate());
        userManage.setRegTime(userRight.getRegTime());
        userManage.setMoPhone(userRight.getMoPhone());
        userManage.setRealName(userRight.getRealName());
        userManage.setDPTName(userRight.getDPTName());
//        userManage.setRegTime(now);
        userManage.setUstate(1);

        userManageDao.insertTotd_user(userManage);

        //------------------------------------------------------------------------------------------
        //5.3新建td_area_leader表，存储Leader和AreaID关系
        String areaID = userRight.getAreaID();
        //5.7通过新添加的用户的名称，查找其对应UserID
        UserManage user = userManageDao.getUserID(userManage.getUserName());
        Integer userID = user.getUserID();

        if(areaID != null && areaID != ""){

            //将多选的AreaID进行分割
            String[] areaSplit = areaID.split(",");

            //将每一个分割的AreaID，对应的Leader进行更新操作
            for(int i = 0; i < areaSplit.length; i++){

                //将分割后的AreaID，和对应新增用户的UserName插入到td_Area_Leader表中
                td_area_leaderDao.addAreaLeader(userID,areaSplit[i]);
            }
        }


        //------------------------------------------------------------------------------------------
        //5.2，存在一个管线对应多个Leader，需要在后面追加名称
        String pipeID = userRight.getPipID();

        if(pipeID != null && pipeID != ""){

            //将多选的pipeID进行分割
            String[] pipeSplit = pipeID.split(",");

            //将每一个分割的PipID，对应的Leader进行更新操作
            for(int i = 0; i < pipeSplit.length; i++){


                //将分割后的PipID，和对应新增用户的UserName插入到td_Pip_Leader表中
                td_pip_leaderDao.addPipLeader(userID,pipeSplit[i]);

            }
        }

        //------------------------------------------------------------------------------------------
        //5.2，存在一个控制柜对应多个Leader，需要在后面追加名称
        String tmnID = userRight.getTmnId();

        if(tmnID != null && tmnID != ""){

            //将多选的pipeID进行分割
            String[] tmnSplit = tmnID.split(",");

            //将每一个分割的PipID，对应的Leader进行更新操作
            for(int i = 0; i < tmnSplit.length; i++){


                //将分割后的TmnID，和对应新增用户的UserName插入到td_Tmn_Leader表中
                td_tmn_leaderDao.addTmnLeader(userID,tmnSplit[i]);
            }
        }


        //------------------------------------------------------------------------------------------
//        //td_user_right表进行插入
//        Integer right_pp = userRight.getRight_PP();
//
//        user = userManageDao.getUserID(userRight.getUserName());//新增用户的用户名->用户ID
//        userID = user.getUserID();
//        td_user_rightDao.addUserRights(userID,right_pp);

        //------------------------------------------------------------------------------------------

        return 200;
    }


    //删除员工数据
    public Integer deletetd_user(String UserID){
        //通过UserID获取UserName
        UserManage user = userManageDao.getUserNameByID(UserID);
        String userName = user.getUserName();

        //删除usermanage表中的数据
        userManageDao.deletetd_user(UserID);

//        //删除userRight表中的数据
//        td_user_rightDao.deleteUser(UserID);

        //通过UserName查询三张Leader表中是否存在该名用户的权限
        List arealist = td_area_leaderDao.showLeader(Integer.parseInt(UserID));
        List piplist = td_pip_leaderDao.showLeader(Integer.parseInt(UserID));
        List tmnlist = td_tmn_leaderDao.showLeader(Integer.parseInt(UserID));
        //System.out.println(arealist.size());
        //System.out.println(piplist.size());
        //System.out.println(tmnlist.size());

        //删除该员工的权限
        if(arealist.size() != 0){
            //根据员工名称删除其分区权限
            td_area_leaderDao.deleteRight(Integer.parseInt(UserID));

            //System.out.println("area已删除");

        }else if(piplist.size() != 0){
            //根据员工名称删除其管线权限
            td_pip_leaderDao.deleteRight(Integer.parseInt(UserID));

            //System.out.println("pip已删除");
        }else if(tmnlist.size() != 0){
            //根据员工名称删除其控制柜
            td_tmn_leaderDao.deleteRight(Integer.parseInt(UserID));
            //System.out.println("tmn已删除");

        }
        return 200;
    }

    //获取所有员工信息
    public List getAllUserManage(@Param(value="UserName") String UserName, Integer page, Integer size){
//        System.out.println("这是Service层的输出："+userManageDao.getAllUserManage());

        if (page != null && size != null){
            page = (page - 1) * size;
        }


        //5.18
        List allUserManage = userManageDao.getAllUserManage("%"+UserName+"%",page, size);
        System.out.println("ZHESHIALLUSERMANAGE"+allUserManage);
        List UserManageRight = new ArrayList();

        for(int i = 0; i < allUserManage.size(); i++){
            List areaRightList = new ArrayList();
            List pipRightList = new ArrayList();
            List tmnRightList = new ArrayList();

            UserManageRight userManageRight = new UserManageRight();
            UserManage userManage = (UserManage) allUserManage.get(i);
            userManageRight.setUserID(userManage.getUserID());
            userManageRight.setUserName(userManage.getUserName());
            userManageRight.setUserPswd(userManage.getUserPswd());
            userManageRight.setUClassID(userManage.getUClassID());
            userManageRight.setUstate(userManage.getUstate());
            userManageRight.setRegTime(userManage.getRegTime());
            userManageRight.setMoPhone(userManage.getMoPhone());
            userManageRight.setRealName(userManage.getRealName());
            userManageRight.setDPTName(userManage.getDPTName());
            userManageRight.setModTime(userManage.getModTime());

            Integer userID = userManage.getUserID();
            List areaRight = td_area_leaderDao.getRightByID(userID);
            List pipRight = td_pip_leaderDao.getRightByID(userID);
            List tmnRight = td_tmn_leaderDao.getRightByID(userID);
//            td_User_Right right_pp = td_user_rightDao.getRight_PP(userID);
//            if(right_pp != null){
//                Integer right = right_pp.getRight_PP();
//                userManageRight.setRight_PP(right);
//            }


            if(areaRight.size() != 0){
                for(int j = 0; j < areaRight.size(); j++){
                    td_Area_Leader td_area_leader = (td_Area_Leader) areaRight.get(j);
                    String id = td_area_leader.getAreaID();
                    areaRightList.add(id);
                }
                userManageRight.setAreaID(areaRightList);
            }else if(pipRight.size() != 0){
                for(int j = 0; j < pipRight.size(); j++){
                    td_Pip_Leader td_pip_leader = (td_Pip_Leader) pipRight.get(j);
                    String id = td_pip_leader.getPipID();
                    pipRightList.add(id);
                }
                userManageRight.setPipID(pipRightList);
            }else if(tmnRight.size() != 0){
                for(int j = 0; j < tmnRight.size(); j++){
                    td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) tmnRight.get(j);
                    String id = td_tmn_leader.getTmnID();
                    tmnRightList.add(id);
                }
                userManageRight.setTmnID(tmnRightList);
            }

            UserManageRight.add(userManageRight);
        }
        System.out.println(UserManageRight);
        return UserManageRight;

    }



    /*
     *   修改员工数据
     */
    public Integer updatetd_user(UserRight userRight) {

        //获取修改名称时的新名称以及曾用名称
        String[] name_split = userRight.getUserName().split("\\+");//分割名称
        String origin_name = name_split[name_split.length - 1];//获取修改前名称
        String new_name = name_split[0];

//        //判断权限分配为0，清空所有权限
//        if(userRight.getRight_PP() == 0){
//            System.out.println("我要将您的权限全部清空");
//
//            Integer userID = userRight.getUserID();//获取前端提交的UserID
//            System.out.println("这是需要进行清空权限的userID"+userID);
//
//            td_User_Right pp = td_user_rightDao.getRight_PP(userID);//通过该UserID去td_user_right表中对应Right_PP
//            Integer right_pp = pp.getRight_PP();
//
//            if(right_pp == 1) { //如果该人权限为分区管理员，则清空其分区管理员权限
//                //用username做条件，删除其在td_area_leader表中所拥有的权限
//                System.out.println("您删除分区权限的用户ID是"+userRight.getUserID());
//
//                td_area_leaderDao.deleteRight(userRight.getUserID());
//
//            }else if(right_pp == 2){//如果该人权限为管线管理员，则清空其管线管理员权限
//                //用username做条件，删除其在td_pip_leader表中所拥有的权限
//                System.out.println("您删除管线权限的用户ID是"+userRight.getUserID());
//
//                td_pip_leaderDao.deleteRight(userRight.getUserID());
//
//            }else if(right_pp == 3){//如果该人权限为控制柜管理员，则清空其控制柜管理员权限
//                //用username做条件，删除其在td_tmn_leader表中所拥有的权限
//                System.out.println("您删除控制柜权限的用户ID是"+userRight.getUserID());
//
//                td_tmn_leaderDao.deleteRight(userRight.getUserID());
//            }
//
//
////            Date date = new Date();//获取当前时间
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            String str = sdf.format(date);//时间存储为字符串
////            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp
//
//            UserManage userManage = new UserManage();
//            userManage.setUserID(userRight.getUserID());
//            userManage.setUserName(new_name);
//            userManage.setUserPswd(userRight.getUserPswd());
//            userManage.setUClassID(userRight.getUClassID());
//            userManage.setUstate(userRight.getUstate());
//            userManage.setRegTime(userRight.getRegTime());
//            userManage.setMoPhone(userRight.getMoPhone());
//            userManage.setRealName(userRight.getRealName());
//            userManage.setDPTName(userRight.getDPTName());
////            userManage.setModTime(now);
//            userManage.setUstate(1);
//
//            userManageDao.updatetd_user(userManage);
//
//            //td_user_right表进行更新
//            Integer rightpp = userRight.getRight_PP();
//            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
//            Integer userid = user.getUserID();
//            td_user_rightDao.updateUserRights(userid, rightpp);
//
//            return 200;

        //5.18
        //判断UClassID为3，该员工为职工管理员
        if(Integer.parseInt(userRight.getUClassID()) == 3){
            System.out.println("该员工权限为员工管理员");

            Integer userID = userRight.getUserID();//获取前端提交的UserID
            System.out.println("这是设置为员工管理员的userID"+userID);

//            td_User_Right pp = td_user_rightDao.getRight_PP(userID);//通过该UserID去td_user_right表中对应Right_PP
//            Integer right_pp = pp.getRight_PP();
            //得到被修改人员的UserID
            Integer UID = userRight.getUserID();
            //用这个UID去找这个员工原本的UClassID
            UserManage userByID = userManageDao.getUserByID(UID);
            String uclassID = userByID.getUClassID();
            //用这个该员工原本的uclassID去判断应该去哪个表中删除权限
            if(Integer.parseInt(uclassID) == 201){
                td_area_leaderDao.deleteRight(UID);
            } else if(Integer.parseInt(uclassID) == 202){
                td_pip_leaderDao.deleteRight(UID);
            } else if(Integer.parseInt(uclassID) == 203){
                td_tmn_leaderDao.deleteRight(UID);
            }

//            if(right_pp == 1) { //如果该人权限为分区管理员，则清空其分区管理员权限
//                //用username做条件，删除其在td_area_leader表中所拥有的权限
//                System.out.println("您删除分区权限的用户ID是"+userRight.getUserID());
//
//                td_area_leaderDao.deleteRight(userRight.getUserID());
//
//            }else if(right_pp == 2){//如果该人权限为管线管理员，则清空其管线管理员权限
//                //用username做条件，删除其在td_pip_leader表中所拥有的权限
//                System.out.println("您删除管线权限的用户ID是"+userRight.getUserID());
//
//                td_pip_leaderDao.deleteRight(userRight.getUserID());
//
//            }else if(right_pp == 3){//如果该人权限为控制柜管理员，则清空其控制柜管理员权限
//                //用username做条件，删除其在td_tmn_leader表中所拥有的权限
//                System.out.println("您删除控制柜权限的用户ID是"+userRight.getUserID());
//
//                td_tmn_leaderDao.deleteRight(userRight.getUserID());
//            }


//            Date date = new Date();//获取当前时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String str = sdf.format(date);//时间存储为字符串
//            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

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
//            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);

//            //td_user_right表进行更新
//            Integer rightpp = userRight.getRight_PP();
//            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
//            Integer userid = user.getUserID();
//            td_user_rightDao.updateUserRights(userid, rightpp);

            return 200;
        } //else if(userRight.getRight_PP() == -1){//保留权限
        else if(Integer.parseInt(userRight.getUClassID()) == 201) {//保留权限
//            Date date = new Date();//获取当前时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String str = sdf.format(date);//时间存储为字符串
//            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

            //5.18拿该职工的UserID去查看其原先的UClassID
            System.out.println("该员工权限为分区管理员");

            Integer userID = userRight.getUserID();//获取前端提交的UserID
            System.out.println("这是设置为分区管理员的userID" + userID);

            //得到被修改人员的UserID
            Integer UID = userRight.getUserID();
            //用这个UID去找这个员工原本的UClassID
            UserManage userByID = userManageDao.getUserByID(UID);
            String uclassID = userByID.getUClassID();

            //用这个该员工原本的uclassID去判断应该去哪个表中删除权限
            if (Integer.parseInt(uclassID) == 201) {
                td_area_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 202) {
                td_pip_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 203) {
                td_tmn_leaderDao.deleteRight(UID);
            }

            //因为UClassID为201，所以往td_area_Leader中插入新的数据
            //td_area_leader表进行更新
            String areaID = userRight.getAreaID();

            //5.18通过新添加的用户的名称，查找其对应UserID
            if (areaID != null && areaID != "") {
                String[] areaSplit = areaID.split(",");
                for (int i = 0; i < areaSplit.length; i++) {
                    //通过AreaID获取AreaLeader
//                        td_areasDao.updateAreaLeader(new_name,areaSplit[i]);
                    //将新的权限插入td_area_leader表
                    td_area_leaderDao.addAreaLeader(userID, areaSplit[i]);
                }
            }

            //5.18更新td_user表
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
//            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);


            return 200;
        }
        else if(Integer.parseInt(userRight.getUClassID()) == 202){
//        } else if((userRight.getAreaID() == null || userRight.getAreaID() == "")
//                && (userRight.getPipID() == null || userRight.getPipID() == "")
//                && (userRight.getTmnId() == null || userRight.getTmnId() == "")){
//            // 在usermanage表中更新员工信息
//            Date date = new Date();//获取当前时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String str = sdf.format(date);//时间存储为字符串
//            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp

            //5.18拿该职工的UserID去查看其原先的UClassID
            System.out.println("该员工权限为控制柜管理员");

            Integer userID = userRight.getUserID();//获取前端提交的UserID
            System.out.println("这是设置为分区管理员的userID" + userID);

            //5.18得到被修改人员的UserID
            Integer UID = userRight.getUserID();
            //用这个UID去找这个员工原本的UClassID
            UserManage userByID = userManageDao.getUserByID(UID);
            String uclassID = userByID.getUClassID();

            //5.18用这个该员工原本的uclassID去判断应该去哪个表中删除权限
            if (Integer.parseInt(uclassID) == 201) {
                td_area_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 202) {
                td_pip_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 203) {
                td_tmn_leaderDao.deleteRight(UID);
            }

            //5.18td_pip_leader表进行更新
            String pipeID = userRight.getPipID();

            if(pipeID != null && pipeID != ""){
                String[] pipeSplit = pipeID.split(",");
                for(int i = 0; i < pipeSplit.length; i++){
//                        td_pipesDao.updatePipLeader(new_name,pipeSplit[i]);
                    td_pip_leaderDao.addPipLeader(userID,pipeSplit[i]);
                }
            }

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
//            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);

//            //将之前所有的权限都给清空之后，赋予新的权限，这个权限没有选择具体的分区/管线/控制柜
//            //-----------------------------------------------------------------------------------------
//
//            td_area_leaderDao.deleteRight(userRight.getUserID());
//
//            //-----------------------------------------------------------------------------------------
//
//            td_pip_leaderDao.deleteRight(userRight.getUserID());
//
//            //-----------------------------------------------------------------------------------------
//
//            td_tmn_leaderDao.deleteRight(userRight.getUserID());
//            //-----------------------------------------------------------------------------------------
//            //td_user_right表进行更新
//            Integer rightpp = userRight.getRight_PP();
//            UserManage user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
//            Integer userid = user.getUserID();
//            td_user_rightDao.updateUserRights(userid, rightpp);
            return 200;
        } if(Integer.parseInt(userRight.getUClassID()) == 203){
//        else {
//            System.out.println("修改用户权限呢");
            //------------------------------------------------------------------------------------------
            // 在usermanage表中更新员工信息
//            Date date = new Date();//获取当前时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String str = sdf.format(date);//时间存储为字符串
//            Timestamp now = Timestamp.valueOf(str);//转换时间字符串为Timestamp


            //5.18拿该职工的UserID去查看其原先的UClassID
            System.out.println("该员工权限为控制柜管理员");

            Integer userID = userRight.getUserID();//获取前端提交的UserID
            System.out.println("这是设置为分区管理员的userID" + userID);

            //5.18得到被修改人员的UserID
            Integer UID = userRight.getUserID();
            //5.18用这个UID去找这个员工原本的UClassID
            UserManage userByID = userManageDao.getUserByID(UID);
            String uclassID = userByID.getUClassID();

            //5.18用这个该员工原本的uclassID去判断应该去哪个表中删除权限
            if (Integer.parseInt(uclassID) == 201) {
                td_area_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 202) {
                td_pip_leaderDao.deleteRight(UID);
            } else if (Integer.parseInt(uclassID) == 203) {
                td_tmn_leaderDao.deleteRight(UID);
            }

            //5.18td_tmn_leader表进行更新
            String tmnID = userRight.getTmnId();

            if(tmnID != null && tmnID != ""){
                String[] tmnSplit = tmnID.split(",");
                for(int i = 0; i < tmnSplit.length; i++){
//                        terminalsDao.updateTmnLeader(new_name,tmnSplit[i]);
                    td_tmn_leaderDao.addTmnLeader(userID,tmnSplit[i]);
                }
            }

            //5.18修改td_user
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
//            userManage.setModTime(now);
            userManage.setUstate(1);

            userManageDao.updatetd_user(userManage);

//            //-----------------------------------------------------------------------------------------
//
//            td_area_leaderDao.deleteRight(userRight.getUserID());
//            //-----------------------------------------------------------------------------------------
//
//            td_pip_leaderDao.deleteRight(userRight.getUserID());
//            //-----------------------------------------------------------------------------------------
//
//            td_tmn_leaderDao.deleteRight(userRight.getUserID());
//            //-----------------------------------------------------------------------------------------
//
//            //------------------------------------------------------------------------------------------
//            //td_area_leader表进行更新
//            String areaID = userRight.getAreaID();
//
//            //5.7通过新添加的用户的名称，查找其对应UserID
//            UserManage user = userManageDao.getUserID(userManage.getUserName());
//            Integer userID = user.getUserID();
//
//            if(areaID != null && areaID != ""){
//                String[] areaSplit = areaID.split(",");
//                for(int i = 0; i < areaSplit.length; i++){
//                    //通过AreaID获取AreaLeader
////                        td_areasDao.updateAreaLeader(new_name,areaSplit[i]);
//                    //将新的权限插入td_area_leader表
//                    td_area_leaderDao.addAreaLeader(userID,areaSplit[i]);
//                }
//            }
//
//            //------------------------------------------------------------------------------------------
//            //td_pip_leader表进行更新
//            String pipeID = userRight.getPipID();
//
//            if(pipeID != null && pipeID != ""){
//                String[] pipeSplit = pipeID.split(",");
//                for(int i = 0; i < pipeSplit.length; i++){
////                        td_pipesDao.updatePipLeader(new_name,pipeSplit[i]);
//                    td_pip_leaderDao.addPipLeader(userID,pipeSplit[i]);
//                }
//            }
//
//            //------------------------------------------------------------------------------------------
//            //td_tmn_leader表进行更新
//            String tmnID = userRight.getTmnId();
//
//            if(tmnID != null && tmnID != ""){
//                String[] tmnSplit = tmnID.split(",");
//                for(int i = 0; i < tmnSplit.length; i++){
////                        terminalsDao.updateTmnLeader(new_name,tmnSplit[i]);
//                    td_tmn_leaderDao.addTmnLeader(userID,tmnSplit[i]);
//                }
//            }
//            //------------------------------------------------------------------------------------------
//            //td_user_right表进行更新
//            Integer rightpp = userRight.getRight_PP();
//            user = userManageDao.getUserID(new_name);//新增用户的用户名->用户ID
//            Integer userid = user.getUserID();
//            td_user_rightDao.updateUserRights(userid, rightpp);
//
        }

        return 200;
    }



    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }

    //根据员工姓名获取员工具体信息
    public List getUserMessageByName(String UserName){
        return userManageDao.getByUserManageName(UserName);
    }

    //5.3将前端的未修改的名称传往后端，与数据库进行对比
    public List getAllUser(String userName) {
       // System.out.println("这是我拿到的不同于修改窗口的用户名称"+userManageDao.getAllUser
        // (userName));
        return userManageDao.getAllUser(userName);
    }

    //5.19统计用户个数
    public Integer getCountNum(String UserName) {
        //System.out.println("得到的所有的员工人数为"+userManageDao.getCountNum(UserName));
        System.out.println("%"+UserName+"%");
        //Integer countNum = 0;
        //if(UserName == null){
        //countNum = userManageDao.getCountNum(UserName);
        //}else{
        Integer countNum = userManageDao.getCountNum("%" + UserName + "%");
        //}
        //Integer countNum = userManageDao.getCountNum("%" + UserName + "%");
//        return userManageDao.getCountNum(UserName);
        return countNum;
    }


    // 05.18 被忽视的个人信息部分
    //lmh
    //  判断修改后的用户名是否重复
    public Integer checkUserName(Integer userID,String userName) {
        //      将用户名与数据库中的不是该id的用户名进行比较
        List result = userManageDao.checkUserName(userID,userName);
        if (result.size() != 0) {
            // 存在用户名 返回用户名存在状态码
            return 201;
        } else
            return 200;
    }

    //  根据用户id修改个人信息
    public Integer updateUserInfo(UserManage userManage) {
        // 现在只需要对用户表进行更新
        userManageDao.updateUserInfo(userManage);

        return 200;
    }


    //5.25添加员工名称是否重复判断
    public String getNameRepulicate(String userName) {
        return userManageDao.getNameRepulicate(userName);
    }

}
