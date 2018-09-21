package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.dao.StandardSubjectMapper;
import com.starapp.entity.StandardSubject;
import com.starapp.service.StandardSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/9/1615:26
 * @author:Allen
 * @description:
 */
@Service
@Transactional
public class StandardSubjectServiceImpl implements StandardSubjectService {

    @Autowired
    private StandardSubjectMapper standardSubjectMapper;

    @Override
    public BackMessage batchInsertStandardSubject(List<List<String>> rowList) {
        BackMessage backMessage=null;
        List<StandardSubject> listStandardSubject = new ArrayList<StandardSubject>();
            StandardSubject standardSubject=null;
        for (int i = 0; i <rowList.size(); i++) {
            standardSubject=new  StandardSubject();
            List<String> str=rowList.get(i);
            standardSubject.setCompanyDm(str.get(0));//企业科目
            standardSubject.setCompanyDmName("eFlag".equals(str.get(1))?"#N/A":str.get(1));//企业科目代码名称
            standardSubject.setStandardDm(str.get(2));//标准科目代码
            standardSubject.setStandardDmName(str.get(3));//标准科目代码名称
            listStandardSubject.add(standardSubject);
        }
            int successFlag=standardSubjectMapper.batchInsertStandardSubject(listStandardSubject);
        if(successFlag>0){
            backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
        }else{
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"导入失败");
        }
        return backMessage;
    }
}
