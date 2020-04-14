package com.miles.demo.controller;


import com.miles.demo.bean.*;
import com.miles.demo.repository.SpotPointRepository;
import com.miles.demo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotPointController {
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private SpotPointRepository spotPointRepository;

    static ResponseCode ADD_OK = new ResponseCode(1,"add success");
    static ResponseCode ADD_FAIL = new ResponseCode(0,"add fail");
    static ResponseCode ID_FAIL = new ResponseCode(0,"fail id not exist");
    //id=spotId
    @PostMapping(value = "/spotPoint/add")
    public ResponseCode pointAdd(@RequestParam("id") Integer id,
                                 @RequestParam("longitude") String longitude,
                                 @RequestParam("latitude") String latitude) {
        Spot spot = spotRepository.findById(id).orElse(null);
        if (spot != null) {
            SpotPoint spotPoint = new SpotPoint();
            spotPoint.setLongitude(longitude);
            spotPoint.setLatitude(latitude);
            spotPoint.setSpot(spot);
            if (spotPointRepository.save(spotPoint) != null) {
                return ADD_OK;
            }else {
                return ADD_FAIL;
            }
        }
        return ID_FAIL;
    }

    @GetMapping(value = "/spotPoint/query")
    public SpotPoint pointFindOne(@RequestParam("id") Integer id) {
        return spotPointRepository.findById(id).orElse(null);
    }


    static ResponseCode DELETE_OK = new ResponseCode(1,"delete point success");
    static ResponseCode DELETE_FAIL = new ResponseCode(0,"delete point fail");
    //id=pointId
    @PostMapping(value = "/spotPoint/delete")
    public ResponseCode pointDelete(@RequestParam("id") Integer id) {
        if (spotPointRepository.findById(id).orElse(null) != null) {
            spotPointRepository.deleteById(id);
            return DELETE_OK;
        }else {
            return DELETE_FAIL;
        }
    }

}
