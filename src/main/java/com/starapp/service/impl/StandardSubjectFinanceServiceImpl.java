package com.starapp.service.impl;


import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.dao.StandardSubjectFinanceMapper;

import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.service.StandardSubjectFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/9/1611:50
 * @author:Allen
 * @description:标准科目与财务报表
 */
@Service
@Transactional
public class StandardSubjectFinanceServiceImpl implements StandardSubjectFinanceService {

    @Autowired
    private StandardSubjectFinanceMapper standardSubjectFinanceMappers;


    @Override
    public BackMessage batchInsertStandardSubjectFinance(List<List<String>> rowList) {
        BackMessage backMessage=null;
        List<StandardSubjectFinance> listStandardSubjectFinance = new ArrayList<StandardSubjectFinance>();
        StandardSubjectFinance standardSubjectFinance=null;
        for (int i = 0; i <rowList.size(); i++) {
            standardSubjectFinance=new  StandardSubjectFinance();
            List<String> str=rowList.get(i);
            standardSubjectFinance.setStandardSubjectsCode(str.get(0));//标准科目代码
            standardSubjectFinance.setStandardSubjectsName(str.get(1));//标准科目名称
            standardSubjectFinance.setFinanceTypeName(str.get(2));//财务报表类型
            standardSubjectFinance.setFinanceType("资产负债表".equals(str.get(2))?"1":"2");
            standardSubjectFinance.setFinanceNo(str.get(3));//财务报表栏次
            standardSubjectFinance.setFinanceNoName(str.get(4));//财务报表栏次名称
            listStandardSubjectFinance.add(standardSubjectFinance);
        }

        int successFlag=standardSubjectFinanceMappers.batchInsertStandardSubjectFinance(listStandardSubjectFinance);
        if(successFlag>0){
            backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
        }else{
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"导入失败");
        }
        return backMessage;
    }




}
