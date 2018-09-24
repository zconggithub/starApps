package com.starapp.dao;

import com.starapp.entity.Tvoucher;

import java.util.HashMap;

/**
 * 凭证表
 */
public interface TvoucherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tvoucher record);

    int insertSelective(Tvoucher record);

    Tvoucher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tvoucher record);

    int updateByPrimaryKey(Tvoucher record);

    /**
     * @description:借贷总额是否相等
     * @return
     */
    HashMap check_Total_loan();
}