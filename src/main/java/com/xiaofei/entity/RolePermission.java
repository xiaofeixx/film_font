package com.xiaofei.entity;

import lombok.Data;

@Data
public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer perId;
}