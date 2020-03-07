package com.miles.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpotController {

    @Autowired
    private SpotRepository spotRepository;

    /**
     * 查询所有景点列表
     * @return
     */
    @GetMapping(value = "/spot/list")
    public List<Spot> spotList() {
        System.out.println(spotRepository.findAll());
        return spotRepository.findAll();
    }


    @PostMapping(value = "/spot/add")
    public Spot spotAdd(@RequestParam("name") String name, @RequestParam("introduce") String introduce) {
        Spot spot = new Spot();
        spot.setName(name);
        spot.setIntroduce(introduce);

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
        Spot spot = new Spot();
        spot.setId(id);
        spot.setName(name);
        spot.setIntroduce(introduce);

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
