package com.starapp.controller;

import com.alibaba.fastjson.JSON;
import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
//import com.starapp.common.SaxExcelUtils;
import com.starapp.common.ParseExcel;
import com.starapp.entity.TInvoiceData;
import com.starapp.service.TInvoiceDataService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/9/522:36
 * @author:Allen
 * @description:数据解析
 */
@Controller
@RequestMapping("/starApp")
public class dataAnalysisController {
    private Logger log=Logger.getLogger(this.getClass());
    @Autowired
    private TInvoiceDataService tInvoiceDataService;

    @RequestMapping("/data_analysis")
    private String dataAnalysis(){

       // return "dataAnalysis";
       return "main";
    }

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile")
    private String uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        BackMessage backMessage=null;
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if(file.isEmpty()||file.getSize()==0){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"文件为空");
            return JSON.toJSONString(backMessage);
        }
        String fileName = file.getOriginalFilename();//文件名称
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"上传的文件格式不正确");
            return JSON.toJSONString(backMessage);
        }

        String file_size=String.valueOf((file.getSize()/1024/1024)) ;//文件大小

        System.out.println("【fileName】-->" + file_size+"M】");
//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
            //获取excel文件的io流
            InputStream inputStream = file.getInputStream();
        List<TInvoiceData> list = new ArrayList<TInvoiceData>();
        TInvoiceData tInvoiceData = null;
        long start_time = System.currentTimeMillis();
        ParseExcel excel2007Reader = new ParseExcel();

        List<List<String>> rowList = null;
        try {
            rowList = excel2007Reader.processSAXReadSheet(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        log.info("Excel解析数据量："+rowList.size());
        int commit=0;//提交次数
        for (int i = 1; i < rowList.size(); i++) {
            tInvoiceData = new TInvoiceData();
            List<String> str = rowList.get(i);
            tInvoiceData.setBillDate(str.get(0));
            tInvoiceData.setTransId(str.get(1));
            tInvoiceData.setFapiaoType(str.get(2));
            tInvoiceData.setGoodsName(str.get(3));
            tInvoiceData.setTotalAmt(new BigDecimal(str.get(4)));
            tInvoiceData.setNetAmt(new BigDecimal(str.get(5)));
            tInvoiceData.setTaxAmt(new BigDecimal(str.get(6)));
            tInvoiceData.setTaxRate(new BigDecimal(str.get(7)));
            tInvoiceData.setPid((str.get(8)));
            tInvoiceData.setGlcode((str.get(9)));
            tInvoiceData.setInstcode((str.get(10)));
            list.add(tInvoiceData);

                if(i%5000==0){
                    System.out.println("【j%5000】"+list.size());
                    int k=tInvoiceDataService.insertBatch(list);
                    commit=commit+1;
                    log.info("K值："+String.valueOf(k));
                    list.clear();
                }
        }
        if(5000*commit<rowList.size()){
            int kk=tInvoiceDataService.insertBatch(list);
            commit+=1;
            log.info("【最后提交次数】"+(commit));

        }
        return JSON.toJSONString(backMessage);

    }

    /**
     * @Author:allen
     * @description:解析Excel并封装List<TInvoiceData>
     *
     * */
 /*   public BackMessage importExcelData(InputStream in) {
        BackMessage backMessage=null;
        List<TInvoiceData> list = new ArrayList<TInvoiceData>();
        TInvoiceData tInvoiceData = null;
        long start_time = System.currentTimeMillis();
        Excel2007Reader excel2007Reader = new Excel2007Reader();

        List<List<String>> rowList = null;
        try {
            rowList = excel2007Reader.processSAXReadSheet(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < rowList.size(); i++) {
            tInvoiceData = new TInvoiceData();
            //* System.out.println(container);*//*
            List<String> str = rowList.get(i);
            tInvoiceData.setBillDate(str.get(0));
            tInvoiceData.setTransId(str.get(1));
            tInvoiceData.setFapiaoType(str.get(2));
            tInvoiceData.setGoodsName(str.get(3));
            tInvoiceData.setTotalAmt(new BigDecimal(str.get(4)));
            tInvoiceData.setNetAmt(new BigDecimal(str.get(5)));
            tInvoiceData.setTaxAmt(new BigDecimal(str.get(6)));
            tInvoiceData.setTaxRate(new BigDecimal(str.get(7)));
            tInvoiceData.setPid((str.get(8)));
            tInvoiceData.setGlcode((str.get(9)));
            tInvoiceData.setInstcode((str.get(10)));
            list.add(tInvoiceData);
            for (int j = 0; i <list.size() ; j++) {
                if(j%30000==0){
               int k=tInvoiceDataService.insertBatch(list);
               log.info("K值："+String.valueOf(k));
                }
                list.clear();
            }
        }

        return  backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
    }
*/
}
