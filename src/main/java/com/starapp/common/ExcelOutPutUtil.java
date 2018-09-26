package com.starapp.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.servlet.http.HttpServletResponse;

/**
 * @date:2018/9/1013:00
 * @author:Allen
 * @description:Excel导出工具
 */
public  class ExcelOutPutUtil {


    //表头对应的字段名

    public static final String Friend_ROW_1_Keys[] = {"BILL_DATE", "TRANS_ID", "FAPIAO_TYPE", "GOODS_NAME", "TOTAL_AMT", "NET_AMT", "TAX_AMT", "TAX_RATE", "PID", "INSTCODE"};

    public static final String Subject_Row_ColumnHeader[] = {"ND_DM", "KM_DM", "KMMC", "KMLX", "KMFX", "FJKM_DM"};//科目表
    public static final String Balance_sheet_ColumnHeader[] = {"REPORT_TYPE", "LINE_NO", "LINE_NO_NAME", "QCYE", "QMYE"};//BS，资产负债表

    public static final String Profit_loss_ColumnHeader[] = {"REPORT_TYPE", "LINE_NO", "LINE_NO_NAME", "FS"};//PL，利润表

    public static final String StandardSubject_ColumnHeader[] = {"STANDARD_DM","STANDARD_DM_NAME","COMPANY_DM","COMPANY_DM_NAME"};//标准科目与企业科目

    public static final String StandardSubjectFinace_ColumnHeader[] = {"STANDARD_SUBJECTS_CODE","STANDARD_SUBJECTS_NAME","FINANCE_NO","FINANCE_TYPE","FINANCE_NO_NAME"};//标准科目与财务报表



    //表头对应的实体字段名

    /**
     * @param listData   数据库查询出来的数据集h
     * @param fileName   文件名
     * @param headColumn 表头信息
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws WriteException
     * @throws IOException
     */
    public static void excleOut(List<?> listData, String fileName, String headColumn[], HttpServletResponse response){

//保存在客户端
        //设置response流信息的头类型，MIME码
        response.setContentType("text/html;charset=UTF-8");//application/msexcel
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        } catch (UnsupportedEncodingException e) {
            System.out.println("[/response]UnsupportedEncodingException异常"+e.getMessage());
        }
        response.setCharacterEncoding("UTF-8");

        //通过Response把数据以Excel格式保存
        WritableWorkbook book = null;// 编写WritableWorkbook对象，该对象代表了excel对象
        WritableSheet sheet = null;

        // * 根据传进来的file对象创建可写入的Excel工作薄到客户端
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            book = Workbook.createWorkbook(os);//输出到客户端时候//将创建的Excel对象利用二进制流的形式强制输出到客户端去
            sheet = book.createSheet(fileName, 0);// 获取sheet对象，sheetName为工作表的名称
        } catch (IOException e) {
            System.out.println("response :out[IOException异常]" + e.getMessage());
        }


        WritableCellFormat wcf = new WritableCellFormat();
        try {
            //创建单元格样式
            // wcf.setBackground(jxl.format.Colour.GREEN);// 背景颜色
            wcf.setAlignment(Alignment.CENTRE); // 平行居中
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
        } catch (WriteException e) {
            e.printStackTrace();
        }

        // 判断一下表头数组是否有数据，并将表头数据添加到sheet中
        // String[] columns=ExcelOutPutUtil.headColumn;//表头数据
        try {
        if (headColumn != null && headColumn.length > 0) {
            // 循环写入表头
            for (int i = 0; i < headColumn.length; i++) {
                    sheet.addCell(new Label(i, 0, headColumn[i], wcf));
            }
        } } catch (WriteException e) {
            System.out.println("[/表头数据写入Excel]：WriteException异常");
        }
        //判断集合是否有数据
        if (listData != null && listData.size() > 0) {
            // 对集合进行遍历
            for (int i = 0; i < listData.size(); i++) {
                // 转换成map集合,场景：联合查询出来的字段
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) listData.get(i);
                // Map<String, Object> maps =listData.stream().collect(Collectors.toMap(Object::toString,a ->a,(k1,k2)->k1));
                // 循环输出map中的子集：既列值
                try{
                for (int j = 0; j < headColumn.length; j++) {
                    sheet.addCell(new Label(j, i + 1, String.valueOf(map
                            .get(headColumn[j]) == null ? "" : map
                            .get(headColumn[j])), wcf));
                }} catch (WriteException e) {
                   System.out.println("[/list数据写入Excel]：WriteException异常");
                }
            }
        }

        //  Label la  = new Label(j, i+1, (fi[j].get(ob)) == null ? "" : String.valueOf(fi[j].get(ob)),wcf);

        try {
            book.write();
            book.close();
        } catch (Exception e) {
          System.out.println("[/Exception:] book.close异常");
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

    }
}


