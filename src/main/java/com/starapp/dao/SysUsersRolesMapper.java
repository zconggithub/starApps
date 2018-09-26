package com.starapp.dao;

import com.starapp.entity.SysUsersRolesKey;

public interface SysUsersRolesMapper {
    int deleteByPrimaryKey(SysUsersRolesKey key);

    int insert(SysUsersRolesKey record);

    int insertSelective(SysUsersRolesKey record);
}