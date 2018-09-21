/*
package com.starapp.controller;
import com.alibaba.fastjson.JSON;
import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.common.Excel2007Reader;
import com.starapp.dao.StandardSubjectMapper;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.service.StandardSubjectService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
*/
/**
 * @date:2018/9/1615:29
 * @author:Allen
 * @description:
 *//*

@RestController
@RequestMapping("/starApp")
public class StandardSubjectController {
    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private StandardSubjectService standardSubjectService;

    @RequestMapping("/companyMappingStandSubjectUpload")
    public String importStandardSubject() throws FileNotFoundException {
        log.info("[/standSubjectUpload]");
        BackMessage backMessage=null;
        //String filePath="C:\\Users\\pw699qr\\Desktop\\风险管理内部\\企业科目与标准科目.xlsx";
        String filePath="C:\\Users\\pw699qr\\Desktop\\风险管理内部\\第三家Company\\企业科目与标准科目.xlsx";
        File file=new File(filePath);
        InputStream inputStream=new FileInputStream(file);

        List<StandardSubject> listStandardSubject = new ArrayList<StandardSubject>();
        StandardSubject  standardSubject = null;
        long start_time = System.currentTimeMillis();
        Excel2007Reader excel2007Reader = new Excel2007Reader();
        List<List<String>> rowList = null;
        try {
            rowList = excel2007Reader.processSAXReadSheet(inputStream);//【记住Excel的表头处理】
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        log.info("Excel解析数据量："+rowList.size());
        for (int i = 0; i <rowList.size(); i++) {
            standardSubject=new  StandardSubject();
            List<String> str=rowList.get(i);
            standardSubject.setCompanyDm(str.get(0));//企业科目
            standardSubject.setCompanyDmName("eFlag".equals(str.get(1))?"#N/A":str.get(1));//企业科目代码名称
            standardSubject.setStandardDm(str.get(2));//标准科目代码
            standardSubject.setStandardDmName(str.get(3));//标准科目代码名称
            listStandardSubject.add(standardSubject);
        }

        int flag=standardSubjectService.batchInsertStandardSubject(listStandardSubject);
        if(flag>0){backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
        }else{
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"导入False");
        }
        return JSON.toJSONString(backMessage);
    }

}
*/
