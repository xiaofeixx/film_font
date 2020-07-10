package com.xiaofei.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaofei
 * @Classname ScreenObject
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Data
public class ScreenObject implements Serializable {

    private String category;  //类别(电视/电影)
    private String sort;      //排序方式
    private String type;      //类型
    private String region;    //地区
    private String year;      //年份
    private String postage;   //资费问题
    private int currentPage;  //当前页面最后一个视频
    private int pageSize;   //每页大小
}
