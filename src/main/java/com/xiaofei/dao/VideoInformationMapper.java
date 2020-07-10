package com.xiaofei.dao;

import com.xiaofei.entity.Episodes;
import com.xiaofei.entity.ScreenObject;
import com.xiaofei.entity.VideoInformation;
import com.xiaofei.util.dynamic.VideoDynamicSql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname VideoInformationMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface VideoInformationMapper {
/*查询最新影视
    @Select("<script>select * from video_information as vi join video_category vc on vi.category_id=vc.id  <where>" +
            "<if test=\"filmType != null\">vc.category_sub_type=#{filmType}</if>" +
            "<if test=\"contentSize !=null \">1=1 limit #{contentSize}</if></where></script>")*/
    @SelectProvider(type = VideoDynamicSql.class,method = "getMustNew")
     List<VideoInformation> findMustNewFilm(int contentSize,String filmType);

    @SelectProvider(type = VideoDynamicSql.class,method = "getScreen")
//    @Select("select * from user")
    List<VideoInformation> findByScreen(ScreenObject screenObject);

    @Select("select * from video_information where id = #{id}")
    @Results(id = "video_find",value = {
       @Result(property = "episodes",column = "id", javaType = List.class,
               many = @Many(select = "com.xiaofei.dao.VideoInformationMapper.findEpisodes")),
       @Result(property = "videoCategories", column = "id", javaType = List.class,
               many = @Many(select = "com.xiaofei.dao.VideoTypeMapper.findVideoCategory"))
    })
    VideoInformation findVideoPlay(int id);

    @Select("select * from episodes where video_id = #{videoId}")
    List<Episodes> findEpisodes(int videoId);

    @Select("select distinct vi.* from video_information as vi left join video_category as vc on vi.id = vc.video_id" +
            " where vc.category_name = #{category} limit #{index},12")
    List<VideoInformation> findPlayPageRecommend(int index, String category);

    @Select("select distinct vi.* from video_information as vi left join video_category as vc on vi.id = vc.video_id where vc.category_name = #{category} order by vi.score desc  limit #{size}")
    List<VideoInformation> findPlayPageHighScoreVideo(int size, String category);

    @Select("select count(vi.id) as counts from video_information as vi inner join video_category as vc on vi.id = vc.video_id where vc.category_name = #{category}")
    Map<String, Long> findVideoCounts(String category);
}