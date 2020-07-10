package com.xiaofei.entity;

import lombok.Data;

@Data
public class VideoCategory {
    private Integer id;

    private String categoryName;

    private String categorySubType;

    private Integer videoId;

}