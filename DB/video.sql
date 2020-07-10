CREATE TABLE video_score
(
    score    int(11) DEFAULT NULL COMMENT '评论分数',
    user_id  int(11) DEFAULT NULL COMMENT '用户ud',
    video_id int(11) DEFAULT NULL COMMENT '视频id',
    id       int(11) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='对视频进行评分';

CREATE TABLE video_category
(
    id                int(11)                                                      NOT NULL AUTO_INCREMENT COMMENT '类别id',
    category_name     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '综艺' COMMENT '类别名',
    category_sub_type varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '正片' COMMENT '大分类的子分类',
    video_id          int(11)                                                      NOT NULL COMMENT '视频id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21189
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE film_type
(
    id        bigint(20) NOT NULL AUTO_INCREMENT,
    film_id   int(11)     DEFAULT NULL COMMENT '电影id',
    film_type varchar(20) DEFAULT NULL COMMENT '电影类型',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13773
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE evaluate
(
    id           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评价评分id',
    user_comment text       NOT NULL COMMENT '评论',
    user_id      int(11)    NOT NULL COMMENT '评论用户id',
    video_id     int(11)    NOT NULL COMMENT '被评论影片id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `video_information`
(
    id           int(11)      NOT NULL AUTO_INCREMENT COMMENT '视频id',
    video_name   varchar(255) NOT NULL COMMENT '视频名',
    director     varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '未知' COMMENT '导演',
    performer    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '未知' COMMENT '演员',
    introduction text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '简介',
    region       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT '未知' COMMENT '地区',
    year_g       char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      DEFAULT '2017' COMMENT '年份',
    img_url      varchar(255) NOT NULL COMMENT '封面地址',
    create_time  datetime                                                      DEFAULT CURRENT_TIMESTAMP COMMENT '年份',
    vip          tinyint(4)                                                    DEFAULT '0' COMMENT '是否是vip',
    play_number  bigint(20)                                                    DEFAULT '0' COMMENT '播放次数',
    score        float                                                         DEFAULT NULL COMMENT '评分',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13261
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE film_information
(
    id           int(11)    NOT NULL AUTO_INCREMENT COMMENT '视频id',
    video_name   varchar(255)        DEFAULT '未知' COMMENT '视频名',
    director     varchar(40)         DEFAULT '未知' COMMENT '导演',
    performer    varchar(255)        DEFAULT '未知' COMMENT '演员',
    introduction text COMMENT '简介',
    region       varchar(40)         DEFAULT '未知' COMMENT '地区',
    year_g       year(4)             DEFAULT '2017' COMMENT '年份',
    publish_time datetime            DEFAULT CURRENT_TIMESTAMP COMMENT '上线时间',
    score        int(11)             DEFAULT NULL COMMENT '评分',
    play_url     varchar(255)        DEFAULT NULL COMMENT '播放url',
    img_url      varchar(255)        DEFAULT NULL COMMENT '图片url',
    vip          tinyint(4) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4973
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE episodes
(
    id         bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '自增id',
    video_id     int(11)      NOT NULL COMMENT '影片对应id',
    url          varchar(255) NOT NULL COMMENT '对应url',
    episodes_num bigint(20) DEFAULT NULL COMMENT '集数',
    vip          int(11)    DEFAULT '2' COMMENT '是否需要vip',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 508084
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

select count(id)
from film_type
where film_type = '动作';

select distinct distinct category_sub_type
from video_category
where category_name = '综艺'

select vi.*
from video_information vi
         left join video_category vc on vi.id = vc.video_id
where vc.category_name = '电视剧'
order by vi.score desc
limit 10;

select distinct vi.*
from video_information as vi
         left join video_category as vc on vi.id = vc.video_id
where vc.category_name = '电视剧'
order by vi.score desc
limit 12