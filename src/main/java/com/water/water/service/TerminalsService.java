package com.water.water.service;

import com.water.water.Result.Result;
import com.water.water.dao.*;
import com.water.water.pojo.*;
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
    @Autowired
    private Td_tpDao td_tpDao;
    @Autowired
    private td_ApDao td_apDao;
    @Autowired
    private td_AreasDao td_areasDao;
    @Autowired
    private UserManageDao userManageDao;
    @Autowired
    private td_Tmn_LeaderDao td_tmn_leaderDao;
    @Autowired
    private Td_User_RightDao td_user_rightDao;
    @Autowired
    private td_pack_listDao td_pack_listDao;

    ErrordealRecService errordealRecService = new ErrordealRecService();


    public List getTmnID(){
        return terminalsDao.getTmnID();
    }

    public List getNameByID(List selectmessage){
        //System.out.println(selectmessage);
        PrintClassName printClassName = new PrintClassName();
        for (int i = 0;i<selectmessage.size();i++){
            try{
                Rec_Detail message = (Rec_Detail)selectmessage.get(i);
                String TmnID = message.getTmnID();
                Terminals terminals = terminalsDao.getNameByID(TmnID);
                String TmnName = terminals.getTmnName();
                message.setTmnID(TmnName);
            }catch (Exception e){
                System.err.println(e);
            }
        }
        //System.out.println(selectmessage);
        return selectmessage;
    }

//  获取全部控制柜
    public List getAllTmnList() {
        return terminalsDao.getAllTmnList();

    }


    //  根据id查询 管线名称和控制柜名称 将合并信息一起返回给前端
    // 不用了
//    public JSONArray getTmnList() {
//        List tmnList = terminalsDao.getAllTmnList();
//        JSONArray jsonArray = JSONArray.fromObject(tmnList);
//        for (int i=0; i<jsonArray.size(); i++) {
//            // 循环获得每一个控制柜
//            Terminals tmn = (Terminals) tmnList.get(i);
//            // 查询控制柜相关信息
//            String TmnID = tmn.getTmnId();
//            String U1TmnID = tmn.getU1TmnID();
//            String U2TmnID = tmn.getU2TmnID();
//            String D1TmnID = tmn.getD1TmnID();
//            String D2TmnID = tmn.getD2TmnID();
//            String U1TmnName = terminalsDao.getTmnNameByTmnID(U1TmnID);
//            String U2TmnName = terminalsDao.getTmnNameByTmnID(U2TmnID);
//            String D1TmnName = terminalsDao.getTmnNameByTmnID(D1TmnID);
//            String D2TmnName = terminalsDao.getTmnNameByTmnID(D2TmnID);
//            String PipID = "";
//            String PipName = "";
//            String AreaID = "";
//            String AreaName = "";
////          还需要显示控制柜所在的分区和管线
//            // 获得管线控制柜的管线id和名称
//            PipID = td_tpDao.getPIDByTID(TmnID);
//            PipName = td_pipesDao.getPipNameByPipID(PipID);
//            List AIDList = td_apDao.getAIDByPID(PipID);
//            if (AIDList.size() != 0) {
//                td_Ap ap = (td_Ap)AIDList.get(0);
//                AreaID = ap.getAreaID();
//            }
//            AreaName = td_areasDao.getNameByID(AreaID);
//            if (AreaName == "") {
//                AreaName = "null";
//            }
//            JSONObject item = jsonArray.getJSONObject(i);
//            item.put("pipID",PipID);
//            item.put("pipName",PipName);
//            item.put("AreaID",AreaID);
//            item.put("AreaName",AreaName);
//            item.put("u1TmnName",U1TmnName);
//            item.put("u2TmnName",U2TmnName);
//            item.put("d1TmnName",D1TmnName);
//            item.put("d2TmnName",D2TmnName);
//        }
//        return jsonArray;
//    }

    //获取控制柜--4.27/16.01--------5.2解决和管线分布的冲突----------------------------------------------
    // 没用了
    public List getTerminal() {
        List terminals = terminalsDao.getTerminal();
        return terminals;
    }

    public List getTNameByUserName(String TmnLeader) throws Exception{
        List terminal = new ArrayList();
        UserManage userManage = userManageDao.getUserID(TmnLeader);
        List tmnID = td_tmn_leaderDao.getRightByID(userManage.getUserID());
        for(int i = 0; i < tmnID.size(); i++) {
            td_Tmn_Leader td_tmn_leader = (td_Tmn_Leader) tmnID.get(i);
            String tmnid = td_tmn_leader.getTmnID();
            String tmnName = terminalsDao.getTmnNameByTmnID(tmnid);
            Terminals tmn = terminalsDao.getTmnByTmnName(tmnName);
            terminal.add(tmn);
        }
        return terminal;
    }
    //  获取控制柜条数
    public Integer getTmnSize() {
        return terminalsDao.getTmnSize();
    }

    //  分页获取全部控制柜
    public JSONArray getTmnListByPage(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List tmnList = terminalsDao.getAllTmnListByPage(page,size);
        JSONArray jsonArray = JSONArray.fromObject(tmnList);
        List<JSONObject> userList = new ArrayList<JSONObject>();
        for (int i=0; i<jsonArray.size(); i++) {
            // 循环获得每一个控制柜
            Terminals tmn = (Terminals) tmnList.get(i);
            // 查询控制柜相关信息
            String TmnID = tmn.getTmnId();
            String U1TmnID = tmn.getU1TmnID();
            String U2TmnID = tmn.getU2TmnID();
            String D1TmnID = tmn.getD1TmnID();
            String D2TmnID = tmn.getD2TmnID();
            String U1TmnName = terminalsDao.getTmnNameByTmnID(U1TmnID);
            String U2TmnName = terminalsDao.getTmnNameByTmnID(U2TmnID);
            String D1TmnName = terminalsDao.getTmnNameByTmnID(D1TmnID);
            String D2TmnName = terminalsDao.getTmnNameByTmnID(D2TmnID);
            String PipID = "";
            String PipName = "";
            String AreaID = "";
            String AreaName = "";

            PipID = td_tpDao.getPIDByTID(TmnID);
            PipName = td_pipesDao.getPipNameByPipID(PipID);
            List AIDList = td_apDao.getAIDByPID(PipID);
            if (AIDList.size() != 0) {
                td_Ap ap = (td_Ap)AIDList.get(0);
                AreaID = ap.getAreaID();
            }
            AreaName = td_areasDao.getNameByID(AreaID);
            if (AreaName == "") {
                AreaName = "null";
            }
            JSONObject item = jsonArray.getJSONObject(i);
            item.put("pipID",PipID);
            item.put("pipName",PipName);
            item.put("AreaID",AreaID);
            item.put("AreaName",AreaName);
            item.put("u1TmnName",U1TmnName);
            item.put("u2TmnName",U2TmnName);
            item.put("d1TmnName",D1TmnName);
            item.put("d2TmnName",D2TmnName);
            // 获得控制柜管理人员的名字并加入到json对象中
            JSONObject tmnleader = new JSONObject();
            List UIDList = td_tmn_leaderDao.getUIDByTID(TmnID);
            userList.clear();

            for (int j = 0; j < UIDList.size(); j++) {
                td_Tmn_Leader UID = (td_Tmn_Leader)UIDList.get(j);
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
            item.put("TmnLeader",userList);
        }
        return jsonArray;
    }

    public List getAreas() {
        return td_areasDao.getAreas();
    }

    public List getPips(String areaID) {
        // 根据分区id找到分区管线表中的管线id
        // 然后根据管线id找到管线名称并将名称list返回给前端
        List pipIdList = td_apDao.getPipIDByAreaID(areaID);
        List pipList = new ArrayList();
        for (int i=0; i<pipIdList.size(); i++) {
            // 分区管线表获取pipid
            String pipID = ((td_Ap)pipIdList.get(i)).getPipID();
//          根据pipid找pipname和pipid
            List pip = td_pipesDao.getPIDNameByPID(pipID);
            for (int j = 0; j < pip.size() ; j++) {
                // 获取pipid对应的name和id
                td_PIPs td_pip = (td_PIPs)pip.get(j);
                pipList.add(td_pip);
            }
        }
        return pipList;
    }


    public List getTmns(String pipID) {
//        根据管线id 返回控制柜 id和名称
//        管线控制柜表找到控制柜id  -》  控制柜表根据id找到名称
//      获取了控制柜以及线内id（按顺序排好了）
        List tmnIDList = td_tpDao.getAlltdByTmnID(pipID);
        List tmnList = new ArrayList();
        for (int i = 0; i < tmnIDList.size(); i++) {
            String tmnID = ((td_Tp)tmnIDList.get(i)).getTmnID();
            List tmn = terminalsDao.getTINByTID(tmnID);
            for (int j = 0; j < tmn.size() ; j++) {
                Terminals terminal = (Terminals) tmn.get(j);
                tmnList.add(terminal);
            }
        }
        return tmnList;
    }


//  选择具有管理控制柜权限的人员
    public List getTmnLeader() {
        // 获取具有权限的用户id
        //List userIDList = td_user_rightDao.getUIDByRight();
        //5.17替换td_user_right表中Right_pp
        List userIDList = userManageDao.getUIDByRight();
        List userNIDList = new ArrayList();
        // 循环list通过用户id找到用户名称  再将id和名称放在一个新的实体类中 返回给前端
        for (int i = 0; i < userIDList.size(); i++) {
            UserManage us = (UserManage) userIDList.get(i);
            Integer userID = us.getUserID();
            String userName = userManageDao.getUNameByID(userID);
            UserManage userManage = new UserManage();
            userManage.setUserID(userID);
            userManage.setUserName(userName);
            userNIDList.add(i,userManage);
        }
        return userNIDList;
    }


    //    增加管线控制柜表
    public Integer addTmnTP(tmn_pip_area tpa,List<Integer> TmnLeader) {
        System.out.println("增加的管线信息"+tpa);
        String TmnName = tpa.getTmnName();
        String U1TmnID = tpa.getU1TmnID();
        String ConPont1 = tpa.getConPont1();
        String ConPont2 = tpa.getConPont2();
        String ConPont3 = tpa.getConPont3();
        String TmnDesc = tpa.getTmnDesc();
//        把管线控制柜表要用的数据取出来
        String pipID = tpa.getPipID();
        String tmnID = tpa.getTmnID();

        if (existname(TmnName)==1) {
            return 201;
        }

        if (tmnID == null) {
            Integer maxid = terminalsDao.getMaxID()+1;
            tmnID = maxid.toString();
        }
//      将控制柜信息插入
        Terminals tmn = new Terminals();
        tmn.setTmnId(tmnID);
        tmn.setTmnName(TmnName);
        if (U1TmnID!=null) {
            tmn.setU1TmnID(U1TmnID);
        }
        if (tpa.getD1TmnID()!=null||tpa.getD1TmnID().equals("")) {
            tmn.setD1TmnID(tpa.getD1TmnID());
        } else {
            tmn.setD1TmnID(null);
        }
        tmn.setConPont1(ConPont2);
        tmn.setConPont1(ConPont3);
        tmn.setConPont1(ConPont1);
        tmn.setTmnDesc(TmnDesc);
//      (要对控制柜管线表 人员权限表进行更新) 成功之后对控制柜进行添加操作
        // 控制柜权限表
        //  判断这个管线中有没有控制柜 如果没有那么ptid直接设置为1
        //      如果有控制柜但是没有选择位置则直接默认插在第一个
        Integer ptid;
        // 判断控制柜插入位置
        // 插入位置的前一个控制柜为空  可能是没有控制柜时插入 可能是有控制柜插入第一个
        if(U1TmnID==null || U1TmnID.equals("")) {
            ptid = 1;
            if(tpa.getD1TmnID()==null || tpa.getD1TmnID().equals("")) {
                //  后一个控制柜也为空 证明该管线没有控制柜 直接插入到第一个位置即可
                //  注：这里要对管线内是否有控制柜进行判断 如果有则插入第一个 如果没有直接插入即可
                if (td_tpDao.getTmnByPID(pipID).size() == 0) {
                    // 证明为空
                    //  对控制柜进行插入
                    terminalsDao.addTmn(tmn);
                    //  添加一条数据到控制柜管线表中
                    td_tpDao.insertPtid(tmnID,pipID,ptid);
                    // 对管理人员进行添加
                    addTmnLeader(TmnLeader,tmnID);
                } else {
                    // 非空 插入第一个(也是默认情况)
                    // 找到第一个控制柜 将其id拿到 设置其上一控制柜为当前控制柜
                    String firstTmnID = td_tpDao.getFirstTmn(pipID);
                    // 将第一个控制柜的上一个控制柜设置为当前控制柜（这里要对数据库中的数据进行操作）
                    terminalsDao.setU1TmnID(firstTmnID,tmnID);
                    // 设置当前控制柜的下一控制柜为 查到的控制柜
                    tmn.setD1TmnID(firstTmnID);
                    //  对控制柜进行插入
                    terminalsDao.addTmn(tmn);
                    //  把后面的控制柜线内编号+1
                    td_tpDao.ptidAdd(pipID,ptid);
                    //  添加一条数据到控制柜管线表中
                    td_tpDao.insertPtid(tmnID,pipID,ptid);
                    // 对管理人员进行添加
                    addTmnLeader(TmnLeader,tmnID);
                }
            }
        } else if(tpa.getD1TmnID()==null || tpa.getD1TmnID().equals("")){
            // 前一个控制柜不是空 后一个控制柜是空 证明插入了最后一个
            // 找到上一控制柜的下一控制柜 将其修改为当前控制柜（这里要对数据库中的数据进行操作）
            terminalsDao.setD1TmnID(U1TmnID,tmnID);
            // 获取上一个控制柜的线内编号+1
            ptid = td_tpDao.getPtid(U1TmnID,pipID)+1;
            //  对控制柜进行插入
            terminalsDao.addTmn(tmn);
            //  添加一条数据到控制柜管线表中
            td_tpDao.insertPtid(tmnID,pipID,ptid);
            // 对管理人员进行添加
            addTmnLeader(TmnLeader,tmnID);
        } else {
            // 前后都不为空 证明插入了控制柜之间
            // 找到上一控制柜的下一控制柜 将其修改为当前控制柜（这里要对数据库中的数据进行操作）
            terminalsDao.setD1TmnID(U1TmnID,tmnID);
            // 找到下一控制柜的上一控制柜 将其修改为当前控制柜
            terminalsDao.setU1TmnID(tpa.getD1TmnID(),tmnID);
            // 获取上一个控制柜的线内编号+1
            ptid = td_tpDao.getPtid(U1TmnID,pipID)+1;
            //  对控制柜进行插入
            terminalsDao.addTmn(tmn);
            //  把后面的控制柜线内编号+1
            td_tpDao.ptidAdd(pipID,ptid);
            //  添加一条数据到控制柜管线表中
            td_tpDao.insertPtid(tmnID,pipID,ptid);
            // 对管理人员进行添加
            addTmnLeader(TmnLeader,tmnID);
        }
        return 1;
    }
    // 添加管理控制柜用户
    public Integer addTmnLeader(List<Integer> TmnLeader,String tmnID) {
        if (TmnLeader.get(0) != -1) {
            for (int i = 0; i < TmnLeader.size(); i++) {
                td_tmn_leaderDao.addTmnLeader(TmnLeader.get(i),tmnID);
            }
            return 1;
        }
        return 0;
    }

    //  根据控制柜id删除一个控制柜（*删除控制柜  *上下控制柜信息修改  *管线控制柜表删除并修改）
    public Integer deleteTmnByID(tmn_pip_area tpa) {
        System.out.println(tpa);
        String tmnID = tpa.getTmnID();
        // 上一控制柜id
        String beTmnID = tpa.getU1TmnID();
        // 下一控制柜id
        String neTmnID = tpa.getD1TmnID();
        System.out.println("tpa"+tpa+"tmnID"+tmnID);
        System.out.println(beTmnID+"这是输出结果"+terminalsDao.getTmnByID(beTmnID));

        // 是否存在上一控制柜
        if (!beTmnID.equals("")) {
            // 是否存在下一控制柜 (不存在就不需要修改关联控制柜 直接删除控制柜信息)
            if (!neTmnID.equals("")) {
                // 即存在上一控制柜又存在下一控制柜(中间控制柜)
                // （*修改上一控制柜的下一控制柜 以及 下一控制柜的上一控制柜）
                // 对上一控制柜和下一控制柜的信息进行修改
                // 设置上一控制柜的下一控制柜id
                terminalsDao.setD1TmnID(beTmnID,neTmnID);
                // 设置下一控制柜的上一控制柜id
                terminalsDao.setU1TmnID(neTmnID,beTmnID);
                // 管线控制柜表删除该控制柜并修改其他控制柜线内位置（不考虑交叉）
                td_Tp PipIDList = (td_Tp)(td_tpDao.getTpByTID(tmnID).get(0));
                String PipID = PipIDList.getPipID();
                Integer PTid = PipIDList.getPTid();
                // 将管线内其他控制柜线内修改好
                td_tpDao.ptidMinus(PipID,PTid);
                // 删除管线控制柜中该控制柜信息
                td_tpDao.deleteTP(tmnID);
            } else {
                // 存在上一控制柜 不存在下一控制柜（最后一个位置）
                // 把删除控制柜的后一控制柜置空
                terminalsDao.setD1TmnID(beTmnID,null);
                // 最后一个控制柜 管线控制柜表直接删除数据即可
                td_tpDao.deleteTP(tmnID);
            }
        } else if (!neTmnID.equals("")) {
            // 不存在上一控制柜存在下一控制柜（第一个位置）
            // 把下一控制柜的前一控制柜置空
            terminalsDao.setU1TmnID(neTmnID,null);
            // 修改管线控制柜
            td_Tp PipIDList = (td_Tp)(td_tpDao.getTpByTID(tmnID).get(0));
//                System.out.println(PipIDList);
            String PipID = PipIDList.getPipID();
            Integer PTid = PipIDList.getPTid();
            // 将管线内其他控制柜线内修改好
            td_tpDao.ptidMinus(PipID,PTid);
            // 删除管线控制柜中该控制柜信息
            td_tpDao.deleteTP(tmnID);
        } else {
            // 没有上一控制柜也没有下一控制柜 不需要修改其他控制柜信息 删除管线控制柜中控制柜即可
            td_tpDao.deleteTP(tmnID);
        }

        // 这里还要删除异常展示表的异常（两个表），控制柜没有了异常自然也就没有了（等会做）
        // packlist里面还有tmnid
//        td_pack_list.deleteByTmnID(tmnID);
        td_pack_listDao.deleteByTmnID(tmnID);

        // 删除控制柜管理员表的管理员id
        td_tmn_leaderDao.deleteTmnLeader(tmnID);
        // 删除控制柜
        if (terminalsDao.deleteTmnByID(tmnID)==1) {
            // 删除控制柜的同时 删除packlist里的id
            // td_packlist.deleteByTmnID(tmnID);
            return 1;
        } else {
            return 0;
        }
    }

    //  编辑控制柜
    public Integer existname(String TmnName) {
        if (terminalsDao.existname(TmnName)==null) {
            return 0;
        } else return 1;
    }


    public Integer modifyTmn(tmn_pip_area tpa,List<Integer> TmnLeader) {
        // 首先判断分区管线上下控制柜是否发生过修改
        // 获取之前的控制柜
        String TmnID = tpa.getTmnID();
        Terminals betmn = (Terminals) terminalsDao.getTmnByID(TmnID).get(0);
        String bePipID = td_tpDao.getPIDByTID(TmnID);
        // 判断
        // 修改控制柜名称
        if (!tpa.getTmnName().equals(betmn.getTmnName())) {
            //这个控制柜的名称没有修改
            if(existname(tpa.getTmnName())==1) {
                //这个控制柜名字已经存在
                return 201;
            }
        }
        // 修改了控制柜的位置
        if (!tpa.getPipID().equals(bePipID)) {
            // 管线修改 相当于先删除后插入
            td_tpDao.ptidMinus(bePipID,td_tpDao.getPtid(TmnID,bePipID));
            td_tpDao.deleteTP(TmnID);
            // 修改原来控制柜
            String beTmnID = betmn.getU1TmnID();
            String neTmnID = betmn.getD1TmnID();
            // 设置上一控制柜的下一控制柜id
            terminalsDao.setD1TmnID(beTmnID,neTmnID);
            // 设置下一控制柜的上一控制柜id
            terminalsDao.setU1TmnID(neTmnID,beTmnID);

            Integer ptid;
            if (tpa.getU1TmnID()==null || tpa.getU1TmnID().equals("")) {
                // 开头
                ptid = 1;
                if (tpa.getD1TmnID()==null || tpa.getD1TmnID().equals("")) {
                    // 判断修改后管线内是否存在控制柜
                    String PipID = tpa.getPipID();
                    if (td_tpDao.getPipSize(PipID)>0) {
                        String firstTmnID = td_tpDao.getFirstTmn(PipID);
                        // 管线内有控制柜 改变第一个控制柜的上一控制柜
                        terminalsDao.setU1TmnID(firstTmnID,TmnID);
                        terminalsDao.setD1TmnID(TmnID,firstTmnID);
                        terminalsDao.setU1TmnIDNull(TmnID);
                        td_tpDao.ptidAdd(PipID,ptid);
                        td_tpDao.insertPtid(TmnID,PipID,ptid);
                    } else {
                        // 管线内没有控制柜 都相当于下列语句
                        td_tpDao.ptidAdd(PipID,ptid);
                        td_tpDao.insertPtid(TmnID,PipID,ptid);
                        terminalsDao.setU1TmnIDNull(TmnID);
                        terminalsDao.setD1TmnIDNull(TmnID);
                    }
                }
            }else if (tpa.getD1TmnID()==null || tpa.getD1TmnID().equals("")) {
                // 最后
                ptid = td_tpDao.getPtid(tpa.getU1TmnID(),tpa.getPipID())+1;
                td_tpDao.insertPtid(TmnID,tpa.getPipID(),ptid);
                terminalsDao.setD1TmnID(tpa.getU1TmnID(),TmnID);
                terminalsDao.setU1TmnID(TmnID,tpa.getU1TmnID());
                terminalsDao.setD1TmnIDNull(TmnID);
            } else {
                // 中间
                ptid = td_tpDao.getPtid(tpa.getU1TmnID(),tpa.getPipID())+1;
                td_tpDao.ptidAdd(tpa.getPipID(),ptid);
                td_tpDao.insertPtid(TmnID,tpa.getPipID(),ptid);
                terminalsDao.setU1TmnID(TmnID,tpa.getU1TmnID());
                terminalsDao.setD1TmnID(TmnID,tpa.getD1TmnID());
                // 上一控制柜
                terminalsDao.setD1TmnID(tpa.getU1TmnID(),TmnID);
                // 下一控制柜
                terminalsDao.setU1TmnID(tpa.getD1TmnID(),TmnID);
            }
        } else if (!tpa.getU1TmnID().equals(betmn.getU1TmnID())){
            // 管线未修改 控制柜位置修改 相当于修改了tp 以及上下控制柜的id
            deleteTP(tpa);
            Integer ptid=0;
            String firstTmnID = "";
            if (tpa.getU1TmnID()==null || tpa.getU1TmnID().equals("")) {
                if(tpa.getD1TmnID()==null || tpa.getD1TmnID().equals("")) {
                    System.out.println("要放在第一个位置上");
                    // 修改到第一位
                    ptid = 1;
                    firstTmnID = td_tpDao.getFirstTmn(tpa.getPipID());
                    System.out.println("第一个控制柜id"+firstTmnID);
                    td_tpDao.ptidAdd(tpa.getPipID(),ptid);
                    td_tpDao.insertPtid(TmnID,tpa.getPipID(),ptid);
                }
            } else {
                // 改到中间或最后
                ptid = td_tpDao.getPtid(tpa.getU1TmnID(),tpa.getPipID())+1;
                td_tpDao.ptidAdd(tpa.getPipID(),ptid);
                td_tpDao.insertPtid(TmnID,tpa.getPipID(),ptid);

            }
            // 更新 控制柜 以及 上下控制柜信息
            // 修改原来控制柜
            String beTmnID = betmn.getU1TmnID();
            String neTmnID = betmn.getD1TmnID();
            // 设置上一控制柜的下一控制柜id
            terminalsDao.setD1TmnID(beTmnID,neTmnID);
            // 设置下一控制柜的上一控制柜id
            terminalsDao.setU1TmnID(neTmnID,beTmnID);
            // 本控制柜
            terminalsDao.setU1TmnID(TmnID,tpa.getU1TmnID());
            if (ptid==1){
                terminalsDao.setD1TmnID(TmnID,firstTmnID);
            } else {
                terminalsDao.setD1TmnID(TmnID,tpa.getD1TmnID());
            }
            // 上一控制柜
            terminalsDao.setD1TmnID(tpa.getU1TmnID(),TmnID);
            // 下一控制柜
            if (ptid==1){
                terminalsDao.setU1TmnID(firstTmnID,TmnID);
            } else {
                terminalsDao.setU1TmnID(tpa.getD1TmnID(),TmnID);
            }
        }
        // 更新terminals表的信息
        terminalsDao.updateTmnByid(TmnID,tpa.getTmnName(),tpa.getConPont1(),tpa.getConPont2(),
                tpa.getConPont3(),tpa.getTmnDesc());
        // 分区管线控制柜位置均未修改 什么都不做
        // 管理人员的修改 直接先删除后添加
        td_tmn_leaderDao.deleteTmnLeader(TmnID);
        if(TmnLeader.size()!=0) {
            addTmnLeader(TmnLeader,TmnID);
        }
        return 200;
    }

    // [仅限于管线未改动的情况]管线控制柜表 (删除一个控制柜)
    public void deleteTP(tmn_pip_area tpa) {
        String TmnID = tpa.getTmnID();
        String PipID = tpa.getPipID();
        Integer ptid = td_tpDao.getPtid(TmnID,PipID);
        td_tpDao.deleteTP(TmnID);
        td_tpDao.ptidMinus(PipID,ptid);
    }
}
