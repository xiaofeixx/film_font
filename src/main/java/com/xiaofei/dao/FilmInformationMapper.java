package com.xiaofei.dao;

import com.xiaofei.entity.Episodes;
import com.xiaofei.entity.FilmInformation;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.util.dynamic.FilmDynamicSql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname FilmInformationMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface FilmInformationMapper {


    @SelectProvider(type = FilmDynamicSql.class, method = "findFilmByScreen")
    List<FilmInformation> findFilmByScreen(ScreenObject screenObject);

    @Select("select id, video_name, performer, img_url from film_information order by score limit 8")
    List<FilmInformation> dpRecommend();

    @SelectProvider(type = FilmDynamicSql.class, method = "checkGetMustNewFilm")
    List<FilmInformation> checkGetMustFilm(String type, String sort, int size);

    @Select("select * from film_information where id = #{id}")
    @Results(id = "film_find", value = {
            @Result(property = "filmTypes", column = "id", javaType = List.class, many = @Many(select = "com.xiaofei.dao.FilmTypeMapper.findTypeByFilmId"))
    })
    FilmInformation findFilmById(int id);


    @Select("select * from film_information limit #{recommendIndex},12")
    List<FilmInformation> findRecommend(int recommendIndex);

    @Select("select * from film_information order by score desc limit #{size}")
    List<FilmInformation> findHighScoreFilm(int size);

    @Select("select count(id) as counts from film_information")
    Map<String, Long> findFilmNumber();
}
