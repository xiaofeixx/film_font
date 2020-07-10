package com.xiaofei.dao;

import com.xiaofei.entity.FilmType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaofei
 * @Classname FilmTypeMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface FilmTypeMapper {

    @Select("select * from film_type where film_id = #{id}")
    List<FilmType> findTypeByFilmId(int id);
}
