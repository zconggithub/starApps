package com.starapp.dao;

import com.starapp.entity.SysRolesPermissionsKey;

public interface SysRolesPermissionsMapper {
    int deleteByPrimaryKey(SysRolesPermissionsKey key);

    int insert(SysRolesPermissionsKey record);

    int insertSelective(SysRolesPermissionsKey record);
}