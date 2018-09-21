package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.StandardSubject;

import java.util.List;

/**
 * @date:2018/9/1615:25
 * @author:Allen
 * @description:
 */
public interface StandardSubjectService {

    BackMessage batchInsertStandardSubject(List<List<String>> listListString);
}
