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


    @PostMapping(value = "/sight/add")
    public Sight spotAdd(@RequestParam("name") String name, @RequestParam("introduce") String introduce) {
        Sight sight = new Sight();
        sight.setName(name);
        sight.setIntroduce(introduce);

        return sightRepository.save(sight);
    }

    @GetMapping(value = "/sight/query")
    public Sight spotFindOne(@RequestParam("id") Integer id) {
        return sightRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/sight/update")
    public Sight spotUpdate(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("introduce") String introduce){
        Sight sight = new Sight();
        sight.setId(id);
        sight.setName(name);
        sight.setIntroduce(introduce);

        return sightRepository.save(sight);

    }

    @PostMapping(value = "/sight/delete")
    public ResponseCode spotDelete(@RequestParam("id") Integer id) {
        if (sightRepository.findById(id).orElse(null) != null) {
            sightRepository.deleteById(id);
            return VoiceController.CODE_OK;
        }else {
            return VoiceController.CODE_FAIL_ID;
        }

    }

}
