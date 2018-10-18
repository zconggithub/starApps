package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tsubject;

import java.util.List;

/**
 * @date:2018/9/2511:48
 * @author:Allen
 * @description:
 */
public interface TsubjectService {

    List<Tsubject> getAllTsubjectService();

    List<StandardSubject> getAllStandard_subject();//标准科目与企业科目

    List<StandardSubjectFinance> getAllStandard_subject_finance();//标准科目与财务报表

    List<?> getAllBalance_sheet();//资产负债表

    List<?> getAllProfit_loss();//利润表
}
