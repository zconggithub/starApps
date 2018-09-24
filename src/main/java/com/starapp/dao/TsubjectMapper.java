package com.starapp.dao;

import com.starapp.entity.Tsubject;

public interface TsubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tsubject record);

    int insertSelective(Tsubject record);

    Tsubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tsubject record);

    int updateByPrimaryKey(Tsubject record);
}