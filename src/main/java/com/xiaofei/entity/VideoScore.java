package com.xiaofei.entity;

import lombok.Data;

@Data
public class VideoScore {
    private Integer id;

    private Integer score;

    private Integer userId;

    private Integer videoId;
}