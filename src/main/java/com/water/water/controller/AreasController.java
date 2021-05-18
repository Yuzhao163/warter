package com.water.water.controller;

import com.water.water.service.td_AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AreasController {

    @Autowired
    td_AreasService td_areasService;


    @RequestMapping(value = "/api/getAreaSize")
    public Integer getAreaSize() {
        return td_areasService.getAreaSize();
    }

    @RequestMapping(value = "/api/getAreaList")
    public List getAllAreas(Integer page, Integer size) {
        return td_areasService.getAllAreas(page, size);
    }

    @RequestMapping(value = "/api/getAreaLeader")
    public List getAreaLeader() {
        return td_areasService.getAreaLeader();
    }


    @RequestMapping(value = "/api/addArea")
    public Integer addArea(String areaName,@RequestParam List<Integer> areaLeader) {
        return td_areasService.addArea(areaName,areaLeader);
    }

    @RequestMapping(value = "/api/modifyArea")
    public Integer modifyArea(String areaID, String areaName, @RequestParam List<Integer> areaLeader) {
//        System.out.println(areaLeader);
        return td_areasService.modifyArea(areaID,areaName,areaLeader);
    }

    @RequestMapping(value = "/api/deleteArea")
    public Integer deleteArea(String areaID) {
        return td_areasService.deleteArea(areaID);
    }



}
