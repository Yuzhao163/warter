package com.water.water.controller;


import com.water.water.Result.Result;
import com.water.water.pojo.Terminals;
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

//    添加一个控制柜
    @PostMapping(value = "api/addTmn")
    public Integer addTmn(@RequestBody Terminals requestTmn){
        Integer result = terminalsService.addTmn(requestTmn);
        return result;
    }

//    删除一个控制柜
    @PostMapping(value = "api/deleteTmnByID")
    public Integer deleteTmn(@RequestBody Terminals tmn) {
        Integer result = terminalsService.deleteTmnByID(tmn);
        return result;
    }

//    修改控制柜信息
    @PostMapping(value = "api/modifyTmn")
    public Integer modifyTmn(@RequestBody Terminals tmn) {
        Integer result = terminalsService.modifyTmn(tmn);
        return result;
    }

//  根据需求显示控制柜的信息（包含了管线名称）
    @RequestMapping(value = "api/getTmnList")
    public JSONArray getTmnList() {
        JSONArray TmnList = terminalsService.getTmnList();
        return TmnList;
    }

    @RequestMapping(value = "api/getTerminalsByUserName")
    public List getTerminalsByUserName(@RequestParam String UserName){
        return terminalsService.getTerminalsByUserName(UserName);
    }

}
