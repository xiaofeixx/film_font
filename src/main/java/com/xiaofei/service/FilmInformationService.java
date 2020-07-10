package com.xiaofei.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xiaofei.entity.FilmInformation;
import com.xiaofei.entity.ScreenObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname FilmInformationService
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface FilmInformationService {

    List<FilmInformation> findFilmByScreen(ScreenObject screenObject) throws JsonProcessingException, IOException;

    List<FilmInformation> dpRecommend() throws IOException;

    List<FilmInformation> checkGetMustFilm(String type, String sort, int size) throws IOException;

    FilmInformation findFilmById(int id) throws JsonProcessingException, IOException;

    List<FilmInformation> findRecommend(int recommendIndex) throws IOException;

    List<FilmInformation> findHighScoreFilm(int size) throws IOException;

    Map<String,Long> findFilmNumber() throws IOException;
}
