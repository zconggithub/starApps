package com.starapp.dao;

import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tsubject;

import java.util.List;

public interface TsubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tsubject record);

    int insertSelective(Tsubject record);

    Tsubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tsubject record);

    int updateByPrimaryKey(Tsubject record);

    List<Tsubject> getAllTsubject();//标准科目

    List<StandardSubject> getAllStandard_subject();//标准科目与企业科目

    List<StandardSubjectFinance> getAllStandard_subject_finance();//标准科目与财务报表

    List<?> getAllBalance_sheet();//资产负债表

    List<?> getAllProfit_loss();//利润表
}