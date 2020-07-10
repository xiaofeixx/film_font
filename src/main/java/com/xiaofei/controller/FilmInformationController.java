package com.xiaofei.controller;

import com.xiaofei.entity.FilmInformation;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.service.FilmInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname FilmInformationController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@RestController
@RequestMapping("/pub")
public class FilmInformationController {

    @Autowired
    private FilmInformationService informationService;


    @PostMapping("/film/screen")
    public List<FilmInformation> findFilmByScreen(@RequestBody ScreenObject screenObject) {
        List<FilmInformation> findResult = null;
        try {
            findResult = informationService.findFilmByScreen(screenObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return findResult;
    }


    @GetMapping("/film/dpRecommend")
    public List<FilmInformation> dpRecommend() {
        List<FilmInformation> results = null;
        try {
            results = informationService.dpRecommend();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @GetMapping("/film/checkGetNew")
    public List<FilmInformation> checkGetMustFilm(@RequestParam("type") String type, @RequestParam String sort, @RequestParam int size) {
        List<FilmInformation> filmInformations = null;
        try {
            filmInformations = informationService.checkGetMustFilm(type, sort, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmInformations;
    }

    @GetMapping("/findFilmPlay")
    public FilmInformation findFilmByid(int id) {
        FilmInformation filmInformation = null;
        try {
            filmInformation = informationService.findFilmById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmInformation;
    }

    @GetMapping("/film/recommend")
    public List<FilmInformation> findRecommend(@RequestParam int recommendIndex) {
        List<FilmInformation> filmInformations = null;
        try {
            filmInformations = informationService.findRecommend(recommendIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmInformations;
    }

    @GetMapping("/film/highScore")
    public List<FilmInformation> findHighScoreFilm(@RequestParam int size) {
        List<FilmInformation> filmInformations = null;
        try {
            filmInformations = informationService.findHighScoreFilm(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmInformations;
    }

    @GetMapping("/film/filmCount")
    public Map findFilmNumber() {
        Map map = null;
        try {
            map = informationService.findFilmNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
