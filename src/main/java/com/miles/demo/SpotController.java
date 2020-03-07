package com.miles.demo;

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

    @PostMapping(value = "/spot/add")
    public Spot spotAdd(@RequestParam("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("introduce") String introduce) {
        Sight sight = sightRepository.findById(id).orElse(null);
        if (sight == null) {
            return null;
        }

        Spot spot = new Spot();
        spot.setName(name);
        spot.setIntroduce(introduce);
        spot.setSight(sight);

        return spotRepository.save(spot);
    }

    @GetMapping(value = "/spot/query")
    public Spot spotFindOne(@RequestParam("id") Integer id) {
        return spotRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/spot/update")
    public Spot spotUpdate(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("introduce") String introduce){
        Spot oldSpot = spotRepository.findById(id).orElse(null);
        if (oldSpot == null) {
            return null;
        }

        Spot spot = new Spot();
        spot.setId(id);
        spot.setName(name);
        spot.setIntroduce(introduce);
        spot.setSight(oldSpot.getSight());

        return spotRepository.save(spot);

    }

    @PostMapping(value = "/spot/delete")
    public ResponseCode spotDelete(@RequestParam("id") Integer id) {
        if (spotRepository.findById(id).orElse(null) != null) {
            spotRepository.deleteById(id);
            return VoiceController.CODE_OK;
        }else {
            return VoiceController.CODE_FAIL_ID;
        }

    }

}
