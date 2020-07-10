package com.xiaofei.controller;

import com.xiaofei.conf.annotation.SysLog;
import com.xiaofei.entity.Role;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.entity.VideoInformation;
import com.xiaofei.service.VideoInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname VideoInformationController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@RestController
@RequestMapping("/pub/video")
public class VideoInformationController {
    @Autowired
    private VideoInformationService informationService;

    @GetMapping("/newFilm")
    @SysLog
    public List<VideoInformation> findMustNewFilm(int contentSize,String filmType){
        System.out.println("被调用=====================================");
        return informationService.findMustNewFilm(contentSize,filmType);
//        return new ArrayList<>();
    }

    @RequestMapping(value = "/screen")
    public List<VideoInformation> findByScreen(@RequestBody ScreenObject screenObject){
        List<VideoInformation> videoInformations = null;
        try {
           videoInformations = informationService.findByScreen(screenObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoInformations;
    }

    @RequestMapping("/test")
    public Role test(@RequestBody Role role){
        return role;
    }

    @GetMapping("/findVideoPlay")
    public VideoInformation findVideoPlay(int id) {
        VideoInformation videoInformation = null;
        try {
            videoInformation = informationService.findVideoPlay(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoInformation;
    }

    @GetMapping("/recommend")
    public List<VideoInformation> findPlayPageRecommend(@RequestParam int index,@RequestParam String category) {
        List<VideoInformation> videoInformations = null;
        try {
            videoInformations =informationService.findPlayPageRecommend(index,category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoInformations;
    }

    @GetMapping("/highScore")
    public List<VideoInformation> findPlayPageHighScoreVideo(@RequestParam int size,@RequestParam String category) {
        List<VideoInformation> videoInformations = null;
        try {
            videoInformations = informationService.findPlayPageHighScoreVideo(size,category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoInformations;
    }

    @GetMapping("/videoCount")
    public Map findVideoCounts (@RequestParam String category) {
        Map map = null;
        try {
            map =  informationService.findVideoCounts(category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
