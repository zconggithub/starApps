package com.starapp.controller;

import com.starapp.common.ExcelOutPutUtil;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tsubject;
import com.starapp.service.TsubjectService;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @date:2018/9/2511:41
 * @author:Allen
 * @description:Excel数据导出
 */
@Controller
@RequestMapping("/starApp")
public  class OutExcelController {

    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private TsubjectService tsubjectService;

    /**
     *
     * @param entity_name 导出实体数据的名称：少量数据导出
     * @return
     */
    @RequestMapping("/excelOut")
    public String excelOutPut(String entity_name,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {



        entity_name=request.getParameter("key");//获取问号传来的参数
        switch (entity_name){
            case "subject":
                List<Tsubject> listTsubject=tsubjectService.getAllTsubjectService();
                ExcelOutPutUtil.excleOut(listTsubject,"科目表",ExcelOutPutUtil.Subject_Row_ColumnHeader,response);
                return null;//若此处不是返回null的话，会报错：Caused by: java.lang.IllegalStateException: getOutputStream() has already been called for this response
            case "standard_subject":
                List<StandardSubject> listStandardSubject=tsubjectService.getAllStandard_subject();
                    ExcelOutPutUtil.excleOut(listStandardSubject,"标准科目与企业科目",ExcelOutPutUtil.StandardSubject_ColumnHeader,response);
                return null;
            case "Standard_subject_finance":
                List<StandardSubjectFinance> listStandard_subject_finance=tsubjectService.getAllStandard_subject_finance();
                ExcelOutPutUtil.excleOut(listStandard_subject_finance,"标准科目与财务报表",ExcelOutPutUtil.StandardSubjectFinace_ColumnHeader,response);
                return null;
            case "BS":
                List<?> listStandardBalance_sheet=tsubjectService.getAllBalance_sheet();
                ExcelOutPutUtil.excleOut(listStandardBalance_sheet,"资产负债表",ExcelOutPutUtil.Balance_sheet_ColumnHeader,response);
                return null;
            case "PL":
                List<?> listProlistLoss=tsubjectService.getAllBalance_sheet();
                ExcelOutPutUtil.excleOut(listProlistLoss,"利润表",ExcelOutPutUtil.Profit_loss_ColumnHeader,response);
                return null;
        }

        return "";
    }
}
