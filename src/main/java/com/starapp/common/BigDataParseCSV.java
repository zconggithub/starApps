package com.starapp.common;

import com.starapp.entity.Tvoucher;
import com.starapp.entity.userBean;
import org.apache.poi.ss.formula.functions.T;
import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date:2018/10/1623:20
 * @author:Allen
 * @description:大数据量解析CSV--Demo
 */

public class BigDataParseCSV {


    public static List<Object>  praseCsvToListBean(InputStream in) throws IOException  {
        InputStreamReader inputStreamReader = new InputStreamReader(in);
      Long start_time= System.currentTimeMillis();
        List<Object> listBean = new ArrayList<Object>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);
        //【此处的表头信息要和bean中的属性对应，即bean中的属性是什么样，此处表头数组中的元素也要保持一致】
        //final String[] header = {"nddm","kjqj","pzz","pzh","flh","pzrq","flzy","kmdm","jfje","dfje","wbbz","wbjfje","wbdfje","shr","zdr","jzr","cn","fjs"};
        String[] header= csvBeanReader.getHeader(true);//csv文件中标头信息。一定要注意标头信息的处理
        System.out.println("【行数："+csvBeanReader.getRowNumber());
        Tvoucher tvoucher;
        int i=0;
        try {
           while ((tvoucher = csvBeanReader.read(Tvoucher.class, header, getCellProcessors())) != null) {
                System.out.println("【BigDataParseCSV:】 " + tvoucher);
                    i=i+1;
                listBean.add(tvoucher);
            }
          /*  while ((bean = csvBeanReader.read(T.class, header, getCellProcessors())) != null) {
                System.out.println("deserialized " + bean);
                i=i+1;
                listBean.add(bean);
            }*/
        } finally {
            System.out.println("CSV解析的行数[i]:"+i);
            if (csvBeanReader != null) {
                csvBeanReader.close();
            }
        }
        Long end_time=System.currentTimeMillis();
        System.out.println("BigDataParseCSV.main:[解析CSV并封装为Bean对象时间]："+(end_time-start_time)+"/ms");
        return listBean;
    }
    //单独处理分录号flh这个字段，因CSV文件中这个字符可能是‘2 ’,即后面加了一个空格【如webapp目录下的TestCSVDemoText.csv】
    private static class ParseFlhToInteger extends  CellProcessorAdaptor{
        @Override
        public <T> T execute(Object o, CsvContext csvContext) { ;
            return next.execute(new Integer(o.toString().trim()),csvContext);

        }
    }
    //单独处理分录号flh这个字段，因CSV文件中这个字符可能是‘0.01 ’,即后面加了一个空格【如webapp目录下的TestCSVDemoText.csv】
    private static class ParseJFje extends  CellProcessorAdaptor{
        @Override
        public <T> T execute(Object o, CsvContext csvContext) {
            return next.execute(new BigDecimal(o.toString().trim()),csvContext);
        }
    }

    /**
     * @description:CellProcessor：解析器，用来处理每列的数据，当然你也可以传入null，表示该列不做特殊处理
     * 像下面的new ParseInt/ParseData,这些都是对每列数据的处理或格式化处理
     * @return
     */
    private static  CellProcessor[] getCellProcessors() {
        return new CellProcessor[]{
                //@formatter:off
                null,
                new ParseInt(),
                null,
                null,
                new Optional(new ParseFlhToInteger()),//此处值得一看，处理单元格值的格式化，对应的Bean属性是一个Integer,而CSV中是一个带有空格的字符串
                new ParseDate("d/mm/yyyy"),//pzRq 日期格式格式化
                null,
                null,
                new Optional(new ParseJFje()),//jfje
                new Optional(new ParseJFje()),//dfje
                null,null,
                null,null,null,null,null,null
        };
    }

    public static void main(String[] args) throws IOException {
        String filePath="C:\\Users\\pw699qr\\Desktop\\TEST.csv";
        BigDataParseCSV.praseCsvToListBean(new FileInputStream(filePath));
    }
}

