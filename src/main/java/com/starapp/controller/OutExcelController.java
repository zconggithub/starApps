package com.starapp.controller;

import com.starapp.common.BaseDaoUtils.BaseDaoJdbc;
import com.starapp.common.BigDataExportTvoucherCsvUtil;
import com.starapp.common.BigDataParseTvoucherCsvUtil;
import com.starapp.common.ExcelOutPutUtil;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tsubject;
import com.starapp.entity.Tvoucher;
import com.starapp.service.TsubjectService;
import com.starapp.service.TvocherService;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.List;

/**
 * @date:2018/9/2511:41
 * @author:Allen
 * @description:Excel数据导出==少量数据导出||大数据量【130W测试时间：】
 */
@Controller
@RequestMapping("/starApp")
public  class OutExcelController {

    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    private TsubjectService tsubjectService;
    @Autowired
    private TvocherService tvocherService;

    /**
     *
     * @param entity_name 导出实体数据的名称：少量数据导出
     * @return
     */
    @RequestMapping("/excelOut")
    public String excelOutPut(String entity_name,HttpServletRequest request,HttpServletResponse response) throws IOException {


//获取问号传来的参数
        entity_name=request.getParameter("key");
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
            /**
             * 408186数据导出测试【22040ms】//1038818==》56524ms
             */
            case "Tvoucher":
                BaseDaoJdbc.ExcelOutListBean(BaseDaoJdbc.getConnection(),BaseDaoJdbc.tvoucherSql,BaseDaoJdbc.tvoucherSqlColumnHeader,response,"凭证表");
                return null;
                default:
                    return null;
        }

        //return "";
    }

    /**
     * csv格式数据导出：目前测试138W数据导出是49秒
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/OutCsv")
    public void ExportCsv(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Long star_Time=System.currentTimeMillis();
        System.out.println("OutExcelController.ExportCsv"+"开始导出CSV。。。");
        Long end_Time=null;
        response.setContentType("text/csv");
        String reportName = "editable.csv";
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);
        Writer writer = new OutputStreamWriter(response.getOutputStream(), "UTF8");
        ICsvBeanWriter beanWriter =  new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
       beanWriter.writeHeader(BigDataExportTvoucherCsvUtil.TvoucherCsvHeader);
        try{
            List<Tvoucher>  objs =tvocherService.getAllTvoucher();
            for(Tvoucher item : objs) {
                beanWriter.write(item, BigDataExportTvoucherCsvUtil.TvoucherCsvHeader);
            }
            beanWriter.flush();

            writer.close();
            end_Time=System.currentTimeMillis();
            System.out.println("OutExcelController.ExportCsv[:/ ]"+"csv格式数据导出结束。。。");
        }catch(Exception e){
            log.error("interruption de l'export !",e);
        } finally {
            if( beanWriter != null ) {
                beanWriter.close();
                System.out.println("OutExcelController.ExportCsv[/导出CSV格式时间： ]"+(end_Time-star_Time)/1000+"s");
            }
        }

    }

    }

