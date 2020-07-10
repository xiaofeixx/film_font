package com.xiaofei.service.iml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaofei.dao.FilmInformationMapper;
import com.xiaofei.entity.FilmInformation;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.service.FilmInformationService;
import com.xiaofei.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname FilmInformationServiceIml
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Service
@Primary
public class FilmInformationServiceIml implements FilmInformationService {

    @Autowired
    private FilmInformationMapper informationMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * * @param screenObject
     *
     * @return {@link List}
     * @throws IOException
     * @description 通过筛选查询
     */
    @Override
    public List findFilmByScreen(ScreenObject screenObject) throws IOException {
        List findResult = null;
        String result = (String) jedisUtil.get(screenObject.toString());
        if (result == null) {
            findResult = informationMapper.findFilmByScreen(screenObject);
            jedisUtil.set(screenObject.toString(), objectMapper.writeValueAsString(findResult), 86400);
            return findResult;
        }
        findResult = objectMapper.readValue(result, List.class);
        return findResult;
    }

    /**
     * * @param id
     *
     * @return {@link FilmInformation}
     * @throws IOException
     * @description 通过id查找电影
     */
    @Override
    public FilmInformation findFilmById(int id) throws IOException {
        FilmInformation filmInformation = null;
        String result = (String) jedisUtil.get("film_" + id);
        if (result == null) {
            filmInformation = informationMapper.findFilmById(id);
            jedisUtil.set("film_" + id, objectMapper.writeValueAsString(filmInformation), 86400);
            return filmInformation;
        }
        filmInformation = objectMapper.readValue(result, FilmInformation.class);
        return filmInformation;
    }

    /**
     * * @param
     *
     * @return {@link List< FilmInformation>}
     * @throws
     * @description 大片推荐部分，主页面
     */
    @Override
    public List<FilmInformation> dpRecommend() throws IOException {
        List<FilmInformation> findResults = null;
        String result = ((String) jedisUtil.get("dpRecommend"));
        if (result == null) {
            findResults = informationMapper.dpRecommend();
            jedisUtil.set("dpRecommend", objectMapper.writeValueAsString(findResults), 86400);
            return findResults;
        }
        findResults = objectMapper.readValue(result, List.class);
        return findResults;
    }

    /**
     * * @param type
     *
     * @param sort
     * @param size
     * @return {@link List< FilmInformation>}
     * @throws IOException
     * @description 根据类型，排序方式和查找数量进行查找电影
     */
    @Override
    public List<FilmInformation> checkGetMustFilm(String type, String sort, int size) throws IOException {
        List<FilmInformation> filmInformations;
        String result = ((String) jedisUtil.get(type + sort + size));
        if (result == null) {
            filmInformations = informationMapper.checkGetMustFilm(type, sort, size);
            jedisUtil.set(type + sort + size, objectMapper.writeValueAsString(filmInformations), 86400);
            return filmInformations;
        }
        filmInformations = objectMapper.readValue(result, List.class);
        return filmInformations;
    }

    /**
     * * @param recommendIndex
     *
     * @return {@link List< FilmInformation>}
     * @throws IOException
     * @description 播放页下方推荐
     */
    @Override
    public List<FilmInformation> findRecommend(int recommendIndex) throws IOException {
        List<FilmInformation> filmInformations;
        String result = ((String) jedisUtil.get("recommendIndex" + recommendIndex));
        if (result == null) {
            filmInformations = informationMapper.findRecommend(recommendIndex);
            jedisUtil.set("recommendIndex" + recommendIndex, objectMapper.writeValueAsString(filmInformations), 86400);
            return filmInformations;
        }
        filmInformations = objectMapper.readValue(result, List.class);
        return filmInformations;
    }

    /**
     * * @param size
     *
     * @return {@link List< FilmInformation>}
     * @throws
     * @description 查找高分电影
     */
    @Override
    public List<FilmInformation> findHighScoreFilm(int size) throws IOException{
        List<FilmInformation> filmInformations;
        String result = ((String) jedisUtil.get("findHighScoreFilm" + size));
        if (result == null) {
            filmInformations = informationMapper.findHighScoreFilm(size);
            jedisUtil.set("findHighScoreFilm" + size, objectMapper.writeValueAsString(filmInformations), 86400);
            return filmInformations;
        }
        filmInformations = objectMapper.readValue(result, List.class);
        return filmInformations;
    }

    /**
     * * @param
     *
     * @return {@link Map< String, Long>}
     * @throws
     * @description 用作分页
     */
    @Override
    public Map findFilmNumber() throws IOException {
        Map result = null;
        String str = ((String) jedisUtil.get("tollSize"));
        if (str == null) {
            result = informationMapper.findFilmNumber();
            jedisUtil.set("tollSize", objectMapper.writeValueAsString(result), 86400);
            return result;
        }
        result = objectMapper.readValue(str, Map.class);
        return result;
    }
}
