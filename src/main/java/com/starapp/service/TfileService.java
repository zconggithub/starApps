package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.Tfile;

import java.util.List;

/**
 * @date:2018/9/2118:39
 * @author:Allen
 * @description:添加文件：此处是数据元部分
 */
public interface TfileService {

    BackMessage  addTfile(Tfile tfile);

    List<Tfile> getAllDataSource();
}
