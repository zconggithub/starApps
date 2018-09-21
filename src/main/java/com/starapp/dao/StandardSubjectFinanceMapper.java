package com.starapp.dao;


import com.starapp.entity.StandardSubjectFinance;

import java.util.List;

public interface StandardSubjectFinanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StandardSubjectFinance record);

    int insertSelective(StandardSubjectFinance record);

    StandardSubjectFinance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StandardSubjectFinance record);

    int updateByPrimaryKey(StandardSubjectFinance record);

    int batchInsertStandardSubjectFinance(List<StandardSubjectFinance> listStandardSubjectFinance);
}