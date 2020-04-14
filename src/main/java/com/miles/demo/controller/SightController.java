package com.miles.demo.controller;

import com.miles.demo.bean.ResponseCode;
import com.miles.demo.bean.Sight;
import com.miles.demo.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SightController {
    @Autowired
    private SightRepository sightRepository;

    @GetMapping(value = "/sight/list")
    public List<Sight> spotList() {
        return sightRepository.findAll();
    }

    static ResponseCode ADD_OK = new ResponseCode(1,"add success");
    static ResponseCode ADD_FAIL = new ResponseCode(0,"add fail");
    @PostMapping(value = "/sight/add")
    public ResponseCode spotAdd(@RequestParam("name") String name,
                                @RequestParam("introduce") String introduce,
                                @RequestParam("address") String address,
                                @RequestParam("contact") String contact) {
        Sight sight = new Sight();
        sight.setName(name);
        sight.setIntroduce(introduce);

        sight.setAddress(address);
        sight.setContact(contact);

		Sight resSight = sightRepository.save(sight);
        if (resSight != null) {
            return new ResponseCode(1,"add success",resSight.getId());
        }else {
            return ADD_FAIL;
        }

    }

    @GetMapping(value = "/sight/query")
    public Sight spotFindOne(@RequestParam("id") Integer id) {
        return sightRepository.findById(id).orElse(null);
    }

    static ResponseCode UPDATE_OK = new ResponseCode(1,"update success");
    static ResponseCode UPDATE_FAIL = new ResponseCode(0,"update fail");
    @PostMapping(value = "/sight/update")
    public ResponseCode spotUpdate(@RequestParam("id") Integer id,
                                   @RequestParam("name") String name,
                                   @RequestParam("introduce") String introduce,
                                   @RequestParam("address") String address,
                                   @RequestParam("contact") String contact){
        Sight sight = new Sight();
        sight.setId(id);
        sight.setName(name);
        sight.setIntroduce(introduce);

        sight.setAddress(address);
        sight.setContact(contact);

        if (sightRepository.save(sight) != null) {
            return UPDATE_OK;
        }
        return UPDATE_FAIL;
    }

    static ResponseCode DELETE_OK = new ResponseCode(1,"delete voice success");
    static ResponseCode DELETE_FAIL = new ResponseCode(0,"delete voice fail");
    @PostMapping(value = "/sight/delete")
    public ResponseCode spotDelete(@RequestParam("id") Integer id) {
        if (sightRepository.findById(id).orElse(null) != null) {
            sightRepository.deleteById(id);
            return DELETE_OK;
        }else {
            return DELETE_FAIL;
        }

    }

}
