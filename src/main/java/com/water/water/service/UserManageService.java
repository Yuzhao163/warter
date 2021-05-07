package com.water.water.service;
/*
    author：李小杰
    date:3/27/2021
    function:add/update
 */
import com.water.water.dao.*;
import com.water.water.pojo.User;
import com.water.water.pojo.UserManage;
import com.water.water.pojo.UserRight;
import com.water.water.pojo.td_User_Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
//    //插入员工数据
//    public Integer insertTotd_user(UserManage userManage){
//        if(userManage.getUserName().isEmpty()){
//            userManage.setUserName("admin"+userManage.getRealName());
//        }
//        if(userManage.getUserPswd().isEmpty()) {
//            userManage.setUserPswd("123456");
//            userManageDao.insertTotd_user(userManage);
//        } else{
//            userManageDao.insertTotd_user(userManage);
//        }
//        return 200;
//    }

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
        //userManage.setUstate(userRight.getUstate());
        userManage.setRegTime(userRight.getRegTime());
        userManage.setMoPhone(userRight.getMoPhone());
        userManage.setRealName(userRight.getRealName());
        userManage.setDPTName(userRight.getDPTName());
        userManage.setRegTime(now);
        userManage.setUstate(1);

        userManageDao.insertTotd_user(userManage);

        //------------------------------------------------------------------------------------------
        //5.3新建td_area_leader表，存储Leader和AreaID关系
        String areaID = userRight.getAreaID();

        if(areaID != null && areaID != ""){

            //标注新员工选择的分区控制权限之前是否为空
            boolean flag = false;

            //将多选的AreaID进行分割
            String[] areaSplit = areaID.split(",");

            //将每一个分割的AreaID，对应的Leader进行更新操作
            for(int i = 0; i < areaSplit.length; i++){

                //将分割后的AreaID，和对应新增用户的UserName插入到td_Area_Leader表中
                td_area_leaderDao.addAreaLeader(userRight.getUserName(),areaSplit[i]);
            }
        }


        //------------------------------------------------------------------------------------------
        //5.2，存在一个管线对应多个Leader，需要在后面追加名称
        String pipeID = userRight.getPipID();

        if(pipeID != null && pipeID != ""){

            //标注新员工选择的分区控制权限之前是否为空
            boolean flag = false;

            //将多选的pipeID进行分割
            String[] pipeSplit = pipeID.split(",");

            //将每一个分割的PipID，对应的Leader进行更新操作
            for(int i = 0; i < pipeSplit.length; i++){

                //将分割后的PipID，和对应新增用户的UserName插入到td_Pip_Leader表中
                td_pip_leaderDao.addPipLeader(userRight.getUserName(),pipeSplit[i]);

            }
        }

        //------------------------------------------------------------------------------------------
        //5.2，存在一个控制柜对应多个Leader，需要在后面追加名称
        String tmnID = userRight.getTmnId();

        if(tmnID != null && tmnID != ""){

            //标注新员工选择的分区控制权限之前是否为空
            boolean flag = false;

            //将多选的pipeID进行分割
            String[] tmnSplit = tmnID.split(",");

            //将每一个分割的PipID，对应的Leader进行更新操作
            for(int i = 0; i < tmnSplit.length; i++){

                //将分割后的TmnID，和对应新增用户的UserName插入到td_Tmn_Leader表中
                td_tmn_leaderDao.addTmnLeader(userRight.getUserName(),tmnSplit[i]);
            }
        }


        //------------------------------------------------------------------------------------------
        //td_user_right表进行插入
        Integer right_pp = userRight.getRight_PP();

        UserManage user = userManageDao.getUserID(userRight.getUserName());//新增用户的用户名->用户ID
        Integer userID = user.getUserID();
        td_user_rightDao.addUserRights(userID,right_pp);

        //------------------------------------------------------------------------------------------

        return 200;
    }

//    //删除员工数据
//    public Integer deletetd_user(String UserID){
//        userManageDao.deletetd_user(UserID);
//        userManageDao.delete_user(UserID);
//        return 200;
//    }

    //删除员工数据
    public Integer deletetd_user(String UserID){
        //通过UserID获取UserName
        UserManage user = userManageDao.getUserNameByID(UserID);
        String userName = user.getUserName();

        //删除usermanage表中的数据
        userManageDao.deletetd_user(UserID);

        //删除userRight表中的数据
        td_user_rightDao.deleteUser(UserID);

        //通过UserName查询三张Leader表中是否存在该名用户的权限
        List arealist = td_area_leaderDao.showLeader(userName);
        List piplist = td_pip_leaderDao.showLeader(userName);
        List tmnlist = td_tmn_leaderDao.showLeader(userName);

        System.out.println(arealist.size());
        System.out.println(piplist.size());
        System.out.println(tmnlist.size());

        //删除该员工的权限
        if(arealist.size() != 0){
            //根据员工名称删除其分区权限
            td_area_leaderDao.deleteRight(userName);

            System.out.println("area已删除");

        }else if(piplist.size() != 0){
            //根据员工名称删除其管线权限
            td_pip_leaderDao.deleteRight(userName);
            System.out.println("pip已删除");
        }else if(tmnlist.size() != 0){
            //根据员工名称删除其控制柜
            td_tmn_leaderDao.deleteRight(userName);
            System.out.println("tmn已删除");

        }
        return 200;
    }

    //获取所有员工信息
    public List<UserManage> getAllUserManage(){
        return userManageDao.getAllUserManage();
    }
//    //更新员工信息
//    public Integer updatetd_user(UserManage userManage){
//        userManageDao.updatetd_user(userManage);
//        userManageDao.update_user(userManage);
//        return 200;
//    }

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

            Integer userID = userRight.getUserID();//获取前端提交的UserID
            System.out.println("这是需要进行清空权限的userID"+userID);

            td_User_Right pp = td_user_rightDao.getRight_PP(userID);//通过该UserID去td_user_right表中对应Right_PP
            Integer right_pp = pp.getRight_PP();

            if(right_pp == 1) { //如果该人权限为分区管理员，则清空其分区管理员权限
                //用username做条件，删除其在td_area_leader表中所拥有的权限
                System.out.println("您删除分区权限的用户ID是"+userRight.getUserID());

                td_area_leaderDao.deleteRight(origin_name);

            }else if(right_pp == 2){//如果该人权限为管线管理员，则清空其管线管理员权限
                //用username做条件，删除其在td_pip_leader表中所拥有的权限
                System.out.println("您删除管线权限的用户ID是"+userRight.getUserID());

                td_pip_leaderDao.deleteRight(origin_name);

            }else if(right_pp == 3){//如果该人权限为控制柜管理员，则清空其控制柜管理员权限
                //用username做条件，删除其在td_tmn_leader表中所拥有的权限
                System.out.println("您删除控制柜权限的用户ID是"+userRight.getUserID());

                td_tmn_leaderDao.deleteRight(origin_name);
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

            List arealist = td_area_leaderDao.showLeader(origin_name);
            List piplist = td_pip_leaderDao.showLeader(origin_name);
            List tmnlist = td_tmn_leaderDao.showLeader(origin_name);

            System.out.println(arealist.size());
            System.out.println(piplist.size());
            System.out.println(tmnlist.size());

            if(arealist.size() != 0){

                //td_areas表进行更新
                //修改td_area_leader中Leader为修改前名称的Leader为新名称
                td_area_leaderDao.updateLeader(new_name,origin_name);
                System.out.println("area修改成功");
            }else if(piplist.size() != 0){

                //td_pips表进行更新
                //修改td_pip_leader中Leader为修改前名称的Leader为新名称
                td_pip_leaderDao.updateLeader(new_name,origin_name);
                System.out.println("pip修改成功");
            }else if(tmnlist.size() != 0){

                //修改td_terminlal_leader中Leader为修改前名称的Leader为新名称
                //td_terminals表进行更新
                td_tmn_leaderDao.updateLeader(new_name,origin_name);
                System.out.println("tmn修改成功");

            }

            return 200;

        } else if((userRight.getAreaID() == null || userRight.getAreaID() == "")
                && (userRight.getPipID() == null || userRight.getPipID() == "")
                && (userRight.getTmnId() == null || userRight.getTmnId() == "")){
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

            //将之前所有的权限都给清空之后，赋予新的权限，这个权限没有选择具体的分区/管线/控制柜
            //-----------------------------------------------------------------------------------------

            td_area_leaderDao.deleteRight(origin_name);

            //-----------------------------------------------------------------------------------------

            td_pip_leaderDao.deleteRight(origin_name);

            //-----------------------------------------------------------------------------------------

            td_tmn_leaderDao.deleteRight(origin_name);
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

            td_area_leaderDao.deleteRight(origin_name);
            //-----------------------------------------------------------------------------------------

            td_pip_leaderDao.deleteRight(origin_name);
            //-----------------------------------------------------------------------------------------

            td_tmn_leaderDao.deleteRight(origin_name);
            //-----------------------------------------------------------------------------------------

            //------------------------------------------------------------------------------------------
            //td_area_leader表进行更新
            String areaID = userRight.getAreaID();

            if(areaID != null && areaID != ""){
                String[] areaSplit = areaID.split(",");
                for(int i = 0; i < areaSplit.length; i++){
                    //通过AreaID获取AreaLeader
//                        td_areasDao.updateAreaLeader(new_name,areaSplit[i]);
                    //将新的权限插入td_area_leader表
                    td_area_leaderDao.addAreaLeader(new_name,areaSplit[i]);
                }
            }

            //------------------------------------------------------------------------------------------
            //td_pip_leader表进行更新
            String pipeID = userRight.getPipID();

            if(pipeID != null && pipeID != ""){
                String[] pipeSplit = pipeID.split(",");
                for(int i = 0; i < pipeSplit.length; i++){
//                        td_pipesDao.updatePipLeader(new_name,pipeSplit[i]);
                    td_pip_leaderDao.addPipLeader(new_name,pipeSplit[i]);
                }
            }

            //------------------------------------------------------------------------------------------
            //td_tmn_leader表进行更新
            String tmnID = userRight.getTmnId();

            if(tmnID != null && tmnID != ""){
                String[] tmnSplit = tmnID.split(",");
                for(int i = 0; i < tmnSplit.length; i++){
//                        terminalsDao.updateTmnLeader(new_name,tmnSplit[i]);
                    td_tmn_leaderDao.addTmnLeader(new_name,tmnSplit[i]);
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


    public UserManage getByusername(String username){
        return userManageDao.getByusername(username);
    }

    //根据员工姓名获取员工具体信息
    public List getUserMessageByName(String UserName){
        return userManageDao.getByUserManageName(UserName);
    }

    //5.3将前端的未修改的名称传往后端，与数据库进行对比
    public List getAllUser(String userName) {
        System.out.println("这是我拿到的不同于修改窗口的用户名称"+userManageDao.getAllUser(userName));
        return userManageDao.getAllUser(userName);
    }
}
