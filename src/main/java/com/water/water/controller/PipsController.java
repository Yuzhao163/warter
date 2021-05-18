package com.water.water.controller;


import com.water.water.service.td_PipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class PipsController {

    @Autowired
    td_PipesService td_pipesService;

    @RequestMapping(value = "/api/getPipSize")
    public Integer getPipSize() {
        return td_pipesService.getPipSize();
    }

    @RequestMapping(value = "/api/getPipList")
    public List getAllPips(Integer page, Integer size) {
        return td_pipesService.getAllPips(page, size);
    }

    @RequestMapping(value = "/api/getPipLeader")
    public List getPipLeader() {
        return td_pipesService.getPipLeader();
    }

    //5.9增加管线
    @RequestMapping(value = "/api/addPip")
    public Integer addPips(String PipName,String AreaID,@RequestParam List<Integer> PipLeader){
        System.out.println("PipLeader"+PipLeader);
        System.out.println("0"+PipLeader.get(0));
        return td_pipesService.addPips(PipName, AreaID, PipLeader);
    }

    //5.9删除管线
    @RequestMapping(value ="/api/deletePip")
    public Integer deletePip(@RequestParam String PipID){
        return td_pipesService.deletePip(PipID);
    }

    //5.9更新管线
    @RequestMapping(value = "/api/updatePip")
    public Integer updatePip(@RequestParam String PipID, @RequestParam String PipName,
                             @RequestParam List<Integer> PipLeader, @RequestParam String AreaID){
        System.out.println("PipLeader"+PipLeader);
        System.out.println("PipID"+PipID);
        System.out.println("AreaID"+AreaID);
        return td_pipesService.updatePip(PipID, PipName, PipLeader, AreaID);

    }




}
