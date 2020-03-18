package com.miles.demo.controller;

import com.miles.demo.bean.Point;
import com.miles.demo.bean.ResponseCode;
import com.miles.demo.bean.Sight;
import com.miles.demo.repository.PointRepository;
import com.miles.demo.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private SightRepository sightRepository;

    static ResponseCode ADD_OK = new ResponseCode(1,"add success");
    static ResponseCode ADD_FAIL = new ResponseCode(0,"add fail");
    static ResponseCode ID_FAIL = new ResponseCode(0,"fail id not exist");
    //id=sightId
    @PostMapping(value = "/point/add")
    public ResponseCode pointAdd(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("coordinate") String coordinate) {
        Sight sight = sightRepository.findById(id).orElse(null);
        if (sight != null) {
            Point point = new Point();
            point.setName(name);
            point.setCoordinate(coordinate);
            point.setSight(sight);
            if (pointRepository.save(point) != null) {
                return ADD_OK;
            }else {
                return ADD_FAIL;
            }
        }
        return ID_FAIL;
    }

    static ResponseCode UPDATE_OK = new ResponseCode(1,"update point success");
    static ResponseCode UPDATE_FAIL = new ResponseCode(0,"update point fail");
    //id=pointId
    @PostMapping(value = "/point/update")
    public ResponseCode pointUpdate(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("coordinate") String coordinate) {
        Point point = pointRepository.findById(id).orElse(null);
        if (point != null) {
            Point newPoint = new Point();
            newPoint.setId(point.getId());
            newPoint.setSight(point.getSight());
            newPoint.setName(name);
            newPoint.setCoordinate(coordinate);
            if (pointRepository.save(newPoint) != null) {
                return UPDATE_OK;
            }else {
                return UPDATE_FAIL;
            }
        }
        return ID_FAIL;
    }


    static ResponseCode DELETE_OK = new ResponseCode(1,"delete point success");
    static ResponseCode DELETE_FAIL = new ResponseCode(0,"delete point fail");
    //id=pointId
    @GetMapping(value = "/point/delete")
    public ResponseCode pointDelete(@RequestParam("id") Integer id) {
        if (pointRepository.findById(id).orElse(null) != null) {
            pointRepository.deleteById(id);
            return DELETE_OK;
        }else {
            return DELETE_FAIL;
        }
    }
}
