package com.xiaofei.service;

import com.xiaofei.entity.ScreenObject;
import com.xiaofei.entity.VideoInformation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname VideoInformationService
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface VideoInformationService {

    List<VideoInformation> findMustNewFilm(int contentSize,String filmType);

    List<VideoInformation> findByScreen(ScreenObject screenObject) throws IOException;

    VideoInformation findVideoPlay(int id) throws IOException;

    List<VideoInformation> findPlayPageRecommend(int index, String category) throws IOException;

    List<VideoInformation> findPlayPageHighScoreVideo(int size, String category) throws IOException;

    Map<String, Long> findVideoCounts(String category) throws IOException;
}
