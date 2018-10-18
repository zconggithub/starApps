package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.Tvoucher;

import java.util.HashMap;
import java.util.List;

/**
 * @date:2018/9/2322:02
 * @author:Allen
 * @description:凭证表
 */
public interface TvocherService {

    //借贷总额是否相等
    HashMap check_Total_loan();
    //导入CSV文件，即插入数据库
    BackMessage insertBatchTvocher(List<Tvoucher> listBean);
}
