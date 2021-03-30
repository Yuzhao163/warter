package com.water.water.controller;


import com.water.water.Result.Result;
import com.water.water.pojo.Terminals;
import com.water.water.service.TerminalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  设备维护
@CrossOrigin
@RestController
public class DeviceController {

    @Autowired
    private TerminalsService terminalsService;

//    获取全部控制柜
    @RequestMapping(value = "api/getAllTmnList")
    public List getAllTmnList(){
        List getAllTmnList = terminalsService.getAllTmnList();
        return getAllTmnList;
    }

//    添加一个控制柜
    @PostMapping(value = "api/addTmn")
    public Result addTmn(@RequestBody Terminals requestTmn){
        try {
            Integer result = terminalsService.addTmn(requestTmn);
            if (result == 1){
                return new Result(200);
            } else {
                return new Result(400);
            }
        } catch (Exception e) {
            return new Result(400);
        }
    }

//    删除一个控制柜
    @PostMapping(value = "api/deleteTmn")
    public Result deleteTmn(@RequestBody Terminals tmn) {
        String tmnID = tmn.getTmnId();
        try {
            Integer result = terminalsService.deleteTmn(tmnID);
            if (result == 1) {
                return new Result(200);
            } else {
                return new Result(400);
            }
        } catch (Exception e) {
            return new Result(400);
        }
    }

//    修改控制柜信息
    @PostMapping(value = "api/modifyTmn")
    public Result modifyTmn(@RequestBody Terminals tmn) {
        try {
            Integer result = terminalsService.modifyTmn(tmn);
            if (result == 1) {
                return new Result(200);
            } else {
                return new Result(400);
            }
        } catch (Exception e) {
            return new Result(400);
        }
    }


}
