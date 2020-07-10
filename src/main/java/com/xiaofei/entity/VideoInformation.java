package com.xiaofei.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VideoInformation {
    private Integer id;

    private String videoName;

    private String director;

    private String performer;

    private String region;

    private String yearG;

    private String imgUrl;

    private Date createTime;

    private Byte vip;

    private Long playNumber;

    private Float score;

    private String introduction;

    private List<VideoCategory> videoCategories;

    private List<Episodes> episodes;

}