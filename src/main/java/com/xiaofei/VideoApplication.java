package com.xiaofei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.xiaofei.*")
public class VideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class);
    }
}
