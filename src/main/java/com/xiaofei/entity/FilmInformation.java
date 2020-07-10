package com.xiaofei.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.*;

@Data
public class FilmInformation {
    private Integer id;

    private String videoName;

    private String director;

    private String performer;

    private String region;
    @JsonFormat(pattern = "yyyy")
    private Date yearG;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    private Integer score;

    private String playUrl;

    private String imgUrl;

    private String introduction;

    private List<FilmType> filmTypes = new ArrayList<>();

}