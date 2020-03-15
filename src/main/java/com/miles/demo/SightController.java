package com.miles.demo;

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
    public ResponseCode spotAdd(@RequestParam("name") String name, @RequestParam("introduce") String introduce) {
        Sight sight = new Sight();
        sight.setName(name);
        sight.setIntroduce(introduce);
        if (sightRepository.save(sight) != null) {
            return ADD_OK;
        }

        return ADD_FAIL;
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
                           @RequestParam("introduce") String introduce){
        Sight sight = new Sight();
        sight.setId(id);
        sight.setName(name);
        sight.setIntroduce(introduce);
        if (sightRepository.save(sight) != null) {
            return UPDATE_OK;
        }
        return UPDATE_FAIL;
    }

    @GetMapping(value = "/sight/delete")
    public ResponseCode spotDelete(@RequestParam("id") Integer id) {
        if (sightRepository.findById(id).orElse(null) != null) {
            sightRepository.deleteById(id);
            return VoiceController.CODE_OK;
        }else {
            return VoiceController.CODE_FAIL_ID;
        }

    }

}
