package com.starapp.dao;

import com.starapp.entity.TInvoiceData;
import com.starapp.entity.User;
import org.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login_check(HashMap<String,String> paramMap);

    int insertBatch(List<TInvoiceData> listTinvoiceData);
}