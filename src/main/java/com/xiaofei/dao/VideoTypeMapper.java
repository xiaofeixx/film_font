package com.xiaofei.dao;

import com.xiaofei.entity.VideoCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaofei
 * @Classname VideoMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface VideoTypeMapper {

    @Select("select * from video_category where video_id = #{videoId}")
    List<VideoCategory> findVideoCategory(int videoId);
}
