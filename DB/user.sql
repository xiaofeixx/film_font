# 创建用户表
create table user
(
    id         int primary key auto_increment comment '用户id',
    name       varchar(40) not null default '未命名' comment '用户名',
    password   varchar(40) not null comment '用户密码',
    age        int comment '年龄',
    sex        char(1) comment '性别',
    super_user tinyint     not null default 0 comment '是否是会员',
    phone      char(11) comment '手机号',
    email      varchar(40) comment '邮箱'
) engine = innodb,
  charset utf8mb4;

# 创建角色表
create table role
(
    id          int primary key auto_increment comment '角色id',
    name        varchar(40) not null default 'ordinary' comment '角色名',
    description varchar(255) comment '角色描述'
) engine innodb,
  charset utf8mb4;


# 创建权限表
create table permission
(
    id   int primary key auto_increment,
    name varchar(40)  not null comment '权限名',
    url  varchar(255) not null comment '可访问路径'
) engine innodb,
  charset utf8mb4;

create table user_role
(
    id      int primary key auto_increment,
    user_id int not null comment '用户id',
    role_id int not null comment '角色id'
) engine innodb,
  charset utf8mb4;


create table role_permission
(
    id      int primary key auto_increment,
    role_id int not null comment 'juesid',
    per_id  int not null comment '权限id'
) engine innodb,
  charset utf8mb4;

#===========================

insert into user(name, password, age, sex, super_user, phone, email)
VALUES ('xiaofei', '123456', 20, '男', 0, '17824255440', '1346574090@qq.com');


alter table user
    drop column salt;
alter table user
    add column salt varchar(255) not null default '小非' comment '密码加盐';


select id, name, password
from user
where name = 'xiaofei';


select ur.role_id, r.name, r.description
from user_role as ur
         left join role as r on ur.role_id = r.id
where ur.user_id = 1;


select p.id, p.name, p.url
from role_permission rp
         left join permission p on rp.per_id = p.id
where role_id = 1;

alter table user
    add column create_time datetime default now() comment '创建时间';




