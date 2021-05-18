package com.water.water.controller;


import com.water.water.dao.TerminalsDao;
import com.water.water.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class PipeDistributeController {

    @Autowired
    private td_AreasService td_areasService;
    @Autowired
    private td_PipesService td_pipesService;
    @Autowired
    private PipeDistributeService pipeDistributeService;
    @Autowired
    private Td_tpService td_tpService;
    @Autowired
    private TerminalsService terminalsService;

    @RequestMapping(value = "api/getAreas")
    public List getAreas() {
        // 对 html 标签进行转义，防止 XSS 攻击
        List Areas = td_areasService.getAreas();
        return Areas;
    }
    @GetMapping(value = "api/getFistPip")
    @ResponseBody
    public Map<String,Map<String,List>> getPips(){
        Map<String,Map<String,List>> Pips = pipeDistributeService.getPipe();
        return Pips;
    }
    @RequestMapping(value = "api/getPipes")
    public List getPipeByArea(@RequestParam String AreaName) throws Exception{
        List Pipes = pipeDistributeService.getPipeByArea(AreaName);
        return Pipes;
    }

    @RequestMapping(value = "api/getTerminals")
    public List getTerminalsBypip(@RequestParam String PipID) throws Exception{
        String pipid = PipID;
        List Terminals = td_tpService.getAlltdByPipID(pipid);
        return Terminals;
    }

    //5.2应用到人员管理的getAreas方法
    @RequestMapping(value = "api/getArea")
    public List getArea() {
        // 对 html 标签进行转义，防止 XSS 攻击
        List Areas = td_areasService.getArea();
        return Areas;
    }
    //获取管线--4.27/15.37------5.2修改和管线分布的冲突------------------------------------------------
    @RequestMapping(value = "api/getPip")
    public List getPipe(){
        List pipes = td_pipesService.getPipe();
        return pipes;
    }

    //获取控制柜--4.27/16.01-----5.2修改和管线分布的冲突-------------------------------------------------
    @RequestMapping(value = "api/getTerminal")
    public List getTerminal(){
        List terminals = terminalsService.getTerminal();
        return terminals;
    }
}
