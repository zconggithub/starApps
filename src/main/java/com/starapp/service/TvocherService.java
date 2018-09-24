package com.starapp.service;

import com.starapp.common.BackMessage;

import java.util.HashMap;

/**
 * @date:2018/9/2322:02
 * @author:Allen
 * @description:凭证表
 */
public interface TvocherService {

    //借贷总额是否相等
    HashMap check_Total_loan();
}
