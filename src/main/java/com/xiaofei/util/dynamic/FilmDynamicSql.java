package com.xiaofei.util.dynamic;

import com.xiaofei.entity.ScreenObject;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author xiaofei
 * @Classname FilmDynamicSql
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public class FilmDynamicSql {


    public String findFilmByScreen(ScreenObject screenObject) {

        return new SQL() {
            {
                SELECT(" distinct fi.id,fi.video_name,fi.performer,fi.img_url");
                FROM("film_information as fi");
                INNER_JOIN("film_type as ft on fi.id = ft.film_id");
                LIMIT("#{currentPage},#{pageSize}");
                if (screenObject.getSort().equals("最新")) {
                    ORDER_BY("fi.publish_time desc");
                } else {
                    ORDER_BY("fi.score desc");
                }

                if (!screenObject.getType().equals("全部")) {
                    WHERE("ft.film_type = #{type}");
                }
                if (!screenObject.getRegion().equals("全部")) {
                    WHERE("fi.region = #{region}");
                }
                if (!screenObject.getYear().equals("全部")) {
                    WHERE("fi.year_g = #{year}");
                }
                if (!screenObject.getPostage().equals("全部")) {
                    if (screenObject.getPostage().equals("付费")) {
                        WHERE("fi.vip = 0");
                    } else {
                        WHERE("fi.vip != 0");
                    }
                }


            }
        }.toString();

    }


    public String checkGetMustNewFilm(Map map) {

        return new SQL() {{
            SELECT(" distinct fi.id,fi.video_name,fi.performer,fi.img_url");
            FROM("film_information as fi");
            INNER_JOIN("film_type as ft on fi.id = ft.film_id");
            if ("最新".equals(map.get("sort"))) {
                ORDER_BY("fi.publish_time desc");
            } else {
                ORDER_BY("fi.score desc");
            }
            WHERE("ft.film_type = #{type}");
            LIMIT("#{size}");
        }}.toString();
    }


}
