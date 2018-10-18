**_`Allen:重点汇总`_**
http://super-csv.github.io/super-csv/downloading.html【API,关于对superCSV的详解】
参看博客：http://blog.51cto.com/ciyorecord/2104385【Super CSV 线程池并发处理大批量数据】
https://www.codejava.net/coding/super-csv-writing-pojos-to-csv-file-using-csvbeanwriter【很好的demo】

项目介绍：
Super CSV是一个用于处理CSV文件的Java开源项目。它完全围绕面向对象的思想进行设计，因此可以利用你的面向对象代码来使得处理CSV文件变得更加简易。它支持输入/输出类型转换、数据完整性校验，支持从任何地方以任何编码读写数据，只要提供相应的Reader与Writer对象。可配置分割符，空格符号和行结束符等。
链接：
项目主页： http://sourceforge.net/projects/supercsv

Super CSV是一个速度奇快、免费跨平台的 CSV 格式数据的读写库，可以方便的处理对象、Map、列表的读写操作，以及自动化的类型转换和数据检查功能。
http://supercsv.sourceforge.net/ 
[][][][][][][][**重点问题:**][][][][][][][][]
默认的情况下, 读和写使用逗号做分隔符(delimiter)，用双引号作为引用符(quotechar)，当遇到特殊情况，可以根据需要手动指定字符
CsvPreference这个类中的源码可以查看下,[**(一定要看其源码)**]其有4中固定的写好的分割符号
  public static final CsvPreference STANDARD_PREFERENCE = (new CsvPreference.Builder('"', 44, "\r\n")).build();
    public static final CsvPreference EXCEL_PREFERENCE = (new CsvPreference.Builder('"', 44, "\n")).build();
    public static final CsvPreference EXCEL_NORTH_EUROPE_PREFERENCE = (new CsvPreference.Builder('"', 59, "\n")).build();
    public static final CsvPreference TAB_PREFERENCE = (new CsvPreference.Builder('"', 9, "\n")).build();

quoteChar:引用符
delimeterChar:分隔符 [上述44表示逗号,59表示分号,9表示水平定位符号.可以对照ASCII查看]
 \r换行 相当于回车    \n是换行
 
 单词：Quote表示“引用”的意思
  
Java读 写CSV之SuperCSV【http://rensanning.iteye.com/blog/1552053】可作为参考

成功demo如下：
package com.starapp.common;

import com.starapp.entity.userBean;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @date:2018/10/1623:20
 * @author:Allen
 * @description:大数据量解析CSV--Demo
 */

public class BigDataParseCSV {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("C:\\Users\\pw699qr\\Desktop\\TEST.csv"), CsvPreference.STANDARD_PREFERENCE);
        //String[] header = beanReader.getHeader(true);//获取标头信息，即第一行（头行）
        // the name mapping provide the basis for bean setters
        final String[] nameMapping = new String[]{"id", "name", "age", "country"};
        System.out.println("inFile.getLineNumber() = " + beanReader.getLineNumber());
        System.out.println("inFile.getRowNumber() = " + beanReader.getRowNumber());
        final CellProcessor[] cellProcessor = getProcessors();


        userBean user_bean;
        while ((user_bean = beanReader.read(userBean.class, nameMapping, cellProcessor)) != null) {
            System.out.println(user_bean.getName());
        }


        // demo测试
      /*  Class user_bean = userBean.class;
        String setterName="setid";
        Class argumentType = Class.forName("java.lang.Integer");
        BigDataParseCSV.findSetterWithCompatible(user_bean,setterName,argumentType);*/
    }

    //CellProcessor：解析器，用来处理每列的数据，当然你也可以传入null，表示该列不做特殊处理
    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new UniqueHashCode(), // ID
                new NotNull(), // Name
                new Optional(), // Age
                new NotNull() // Country
        };
        return processors;
    }

}

package com.starapp.entity;

import java.util.Date;

/*
*
 * @date:2018/10/1710:29
 * @author:Allen
 * @description:
*/

public class userBean {

    private String id;
    private String name;
    private String age;
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" + id + "::" + name + "::" + age + "::" + country + "}";
    }
}

