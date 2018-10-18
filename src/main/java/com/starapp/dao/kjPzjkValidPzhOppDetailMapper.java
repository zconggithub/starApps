package com.starapp.dao;

import com.starapp.entity.kjPzjkValidPzhOppDetail;

public interface kjPzjkValidPzhOppDetailMapper {
    int deleteByPrimaryKey(Long kjPzjkValidPzhOppDetailId);

    int insert(kjPzjkValidPzhOppDetail record);

    int insertSelective(kjPzjkValidPzhOppDetail record);

    kjPzjkValidPzhOppDetail selectByPrimaryKey(Long kjPzjkValidPzhOppDetailId);

    int updateByPrimaryKeySelective(kjPzjkValidPzhOppDetail record);

    int updateByPrimaryKey(kjPzjkValidPzhOppDetail record);
}