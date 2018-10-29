package com.starapp.controller;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.common.ParseExcel;
import com.starapp.entity.StandardSubject;
import com.starapp.service.StandardSubjectFinanceService;
import com.starapp.service.StandardSubjectService;
import com.starapp.service.TvocherService;
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
import java.util.List;

/**
 * @date:2018/9/200:57
 * @author:Allen
 * @description:少量数据导入==》Excel
 */
@Controller
@RequestMapping("/starApp")
public class ImportDataController {

    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private StandardSubjectService standardSubjectService;
    @Autowired
    private StandardSubjectFinanceService standardSubjectFinanceService;
    @Autowired
    private TvocherService tvocherService;

    /**
     *
     * @return
     */
    @RequestMapping("/importExcel")
    public String importFile(Model model, @RequestParam("file")MultipartFile file,HttpServletRequest request) throws IOException {

       String dataSourceType=request.getParameter("dataSource_type");
        long start_Time=System.currentTimeMillis();
        BackMessage backMessage=null;
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if(file.isEmpty()||file.getSize()==0){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"文件为空");
            return "/datahandler/dataSourceManage";
        }
        //文件名称
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"上传的文件格式不正确");
            log.info(backMessage.toString());
            return "/datahandler/dataSourceManage";
        }
        //文件大小
        String fileSize=String.valueOf((file.getSize()/1024/1024)) ;
        log.info("【"+fileName+"】-->" + fileSize+"M】");
        InputStream inputStream = file.getInputStream();
       // Excel2007Reader excel2007Reader = new Excel2007Reader();
        ParseExcel excel2007Reader=new ParseExcel();
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
        switch (dataSourceType){
            case "StandardSubject":
                //导入的字段为4个
                importColumn_No=4;
               if(rowList.get(0).size()==4){
                   //标准科目与企业科目
                backMessage= standardSubjectService.batchInsertStandardSubject(rowList);
                model.addAttribute("importSuccessFlag",backMessage.getObj());
                log.info("[{/importExcel}标准科目与企业科目导入： ]"+backMessage.getObj());
               }else {
                   model.addAttribute("importSuccessFlag","导入数据字段数量不匹配");
                   log.info("[{/importExcel}标准科目与企业科目导入： ]"+"导入数据字段数量不匹配");
               }
                break;
            case "StandardSubjectFinance":
                //导入的字段为5个
                importColumn_No=5;
                if(rowList.get(0).size()==5){
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
            case "Tvoucher"://【目前测试130W数据是耗时间，其他插入还好】
                //导入的字段为5个
                importColumn_No=18;
                if(rowList.get(0).size()==18){
                    backMessage= tvocherService.insertBatchTvocherByExcel(rowList);
                    model.addAttribute("importSuccessFlag",backMessage.getObj());
                    log.info("[{/importExcel}凭证表大量数据100W导入测试： ]"+backMessage.getObj());
                }else{
                    model.addAttribute("importSuccessFlag","导入数据字段数量不匹配");
                    log.info("[{/importExcel}凭证表大量数据100W导入测试： ]"+"导入数据字段数量不匹配");
                }
                break;
            default:  backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"程序无对应解析");

        }
        long end_Time=System.currentTimeMillis();
        log.info("花费时间： ]"+(end_Time-start_Time)+"ms");
        return "/datahandler/dataSourceManage";
    }


}
