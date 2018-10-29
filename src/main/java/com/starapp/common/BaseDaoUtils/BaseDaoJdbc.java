package com.starapp.common.BaseDaoUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.*;
import java.util.HashMap;

/**
 * @date:2018/9/2918:49
 * @author:Allen
 * @description:JDBC:数据库连接：测试大数据量数据导出
 */
public class BaseDaoJdbc {
    //数据库总数量
    public static  int rowCount= 0;



    /**取数SQL
     */
    public static String tvoucherSql="select ND_DM,KJQJ,PZZ,PZH,FLH,PZ_RQ,FLZY,KM_DM,JFJE,DFJE,WBBZ,WBJFJE,WBDFJE,SHR,ZDR,JZR,CN,FJS from t_voucher where 1=1";
    /**表头信息
     */
    public static String[] tvoucherSqlColumnHeader={"ND_DM","KJQJ","PZZ","PZH","FLH","PZ_RQ","FLZY","KM_DM","JFJE","DFJE","WBBZ","WBJFJE","WBDFJE","SHR","ZDR","JZR","CN","FJS" };

    /**
     * @descripion:获取数据库连接
     * @return Connection
     */
    public static Connection getConnection() {
        final String url = "jdbc:mysql://127.0.0.1:3306/allen?useUnicode=true&characterEncoding=utf8&useSSL=false";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "123";
        Connection conn = null;
        //指定连接类型
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取连接
        if (conn != null) {
            System.out.println("获取连接成功");
        } else {
            System.out.println("获取连接失败");
        }
        return conn;
    }

    public static void ExcelOutListBean(Connection conn,String sql,String[] table_Column,HttpServletResponse response,String excelName) throws IOException {
        //保存在客户端
        //设置response流信息的头类型，MIME码//application/msexcel
        response.setContentType("text/html;charset=UTF-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream(); // 输出流
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(excelName, "UTF-8") + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            System.out.println("[/response]UnsupportedEncodingException异常"+e.getMessage());
        }
        response.setCharacterEncoding("UTF-8");


        //关键语句，POI对大数据量指定，指定内存中缓存的最大行数
        SXSSFWorkbook wb = new SXSSFWorkbook(100000);
       /* 对上述语句的解释【在生成Workbook 时给工作簿一个内存数据存在条数，这样一旦这个Workbook 中数据量超过10000就会写入到磁盘中，减少内存的使用量来提高速度和避免溢出。】
        工作表对象*/
        Sheet sheet = wb.createSheet("excelName");
        //行对象
        Row nRow = null;
        //列对象
        Cell nCell = null;
        //String:此处是表字段,Object是对应的列值
        HashMap<String,Object> hashMap=new HashMap<>();
        // 开始时间
        Long begin = System.currentTimeMillis();
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
            /**弄清楚conn.prepareStatement（）方法中参数的意思【sql语句，游标，结果集操作】
             * ResultSet.CONCUR_READ_ONLY 结果集可进行的操作：只读
             * 使用rs.getMetaData()方法，该方法的返回类型是ResultSetMetaData，在这个类中调用getColumnCount()方法，即可得到字段个数。【目前表头字段是用的静态数组，待优化】
             */
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

                //此处处理业务逻辑，此处优化空间待提升
                rowCount++;
                if (rowCount % 100000 == 0) {
                    hashMap.clear();//待验证
                    System.out.println(" 写入到第  " + (rowCount / 100000) + " 批文件中！");
                    long end = System.currentTimeMillis();
                    System.out.println("当前数据量："+String.valueOf(rowCount));
                }
            }
          /*  os = new FileOutputStream(excelName);*/
            wb.write(os);

            System.out.println("最终数据量："+rowCount);
            Long end = System.currentTimeMillis();
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
            } if (os != null) {
                try {
                    os.flush();
                    os.close();
                    //【清除缓存文件】
                    wb.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }






















/*
    //表头信息kj_pzjk_valid_pzh_opp_detail
    public static final String opp_detail_Column[]={"模型代码","模型名称","年度",
            "期间","日期","凭证字","凭证号","分录号","科目代码","科目名称","标准科目代码","标准科目名称","摘要","借方金额","贷方金额"
            ,"关键字","条件类型","风险提示"};

    //表头信息kj_pzjk_valid_pzh_opp_header
    public static final String  header_Column[]={"年度","期间","日期","凭证字","凭证号","分录号","科目代码",
            "科目名称","标准科目代码","标准科目名称","摘要","借方金额","贷方金额","命中模型数"};
    // 【SQL语句】
    public static String header_SQL="SELECT ND_DM 年度, KJQJ 期间, PZ_RQ 日期, PZZ 凭证字, pzh 凭证号, FLH 分录号, km_dm 科目代码, client_subject_name 科目名称," +
            " model_subject_code 标准科目代码, model_subject_name 标准科目名称, FLZY 摘要, JFJE 借方金额, DFJE 贷方金额, match_count 命中模型数 FROM kj_pzjk_valid_pzh_opp_header order by nd_dm, kjqj, PZ_RQ, pzh, FLH";//表kj_pzjk_valid_pzh_opp_header
    public static String opp_detail_SQL="SELECT MODEL_CODE 模型代码, MODEL_NAME 模型名称, ND_DM 年度, KJQJ 期间, PZ_RQ 日期, PZZ 凭证字, pzh 凭证号," +
            " FLH 分录号, km_dm 科目代码, client_subject_name 科目名称, model_subject_code 标准科目代码, model_subject_name 标准科目名称," +
            " FLZY 摘要, JFJE 借方金额, DFJE 贷方金额, DESCRIPTION_CONDITION 关键字, condition_type 条件类型, tips 风险提示 FROM kj_pzjk_valid_pzh_opp_detail order by MODEL_CODE, MODEL_NAME, nd_dm, kjqj, PZ_RQ, pzh, FLH";//表SQL

    public static String opp_detail_Excel = "C:/Users/pw699qr/Desktop/opp_detail.xlsx";		//opp_detail_Excel

    public static String head_Excel = "C:/Users/pw699qr/Desktop/head_Excel.xlsx";		//opp_detail_Excel

    public static String Tgl_check_result_2016Excel = "C:/Users/pw699qr/Desktop/Tgl_check_result2016.xlsx";
    public static String Tgl_check_result_2017Excel = "C:/Users/pw699qr/Desktop/Tgl_check_result2017.xlsx";
    public static String Tgl_check_result_ColumnHeader[]={"ND_DM","KJQJ","PZJ","PZH","FLH","PZ_RQ","KM_DM","JFJE","DFJE","LOC_CHAR","CLIENT_CODE","SAP_PZBH","SAP_ZH"};
    public static String Tgl_check_result_Sql2016="select * from t_gl_check_result where client_code = 7106 and ND_DM = 2016";
    public static String Tgl_check_result_Sql2017="select * from t_gl_check_result where client_code = 7106 and ND_DM = 2017";*/
}
