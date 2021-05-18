package com.water.water.controller;


import com.water.water.Result.Result;
import com.water.water.pojo.Terminals;
import com.water.water.pojo.tmn_pip_area;
import com.water.water.service.TerminalsService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  设备维护
@CrossOrigin
@RestController
public class DeviceController {

    @Autowired
    private TerminalsService terminalsService;


//    获取全部控制柜（原表）
    @RequestMapping(value = "api/getAllTmnList")
    public List getAllTmnList(){
        List getAllTmnList = terminalsService.getAllTmnList();
        return getAllTmnList;
    }

//  获取控制柜个数
    @RequestMapping(value = "api/getTmnSize")
    public Integer getTmnSize() {
        return terminalsService.getTmnSize();
    }

//  分页获取控制柜信息
    @RequestMapping(value = "api/getTmnListByPage")
    public JSONArray getTmnListByPage(Integer page, Integer size) {
        JSONArray TmnList = terminalsService.getTmnListByPage(page,size);
        return TmnList;
    }

//  根据需求显示控制柜的信息（包含了管线名称）
    @RequestMapping(value = "api/getTmnList")
    public JSONArray getTmnList() {
        JSONArray TmnList = terminalsService.getTmnList();
        return TmnList;
    }

    @RequestMapping(value = "api/getTerminalsByUserName")
    public List getTerminalsByUserName(@RequestParam String UserName){
        return terminalsService.getTNameByUserName(UserName);
    }

    @RequestMapping(value = "api/getAllAreas")
    public List getAreas () {
        System.out.println("data"+terminalsService.getAreas());
        return terminalsService.getAreas();
    }

    @RequestMapping(value = "api/getPips")
    public List getPips(@RequestParam String areaID) {
        return terminalsService.getPips(areaID);
    }

    @RequestMapping(value = "api/getTmns")
    public List getTmns(@RequestParam String pipID) {
        return terminalsService.getTmns(pipID);
    }

    @RequestMapping(value = "api/getTmnLeader")
    public List getTmnLeader() {
        return terminalsService.getTmnLeader();
    }

    @RequestMapping(value = "api/addTmn")
    public Integer addTmn(tmn_pip_area tpa,@RequestParam List<Integer> TmnLeader) {
        return terminalsService.addTmnTP(tpa,TmnLeader);
    }

    @PostMapping(value = "api/deleteTmn")
    public Integer deleteTmn(@RequestBody tmn_pip_area tpa) {
        Integer result = terminalsService.deleteTmnByID(tpa);
        return result;
    }

    //    修改控制柜信息
    @PostMapping(value = "api/modifyTmn")
    public Integer modifyTmn(tmn_pip_area tpa,@RequestParam List<Integer> TmnLeader) {
        Integer result = terminalsService.modifyTmn(tpa,TmnLeader);
        return result;
    }


}
