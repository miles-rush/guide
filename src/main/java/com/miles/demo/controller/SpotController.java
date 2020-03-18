package com.miles.demo.controller;

import com.miles.demo.bean.ResponseCode;
import com.miles.demo.bean.Sight;
import com.miles.demo.bean.Spot;
import com.miles.demo.repository.SightRepository;
import com.miles.demo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpotController {

    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private SightRepository sightRepository;

    @GetMapping(value = "/spot/list")
    public List<Spot> spotList() {
        return spotRepository.findAll();
    }

    static ResponseCode ADD_OK = new ResponseCode(1,"add success");
    static ResponseCode ADD_FAIL = new ResponseCode(0,"add fail");
    @PostMapping(value = "/spot/add")
    public ResponseCode spotAdd(@RequestParam("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("introduce") String introduce) {
        Sight sight = sightRepository.findById(id).orElse(null);
        if (sight == null) {
            return ADD_FAIL;
        }

        Spot spot = new Spot();
        spot.setName(name);
        spot.setIntroduce(introduce);
        spot.setSight(sight);

        Spot result = spotRepository.save(spot);
        if (result != null) {
            return new ResponseCode(1,"add success", result.getId());
        }
        return ADD_FAIL;
    }

    @GetMapping(value = "/spot/query")
    public Spot spotFindOne(@RequestParam("id") Integer id) {
        return spotRepository.findById(id).orElse(null);
    }

    static ResponseCode UPDATE_OK = new ResponseCode(1,"update success");
    static ResponseCode UPDATE_FAIL = new ResponseCode(0,"update fail");
    @PostMapping(value = "/spot/update")
    public ResponseCode spotUpdate(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("introduce") String introduce){
        Spot oldSpot = spotRepository.findById(id).orElse(null);
        if (oldSpot == null) {
            return UPDATE_FAIL;
        }

        Spot spot = new Spot();
        spot.setId(id);
        spot.setName(name);
        spot.setIntroduce(introduce);
        spot.setSight(oldSpot.getSight());
        if (spotRepository.save(spot) != null) {
            return UPDATE_OK;
        } else {
            return UPDATE_FAIL;
        }
    }

    static ResponseCode DELETE_OK = new ResponseCode(1,"delete spot success");
    static ResponseCode DELETE_FAIL = new ResponseCode(0,"delete spot fail");
    @GetMapping(value = "/spot/delete")
    public ResponseCode spotDelete(@RequestParam("id") Integer id) {
        if (spotRepository.findById(id).orElse(null) != null) {
            spotRepository.deleteById(id);
            return DELETE_OK;
        }else {
            return DELETE_FAIL;
        }

    }

}
