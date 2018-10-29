
package com.starapp.common;
import com.starapp.entity.Tvoucher;
import com.starapp.service.TvocherService;
import com.starapp.service.impl.TvocherServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



/**
 * @date:2018/10/1623:20
 * @author:Allen
 * @description:大数据量解析CSV入库--Demo【单线程处理130万数据，每5W提交一次，耗费时间在149S左右】
 */

@Component
public  class BigDataParseTvoucherCsvUtil {

static int i=0;
    public static List<Tvoucher>  praseCsvToListBean(InputStream in,TvocherService tvocherService)  {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(in,"utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("BigDataParseTvoucherCsvUtil.praseCsvToListBean]+UnsupportedEncodingException"+e.getMessage());
        }
        Long start_time= System.currentTimeMillis();
       List<Tvoucher> listBean = new ArrayList<Tvoucher>();
        ICsvBeanReader csvBeanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);
        //【此处的表头信息要和bean中的属性对应，即bean中的属性是什么样，此处表头数组中的元素也要保持一致】
        final String[] header = {"nddm","kjqj","pzz","pzh","flh","pzrq","flzy","kmdm","jfje","dfje","wbbz","wbjfje","wbdfje","shr","zdr","jzr","cn","fjs"};
        //csv文件中标头信息。一定要注意标头信息的处理
        Tvoucher bean;
       // String[] header=new String[0];
        int commit_No=0;
//nddm,kjqj,pzz,pzh,flh,pzrq,flzy,kmdm,jfje,dfje,wbbz,wbjfje,wbdfje,shr,zdr,jzr,cn,fjs
        try {
             //header = csvBeanReader.getHeader(true);
           while ((bean = csvBeanReader.read(Tvoucher.class,header, getCellProcessors())) != null) {
                    i=i+1;
               listBean.add(bean);
               if(i%50000==0){
                   System.out.println("BigDataParseTvoucherCsvUtil.praseCsvToListBean"+"开始第"+commit_No+"提交。。。。");
                       tvocherService.insertBatchTvocher(listBean);
                   commit_No=i/50000;
                  // System.out.println("第"+commit_No+"次提交");
                   listBean.clear();
               }
            }
            //表示最后剩下的数据
            if(!listBean.isEmpty()){
                tvocherService.insertBatchTvocher(listBean);
                System.out.println("提交结束。。。。");
                System.out.println("第"+(commit_No+1)+"次提交");
            }
        } catch (IOException e) {
            System.out.println("BigDataParseTvoucherCsvUtil.表头信息获取失败"+e.getMessage());
        } finally {
            System.out.println("CSV解析的行数[i]:"+i);
            if (csvBeanReader != null) {
                try {
                    csvBeanReader.close();
                } catch (IOException e) {
                    System.out.println("BigDataParseTvoucherCsvUtil.csvBeanReader.read"+e.getMessage());
                }
            }
        }
        Long end_time=System.currentTimeMillis();
        System.out.println("BigDataParseCSV.main:[解析CSV并封装为Bean对象时间]："+(end_time-start_time)+"/ms");
        return listBean;
    }


/**
     * 单独处理分录号flh这个字段，因CSV文件中这个字符可能是‘2 ’,即后面加了一个空格【如webapp目录下的TestCSVDemoText.csv】*/


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
     * 空格处理
     */

    private static class TrimHandler extends  CellProcessorAdaptor{
        @Override
        public <T> T execute(Object o, CsvContext csvContext) {
            if(o.toString().length()!=4){
                System.out.println("TrimHandler.execute :csvContext ]"+csvContext);
                System.out.println("TrimHandler.execute "+o.toString());
            }
            return next.execute(o.toString().trim(),csvContext);
        }
    }

/**
     * @description:CellProcessor：解析器，用来处理每列的数据，当然你也可以传入null，表示该列不做特殊处理
     * 像下面的new ParseInt/ParseData,这些都是对每列数据的处理或格式化处理
     * @return
     */

    private static CellProcessor[] getCellProcessors() {
        return new CellProcessor[]{
               /* Optional(new ParseInt()), //整型数字，但只有这列有值的时候ParseInt处理器才会去处理这个值（其实就是该列可以为空）*/
                //空格处理
                new TrimHandler(),
                new ParseInt(),
                null,
                null,
                //此处值得一看ParseFlhToInteger()，处理单元格值的格式化，对应的Bean属性是一个Integer,而CSV中是一个带有空格的字符串
                new Optional(new ParseFlhToInteger()),
                //pzRq 日期格式格式化
                new ParseDate("d/mm/yyyy"),
                null,
                null,
                //jfje
                new Optional(new ParseJFje()),
                new Optional(new ParseJFje()),
                null,null,
                null,null,null,null,null,null
        };
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String filePath="C:\\Users\\pw699qr\\Desktop\\TEST.csv";
        //class<T>=Class.forName("com.starapp.entity.Tvoucher"==Tvoucher.Class
        List<Tvoucher> listBean=new ArrayList<>();

                TvocherService tvocherService=new TvocherServiceImpl();
        listBean= BigDataParseTvoucherCsvUtil.praseCsvToListBean(new FileInputStream(filePath),tvocherService);
        System.out.println("BigDataParseCsv.main"+listBean.size());

    }
}

    /*show VARIABLES like '%max_allowed_packet%';*/
