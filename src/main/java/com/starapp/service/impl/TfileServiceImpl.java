package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.dao.TfileMapper;
import com.starapp.entity.Tfile;
import com.starapp.service.TfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date:2018/9/2118:40
 * @author:Allen
 * @description:数据元添加
 */
@Service
public class TfileServiceImpl implements TfileService {
    @Autowired
    private TfileMapper tfileMapper;

    @Override
    public BackMessage addTfile(Tfile tfile) {

        BackMessage backMessage=null;
        int successFlag=tfileMapper.insert(tfile);

        if(successFlag>0){
            backMessage= new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"提交OK");
        }else{
            backMessage= new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"提交失败");
        }
        return backMessage;
    }

    @Override
    public List<Tfile> getAllDataSource() {

        return tfileMapper.getAllDataSource();
    }
}
