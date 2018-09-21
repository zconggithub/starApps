package com.starapp.dao;

import com.starapp.entity.StandardSubject;

import java.util.List;

public interface StandardSubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StandardSubject record);

    int insertSelective(StandardSubject record);

    StandardSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StandardSubject record);

    int updateByPrimaryKey(StandardSubject record);

    int batchInsertStandardSubject(List<StandardSubject> listStandardSubject);
}