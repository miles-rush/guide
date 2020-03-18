package com.miles.demo.controller;

import com.miles.demo.bean.ResponseCode;
import com.miles.demo.bean.Spot;
import com.miles.demo.bean.Voice;
import com.miles.demo.repository.SpotRepository;
import com.miles.demo.repository.VoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
public class VoiceController {

    static ResponseCode CODE_OK = new ResponseCode(1,"add voice success");
    static ResponseCode CODE_FAIL = new ResponseCode(0,"add voice fail");
    static ResponseCode CODE_FAIL_ID = new ResponseCode(0,"id not exist");
    @Autowired
    private VoiceRepository voiceRepository;
    @Autowired
    private SpotRepository spotRepository;
    //上传中的ID是spotID
    @PostMapping(value = "/voice/upload")
    public ResponseCode voiceUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
        Spot spot = spotRepository.findById(id).orElse(null);
        if (spot == null) {
            return CODE_FAIL_ID;
        }
        try {
            byte[] bytes = file.getBytes();
            String fileName = "" + new Date().getTime();
            String filePath = "D:\\voice\\" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            Voice voice = new Voice();
            voice.setName(file.getName());
            voice.setResourcesPath(fileName);
            voice.setFilePath(filePath);
            voice.setSpot(spot);
            voiceRepository.save(voice);
            return CODE_OK;
        } catch (IOException e) {
            e.printStackTrace();
            return CODE_FAIL;
        }
    }

    static ResponseCode DELETE_OK = new ResponseCode(1,"delete voice success");
    static ResponseCode DELETE_FAIL = new ResponseCode(0,"delete voice fail");
    @PostMapping(value = "/voice/delete")
    public ResponseCode voiceDelete(@RequestParam("id") Integer id) {
        if (voiceRepository.findById(id).orElse(null) != null) {
            voiceRepository.deleteById(id);
            return DELETE_OK;
        }else {
            return DELETE_FAIL;
        }
    }

    //更新中的ID是VOICE的ID
    static ResponseCode UPDATE_OK = new ResponseCode(1,"update voice success");
    static ResponseCode UPDATE_FAIL = new ResponseCode(0,"update voice fail");
    @PostMapping(value = "/voice/update")
    public ResponseCode voiceUpdate(@RequestParam("id") Integer id,@RequestParam("file") MultipartFile file) {
        Voice voice = voiceRepository.findById(id).orElse(null);
        if (voice == null) {
            return CODE_FAIL_ID;
        }
        try {
            byte[] bytes = file.getBytes();
            String fileName = "" + new Date().getTime();
            String filePath = "D:\\voice\\" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            //更新
            Voice newVoice = new Voice();
            newVoice.setId(voice.getId());
            newVoice.setName(file.getName());
            newVoice.setResourcesPath(fileName);
            newVoice.setFilePath(filePath);
            newVoice.setSpot(voice.getSpot());
            if (voiceRepository.save(voice) != null) {
                return UPDATE_OK;
            }
            return UPDATE_FAIL;
        } catch (IOException e) {
            e.printStackTrace();
            return UPDATE_FAIL;
        }
    }

}
