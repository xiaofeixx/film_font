package com.xiaofei.util.dynamic;

import com.xiaofei.entity.ScreenObject;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author xiaofei
 * @Classname VideoDynamicSql
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public class VideoDynamicSql {

    public String getMustNew(Map map) {
/*
* select * from video_information as vi join video_category vc on vi.category_id=vc.id  <where>" +
            "<if test=\"filmType != null\">vc.category_sub_type=#{filmType}</if>" +
            "<if test=\"contentSize !=null \">1=1 limit #{contentSize}</if></where>*/
        return new SQL() {
            {
                SELECT("*");
                FROM("video_information as vi join video_category vc on vi.category_id=vc.id");
                if (map.get("filmType") != null) {
                    WHERE("vc.category_sub_type=#{filmType}");
                }
                if (map.get("contentSize") != null) {
                    LIMIT("#{contentSize}");
                }
            }
        }.toString();
    }

   /**
    * * @param screenObject
    *@return {@link String}
    *@throws
    *@description 对视频进行粒度更细的筛选
    */
    public String getScreen(ScreenObject screenObject) {

        return new SQL() {
            {
                SELECT("distinct vi.id,vi.video_name,vi.performer,vi.img_url,vc.category_name as region");   //将类型存入region有用
                FROM("video_information as vi ");
                INNER_JOIN("video_category vc on vi.id=vc.video_id");
                if (screenObject.getSort().equals("最新")) {
                    ORDER_BY("vi.play_number desc");
                }

                if (screenObject.getSort().equals("最新")) {
                    ORDER_BY("vi.create_time desc");
                }


                if (!screenObject.getType().equals("全部")) {
                    WHERE("vc.category_sub_type = #{type}");
                }
                if (!screenObject.getRegion().equals("全部")) {
                    WHERE("vi.region = #{region}");
                }
                if (!screenObject.getYear().equals("全部")) {
                    WHERE("vi.year_g = #{year}");
                }
                if (!screenObject.getPostage().equals("全部")) {
                    if (screenObject.getPostage().equals("付费")) {
                        WHERE("vi.vip = 0");
                    } else {
                        WHERE("vi.vip > 0");
                    }
                }
                WHERE("vc.category_name = #{category}");
                LIMIT("#{currentPage},#{pageSize}");
            }
        }.toString();
    }

}
