/*
package com.starapp.controller;

import com.alibaba.fastjson.JSON;
import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.common.Excel2007Reader;
import com.starapp.entity.StandardSubjectFinance;

import com.starapp.service.StandardSubjectFinanceService;
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
 * @date:2018/9/1612:30
 * @author:Allen
 * @description:
 *//*

@RestController
@RequestMapping("/starApp")
public class StandardSubjectFinanceController {

    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private StandardSubjectFinanceService standardSubjectFinanceService;

    @RequestMapping("/standSubjectUpload")
    public String importStandardSubjectFinance() throws FileNotFoundException {
        log.info("[/standSubjectUpload]");
        BackMessage backMessage=null;
        //String filePath="C:\\Users\\pw699qr\\Desktop\\风险管理内部\\标准科目与财务报表.xlsx";
        String filePath="C:\\Users\\pw699qr\\Desktop\\风险管理内部\\第三家Company\\标准科目与财务报表.xlsx";
        File file=new File(filePath);
        InputStream inputStream=new FileInputStream(file);

        List<StandardSubjectFinance> listStandardSubjectFinance = new ArrayList<StandardSubjectFinance>();
        StandardSubjectFinance  standardSubjectFinance = null;
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
    int flag=standardSubjectFinanceService.batchInsertStandardSubjectFinance(listStandardSubjectFinance);
        if(flag>0){backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
        }
        return JSON.toJSONString(backMessage);
    }





}
*/
