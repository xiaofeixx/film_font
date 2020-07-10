package com.xiaofei.service.iml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaofei.dao.VideoInformationMapper;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.entity.VideoInformation;
import com.xiaofei.service.VideoInformationService;
import com.xiaofei.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname VideoInformationServiceIml
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Service
@Primary
public class VideoInformationServiceIml implements VideoInformationService {

    @Autowired
    private VideoInformationMapper videoInformationMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * * @param contentSize
     *
     * @param filmType
     * @return {@link List< VideoInformation>}
     * @throws
     * @description 根据剧集类型和查出视频个数
     */
    @Override
    public List<VideoInformation> findMustNewFilm(int contentSize, String filmType) {
        List<VideoInformation> videoInformations = null;
        return videoInformationMapper.findMustNewFilm(contentSize, filmType);
    }

    /**
     * * @param screenObject
     *
     * @return {@link List< VideoInformation>}
     * @throws
     * @description 根据筛选分类查询剧集
     */
    @Override
    public List<VideoInformation> findByScreen(ScreenObject screenObject) throws IOException {
        System.out.println(screenObject);
        List<VideoInformation> videoInformations = null;
        String result = ((String) jedisUtil.get(screenObject.toString()));
        if (result == null) {
            videoInformations = videoInformationMapper.findByScreen(screenObject);
            jedisUtil.set(screenObject.toString(), objectMapper.writeValueAsString(videoInformations), 86400);
            return videoInformations;
        }
        videoInformations = objectMapper.readValue(result, List.class);
        return videoInformations;
    }

    /**
     * * @param id
     *
     * @return {@link VideoInformation}
     * @throws
     * @description 通过id查找播放视频信息
     */
    @Override
    public VideoInformation findVideoPlay(int id) throws IOException {
        VideoInformation videoInformation = null;
        String resault = ((String) jedisUtil.get("videoPlayId_" + id));
        if (resault == null) {
            videoInformation = videoInformationMapper.findVideoPlay(id);
            jedisUtil.set("videoPlayId_" + id, objectMapper.writeValueAsString(videoInformation), 86400);
            return videoInformation;
        }
        videoInformation = objectMapper.readValue(resault, VideoInformation.class);
        return videoInformation;
    }

    /**
     * * @param index
     *
     * @param category
     * @return {@link List< VideoInformation>}
     * @throws
     * @description 根据分类和索引查找播放页下方推荐
     */
    @Override
    public List<VideoInformation> findPlayPageRecommend(int index, String category) throws IOException {
        List<VideoInformation> videoInformations = null;
        String result = ((String) jedisUtil.get("playPageRecommend_" + index + category));
        if (result == null) {
            videoInformations = videoInformationMapper.findPlayPageRecommend(index, category);
            jedisUtil.set("playPageRecommend_" + index + category, objectMapper.writeValueAsString(videoInformations), 86400);
            return videoInformations;
        }
        videoInformations = objectMapper.readValue(result, List.class);
        return videoInformations;
    }

    /**
     * * @param size
     *
     * @param category
     * @return {@link List< VideoInformation>}
     * @throws
     * @description 播放页高分推荐查找
     */
    @Override
    public List<VideoInformation> findPlayPageHighScoreVideo(int size, String category) throws IOException{
        List<VideoInformation> videoInformations = null;
        String result = ((String) jedisUtil.get("playPageHighScore_" + size + category));
        if (result == null ) {
           videoInformations =  videoInformationMapper.findPlayPageHighScoreVideo(size, category);
           jedisUtil.set("playPageHighScore_" + size + category,objectMapper.writeValueAsString(videoInformations), 86400);
           return videoInformations;
        }
        videoInformations = objectMapper.readValue(result,List.class);
        return videoInformations;
    }

    /**
     * * @param category
     *@return {@link Map< String, Long>}
     *@throws
     *@description 查找对应分类的视频数量
     */
    @Override
    public Map<String, Long> findVideoCounts(String category) throws IOException{
        Map map = null;
        String result = ((String) jedisUtil.get("videoCount_" + category));
        if (result == null ) {
           map = videoInformationMapper.findVideoCounts(category);
           jedisUtil.set("videoCount_"+category,objectMapper.writeValueAsString(map),86400);
           return map;
        }
        map = objectMapper.readValue(result,Map.class);
        return map;
    }
}
