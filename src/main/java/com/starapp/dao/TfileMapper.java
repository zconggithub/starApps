package com.starapp.dao;

import com.starapp.entity.Tfile;

import java.util.List;

public interface TfileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tfile record);

    int insertSelective(Tfile record);

    Tfile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tfile record);

    int updateByPrimaryKey(Tfile record);

    List<Tfile> getAllDataSource();
}