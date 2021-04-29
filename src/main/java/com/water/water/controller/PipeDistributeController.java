package com.water.water.controller;


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
//    @RequestMapping(value = "api/getPipes")
//    public List getPipeByArea(@RequestParam String AreaName) throws Exception{
//        String AreasName = AreaName;
//        List Pipes = pipeDistributeService.getPipeByArea(AreaName);
//        return Pipes;
//    }
//    @RequestMapping(value = "api/getTerminals")
//    public List getTerminalsBypip(@RequestParam String PipID) throws Exception{
//        String pipid = PipID;
//        List Terminals = td_tpService.getAlltdByPipID(pipid);
//        System.out.println(Terminals);
////        List Terminals = td_tpService.getTmnLeaderNull();
//        return Terminals;


    //获取管线--4.27/15.37------------------------------------------------------
    @RequestMapping(value = "api/getPipes")
    public List getPipes(){
        List pipes = td_pipesService.getPipes();
        return pipes;
    }

    //获取控制柜--4.27/16.01------------------------------------------------------
    @RequestMapping(value = "api/getTerminals")
    public List getTerminals(){
        List terminals = terminalsService.getTerminals();
        return terminals;
    }
}
