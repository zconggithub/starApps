package com.starapp.common;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.xerces.xs.datatypes.ObjectList;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @date:2018/9/2713:28
 * @author:Allen
 * @description:大数据量导出【目前测试20W---》3s】
 */
public class JDBC_Export {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        final String url = "jdbc:mysql://192.168.1.128:3306/dataannalysis?useUnicode=true&characterEncoding=utf8";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "EySystem";
        Connection conn = null;
        Class.forName(name);//指定连接类型
        conn = DriverManager.getConnection(url, user, password);//获取连接
        if (conn != null) {
            System.out.println("获取连接成功");
            getData_No(conn,opp_detail_SQL,opp_detail_Column,opp_detail_Excel);//【备注：第一个参数conn连接不用动，第二个参数是：SQL语句{}，第三个参数是：表头信息{}。第四个参数是对应的表名。后面三个参数跟随业务变动】
           // getData_No(conn,header_SQL,header_Column,head_Excel);
            System.out.println("该数据量" + String.valueOf(rowCount));
        } else {
            System.out.println("获取连接失败");
        }

    }


    /**
     *
     * @param conn sql连接
     * @param sql  传入sql语句
     * @param table_Column  表头信息，即作为数组传入
     * @throws IOException
     */
    public static void getData_No(Connection conn,String sql,String[] table_Column,String ExcelFileName) throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);			//关键语句
        Sheet sheet = wb.createSheet();		//工作表对象
        Row nRow = null;		//行对象
        Cell nCell = null;		//列对象
        HashMap<String,Object> hashMap=new HashMap<>();//String:此处是表字段
        // 开始时间
        Long begin = new Date().getTime();
        PreparedStatement pst = null;
        ResultSet rs = null;
       // String getDataSet_Sql = "SELECT * FROM kj_pzjk_valid_pzh_opp_detail ORDER by pzh";//获取数据集合
        //[将表头插入]
        nRow=sheet.createRow(0);
        for (int j = 0; j < table_Column.length; j++) {
            nCell= nRow.createCell(j);
            nCell.setCellValue(table_Column[j]);
        }
        try {
            pst = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pst.setFetchSize(Integer.MIN_VALUE);
            pst.setFetchDirection(ResultSet.FETCH_REVERSE);
            rs = pst.executeQuery();
            while (rs.next()) {
                nRow=sheet.createRow(rowCount+1);
                                for (int j = 0; j < table_Column.length; j++) {
                                    hashMap.put(table_Column[j],rs.getString(table_Column[j]));
                                    nCell= nRow.createCell(j);
                                    nCell.setCellValue(rs.getString(table_Column[j]));
                                }

                //此处处理业务逻辑
                rowCount++;
                if (rowCount % 50000 == 0) {
                            hashMap.clear();//待验证
                    System.out.println(" 写入到第  " + (rowCount / 50000) + " 批文件中！");
                    long end = System.currentTimeMillis();
                    System.out.println("当前数据量："+String.valueOf(rowCount));
                }
                }
            FileOutputStream fOut = new FileOutputStream(ExcelFileName);
            wb.write(fOut);
            fOut.flush();		//刷新缓冲区
            fOut.close();
            System.out.println("最终数据量："+rowCount);
            Long end = new Date().getTime();
            System.out.println("导出时间："+(end-begin)+"ms");
        } catch (SQLException e1) {
            e1.printStackTrace();
             } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (pst != null) {
                            pst.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                        }
    }

    //表头信息kj_pzjk_valid_pzh_opp_detail
    public static final String opp_detail_Column[]={"KJ_PZJK_VALID_PZH_OPP_DETAIL_ID","kj_pzjk_valid_pzh_detail_id","OPP_SUBJECT_DETAIL_ID",
            "ND_DM","KJQJ","PZZ","pzh","FLH","PZ_RQ","combine_subject_code","km_dm","client_subject_name","client_subject_name2","FLZY","JFJE","DFJE","model_subject_code"
            ,"model_subject_name","MODEL_CODE","MODEL_NAME","DESCRIPTION_CONDITION","condition_type","tips"};

    //表头信息kj_pzjk_valid_pzh_opp_header
    public static final String  header_Column[]={"KJ_PZJK_VALID_PZH_OPP_HEADER_ID","ND_DM","KJQJ","PZZ","pzh","FLH","PZ_RQ","combine_subject_code",
            "km_dm","client_subject_name","client_subject_name2","FLZY","JFJE","DFJE","model_subject_code","model_subject_name","match_count"};
    // 【SQL语句】
    public static String header_SQL="SELECT * FROM kj_pzjk_valid_pzh_opp_header ORDER BY pzh";//表kj_pzjk_valid_pzh_opp_detail
    public static String opp_detail_SQL="SELECT * FROM kj_pzjk_valid_pzh_opp_detail ORDER by pzh";//表SQL
    public static  int rowCount= 0;//数据库总数量

    public static String opp_detail_Excel = "C:/Users/pw699qr/Desktop/opp_detail.xlsx";		//opp_detail_Excel

    public static String head_Excel = "C:/Users/pw699qr/Desktop/head_Excel.xlsx";		//opp_detail_Excel


}

/**
 * @description:导出到客户端
 * @param listData
 * @param fileName
 * @param headColumn
 * @param response
 */
   /* public static void excleOut(List<?> listData, String fileName, String headColumn[], HttpServletResponse response){

    }*/