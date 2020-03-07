package com.miles.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
public class VoiceController {
    static ResponseCode CODE_OK = new ResponseCode(1,"success");
    static ResponseCode CODE_FAIL = new ResponseCode(0,"fail");
    static ResponseCode CODE_FAIL_ID = new ResponseCode(0,"id not exist");

    @Autowired
    private VoiceRepository voiceRepository;
    @Autowired
    private SpotRepository spotRepository;


    @PostMapping(value = "/voice/upload")
    public ResponseCode voiceUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        Spot spot = spotRepository.findById(id).orElse(null);
        if (spot == null) {
            return CODE_FAIL_ID;
        }
        try {

            byte[] bytes = file.getBytes();
            String filePath = "D:\\voice\\" + new Date().getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            Voice voice = new Voice();
            voice.setFilePath(filePath);

            voice.setSpot(spot);

            voiceRepository.save(voice);


            return CODE_OK;
        } catch (IOException e) {
            e.printStackTrace();
            return CODE_FAIL;
        }

    }
}
