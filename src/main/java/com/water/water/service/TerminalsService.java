package com.water.water.service;

import com.water.water.Result.Result;
import com.water.water.dao.*;
import com.water.water.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.ibatis.jdbc.Null;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.water.water.util.PrintClassName;

@Service
public class TerminalsService {
    @Autowired
    private TerminalsDao terminalsDao;
    @Autowired
    private td_PipesDao td_pipesDao;
    @Autowired
    private td_AreasDao td_areasDao;
    @Autowired
    private td_ApDao td_apDao;
    @Autowired
    private Td_tpDao td_tpDao;
    @Autowired
    private UserManageDao userManageDao;
    @Autowired
    private td_UserRightDao td_userRightDao;



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



//  根据控制柜id删除一个控制柜（*删除控制柜  *上下控制柜信息修改  *管线控制柜表删除并修改  *用户权限表修改[先不做]）
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
                Terminals beTmn = (Terminals)terminalsDao.getTmnByID(beTmnID).get(0);
                beTmn.setD1TmnID(neTmnID);
                Terminals neTmn = (Terminals)terminalsDao.getTmnByID(neTmnID).get(0);
                neTmn.setU1TmnID(beTmnID);
                // 管线控制柜表删除该控制柜并修改其他控制柜线内位置（不考虑交叉）
                td_Tp PipIDList = (td_Tp)(td_tpDao.getTpByTID(tmnID).get(0));
                System.out.println("td_tplist"+PipIDList);
                String PipID = PipIDList.getPipID();
                Integer PTid = PipIDList.getPTid();
                System.out.println("PIPID"+PipID+"ptid"+PTid);
                // 将管线内其他控制柜线内修改好
                td_tpDao.ptidMinus(PipID,PTid);
                // 删除管线控制柜中该控制柜信息
                td_tpDao.deleteTP(tmnID);
            } else {
                // 存在上一控制柜 不存在下一控制柜（最后一个位置）
                // 把迁移控制柜的后一控制柜置空
                Terminals beTmn = (Terminals)terminalsDao.getTmnByID(beTmnID).get(0);
                beTmn.setD1TmnID(null);
                // 最后一个控制柜 管线控制柜表直接删除数据即可
                td_tpDao.deleteTP(tmnID);
            }
        } else if (!neTmnID.equals("")) {
                // 不存在上一控制柜存在下一控制柜（第一个位置）
                // 把下一控制柜的前一控制柜置空
                Terminals neTmn = (Terminals)terminalsDao.getTmnByID(neTmnID).get(0);
                neTmn.setU1TmnID(null);
                System.out.println(neTmn);
                // 修改管线控制柜
                td_Tp PipIDList = (td_Tp)(td_tpDao.getTpByTID(tmnID).get(0));
                System.out.println(PipIDList);
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

        // 删除控制柜
        if (terminalsDao.deleteTmnByID(tmnID)==1) {
            return 1;
        } else {
            return 0;
        }
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
//          还需要显示控制柜所在的分区和管线
            // 获得管线控制柜的管线id和名称
//            td_Tp tp = (td_Tp)td_tpDao.getAlltdByTmnID(TmnID).get(0);
//            System.out.println(td_tpDao.getPIDByTID(TmnID));
            List PIDList = td_tpDao.getPIDByTID(TmnID);
//          管线不为空时候才能输出
            if (PIDList.size() != 0) {
//                System.out.println(PIDList.get(0));
                td_Tp tp = (td_Tp)PIDList.get(0);
                PipID = tp.getPipID();
//                System.out.println(PipID);
            }
//            System.out.println();
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
//            System.out.println("AreaName"+AreaName);


            JSONObject item = jsonArray.getJSONObject(i);
            item.put("pipID",PipID);
            item.put("pipName",PipName);
            item.put("AreaID",AreaID);
            item.put("AreaName",AreaName);
            item.put("u1TmnName",U1TmnName);
            item.put("u2TmnName",U2TmnName);
            item.put("d1TmnName",D1TmnName);
            item.put("d2TmnName",D2TmnName);
        }
        return jsonArray;
    }

    public List getTNameByUserName(String UserName){
        return terminalsDao.getTNameByUserName(UserName);
    }

    public List getAreas() {
        System.out.println(td_areasDao.getAreas());
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

    public List getTmnLeader() {
        return userManageDao.getUIDName();
    }

//    增加管线控制柜表
    public Integer addTmnTP(tmn_pip_area tpa) {
        String TmnName = tpa.getTmnName();
        String U1TmnID = tpa.getU1TmnID();
        String U2TmnID = tpa.getU2TmnID();
        String D1TmnID = tpa.getD1TmnID();
        String D2TmnID = tpa.getD2TmnID();
        String ConPont1 = tpa.getConPont1();
        String ConPont2 = tpa.getConPont2();
        String ConPont3 = tpa.getConPont3();
        String TmnDesc = tpa.getTmnDesc();
//        把管线控制柜表要用的数据取出来
        String pipID = tpa.getPipID();
        String tmnID = tpa.getTmnID();
        String beTmnID = tpa.getU1TmnID();
        String areaID = tpa.getAreaID();
//        管理控制柜的用户id
        String TmnLeadID = tpa.getTmnLeadID();
        // 找到该用户
        String TmnLeader = userManageDao.getUserNameByID(TmnLeadID);

//      将控制柜信息插入
        Terminals tmn = new Terminals();
        tmn.setTmnId(tmnID);
        tmn.setTmnName(TmnName);
        tmn.setU1TmnID(U1TmnID);
        tmn.setU2TmnID(U2TmnID);
        tmn.setD1TmnID(D1TmnID);
        tmn.setD2TmnID(D2TmnID);
        tmn.setConPont1(ConPont2);
        tmn.setConPont1(ConPont3);
        tmn.setConPont1(ConPont1);
        tmn.setTmnDesc(TmnDesc);
        tmn.setTmnLeader(TmnLeader);

//      (要对控制柜管线表 人员权限表进行更新) 成功之后对控制柜进行添加操作

            // 控制柜权限表
            //  判断这个管线中有没有控制柜 如果没有那么ptid直接设置为1
            //      如果有控制柜但是没有选择位置则直接默认插在第一个
            Integer ptid;

            if(U1TmnID==null || U1TmnID.equals("")) {
                ptid = 1;
            } else {
                //  根据前一个控制柜id和管线id找前一个控制柜id对应的线内编号
                ptid = td_tpDao.getPtid(beTmnID, pipID);
                ptid++;
                System.out.println("pti=======" + ptid);
                //  修改上一控制柜的下一控制柜以及下一控制柜的上一控制柜信息
                Terminals beforeTmn = (Terminals) terminalsDao.getTmnByID(U1TmnID).get(0);
                System.out.println("前一个控制柜的信息" + beforeTmn);
                // 先从管线控制柜表中查询到上一控制柜的下一控制柜
                String nextTmnID = beforeTmn.getD1TmnID();
                System.out.println("前一个控制柜的信息" + nextTmnID);
                // 将上一控制柜的下一控制柜改为当前控制柜
                beforeTmn.setD1TmnID(tmnID);
                System.out.println("塞进去之后的" + beforeTmn);
                // 如果上一控制柜的id是空的话证明现在插入的是最后一个位置
                // 如果不是空的话 证明插在中间位置 则需要修改下一控制柜的上一控制id变为当前控制柜id
                if (!(nextTmnID == null || nextTmnID.equals(""))) {
                    tmn.setD1TmnID(nextTmnID);
                    Terminals nextTmn = (Terminals) terminalsDao.getTmnByID(nextTmnID).get(0);
                    nextTmn.setU1TmnID(tmnID);
                } else {
                    // 插在最后
                }

            }
            //  对控制柜进行插入
            terminalsDao.addTmn(tmn);

            //  把后面的控制柜线内编号+1
            td_tpDao.ptidAdd(pipID,ptid);
            //  添加一条数据到控制柜管线表中
            td_tpDao.insertPtid(tmnID,pipID,ptid);

            // 人员权限表
            // 根据id把人员权限查询出来 然后再插入分区管线控制柜的id
//            Integer Right_PP = td_userRightDao.getRightByUID(TmnLeadID);
            // 把userid rightpp tmnid pipid areaid 插入到人员权限表中
//            td_userRightDao.addUserRight(TmnLeadID,Right_PP,tmnID,pipID,areaID);

        return 1;
    }




    public void test() {
        System.out.println(td_userRightDao.getall());

    }


}
