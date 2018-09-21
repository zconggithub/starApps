package com.starapp.controller;

import com.alibaba.fastjson.JSON;
import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.common.Excel2007Reader;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.service.StandardSubjectFinanceService;
import com.starapp.service.StandardSubjectService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @date:2018/9/200:57
 * @author:Allen
 * @description:
 */
@Controller
@RequestMapping("/starApp")
public class ImportDataController {

    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private StandardSubjectService standardSubjectService;
    @Autowired
    private StandardSubjectFinanceService standardSubjectFinanceService;
    /**
     *
     * @return
     */
    @RequestMapping("/importExcel")
    public String importFile(Model model, @RequestParam("file")MultipartFile file,HttpServletRequest request) throws IOException {

       String dataSource_type=request.getParameter("dataSource_type");
        long start_Time=System.currentTimeMillis();
        BackMessage backMessage=null;
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if(file.isEmpty()||file.getSize()==0){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"文件为空");
            return "/datahandler/dataSourceManage";
        }
        String fileName = file.getOriginalFilename();//文件名称
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"上传的文件格式不正确");
            return "/datahandler/dataSourceManage";
        }
        String file_size=String.valueOf((file.getSize()/1024/1024)) ;//文件大小
        log.info("【"+fileName+"】-->" + file_size+"M】");
        InputStream inputStream = file.getInputStream();
        Excel2007Reader excel2007Reader = new Excel2007Reader();
        List<List<String>> rowList = null;
        try {
            rowList = excel2007Reader.processSAXReadSheet(inputStream);
        } catch (OpenXML4JException e) {
            log.info("SAX解析Excel失败"+e.getMessage());
          new BackMessage(EnumCodeMsg.Code301.getEnumName(),EnumCodeMsg.Code301.getEnumCode(),"OpenXML4JException",e.getMessage());
        } catch (SAXException e) {
            log.info("SAX解析Excel失败"+e.getMessage());
            new BackMessage(EnumCodeMsg.Code301.getEnumName(),EnumCodeMsg.Code301.getEnumCode(),"SAXException",e.getMessage());
        }
    String entityName="StandardSubject";
        //3,通过java.lang.Class类的forName()，方法获取
        try {
            StandardSubject standardSubject=new StandardSubject();
            Class className = Class.forName("com.starapp.entity."+entityName);
        } catch (ClassNotFoundException e) {
            new BackMessage(EnumCodeMsg.Code301.getEnumName(),EnumCodeMsg.Code301.getEnumCode(),"ClassNotFoundException",e.getMessage());
        }
        int importColumn_No;//导入字段数量
        switch (dataSource_type){
            case "StandardSubject":
                importColumn_No=4;//导入的字段为4个
               if(rowList.get(0).size()==4){
                backMessage= standardSubjectService.batchInsertStandardSubject(rowList);//标准科目与企业科目
                model.addAttribute("importSuccessFlag",backMessage.getObj());
                log.info("[{/importExcel}标准科目与企业科目导入： ]"+backMessage.getObj());
               }else {
                   model.addAttribute("importSuccessFlag","导入数据字段数量不匹配");
                   log.info("[{/importExcel}标准科目与企业科目导入： ]"+"导入数据字段数量不匹配");
               }
                break;
            case "StandardSubjectFinance":
                importColumn_No=6;//导入的字段为6个
                if(rowList.get(0).size()==4){
                backMessage= standardSubjectFinanceService.batchInsertStandardSubjectFinance(rowList);
                model.addAttribute("importSuccessFlag",backMessage.getObj());
                log.info("[{/importExcel}标准科目与财务导入： ]"+backMessage.getObj());
                }else{
                    model.addAttribute("importSuccessFlag","导入数据字段数量不匹配");
                    log.info("[{/importExcel}标准科目与企业科目导入： ]"+"导入数据字段数量不匹配");
                }
                break;
            case "Subject":log.info("Subject");
                break;
            default:  backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"程序无对应解析");

        }
        long end_Time=System.currentTimeMillis();
        log.info("导入时间： ]"+(end_Time-start_Time)+"ms");
        return "/datahandler/dataSourceManage";
    }


}
