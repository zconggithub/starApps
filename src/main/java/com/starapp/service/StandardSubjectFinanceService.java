package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.StandardSubjectFinance;

import java.util.List;

/**
 * @date:2018/9/1611:48
 * @author:Allen
 * @description:
 */
public interface StandardSubjectFinanceService {

   BackMessage batchInsertStandardSubjectFinance(List<List<String>> listListString);
}
