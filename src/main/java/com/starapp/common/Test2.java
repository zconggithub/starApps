/*
package com.starapp.common;//单文件示例代码，转换结果在List<List<String>> container

import com.starapp.entity.TInvoiceData;
import com.starapp.service.TInvoiceDataService;
import com.starapp.service.impl.TInvoiceDataServiceImpl;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import sun.rmi.log.LogInputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class Test2 {

    private Logger log=Logger.getLogger(this.getClass());
    public List processOneSheet() throws Exception {
        long startime = System.currentTimeMillis();
        File file = new File("C:\\Users\\pw699qr\\Desktop\\bb.xlsx");
        OPCPackage pkg = OPCPackage.open(file);
        XSSFReader r = new XSSFReader(pkg);
        // @param sheetId  sheetId为要遍历的sheet索引，从1开始，1-3
        InputStream in = r.getSheet("rId" + 1);//InputStream in =  r.getSheet("rId1");
        //查看转换的xml原始文件，方便理解后面解析时的处理,
        // 注意：如果打开注释，下面parse()就读不到流的内容了
      //  Test2.streamOut(in);

        //下面是SST 的索引会用到的
        SharedStringsTable sst = r.getSharedStringsTable();
       // sst.writeTo(System.out);

        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        List<List<String>> container = new ArrayList<>();
        Myhandler myhandler = new Myhandler(sst, container);
        parser.setContentHandler(myhandler);//new Myhandler(sst,container)
        InputSource inputSource = new InputSource(in);
        parser.parse(inputSource);

        in.close();

      Test2.printContainer(container);
        System.out.println("Sax解析Excel时间" + (System.currentTimeMillis() - startime)/1000 + "s");
//测试数据插入
      List<TInvoiceData> list = new ArrayList<TInvoiceData>();
        TInvoiceData tInvoiceData = null;
        Long getStar_time=System.currentTimeMillis();//封装时间
 */
/*       for (int i = 1; i < container.size(); i++) {
            tInvoiceData = new TInvoiceData();
            //* System.out.println(container);*//*
*/
/*
            List<String> str = container.get(i);
            tInvoiceData.setBillDate(str.get(0));
            tInvoiceData.setTransId(str.get(1));
            tInvoiceData.setFapiaoType(str.get(2));
            tInvoiceData.setGoodsName(str.get(3));
            tInvoiceData.setTotalAmt(new BigDecimal(str.get(4)));
            tInvoiceData.setNetAmt(new BigDecimal(str.get(5)));
            tInvoiceData.setTaxAmt(new BigDecimal(str.get(6)));
            tInvoiceData.setTaxRate(new BigDecimal(str.get(7)));
            tInvoiceData.setPid((str.get(8)));
            tInvoiceData.setGlcode((str.get(9)));
            tInvoiceData.setInstcode((str.get(10)));
            list.add(tInvoiceData);
         /*   if(i%29999==0){
                tInvoiceDataService.insertBatch(list);
                list.clear();
                System.out.println("[【每1W次提交一次//list】]"+list.size()+"此时i=["+i+"]");
            }*//*

       */
/*   if(list.size()==1155||list.size()==1101){
              System.out.println(tInvoiceData.toString());
          }*//*

          */
/*  if(list.size()%10000==0){
                tInvoiceDataService.insertBatch(list);
                list.clear();
            }*//*

     //   }
       // System.out.println("【封装时间" + (System.currentTimeMillis() - getStar_time)/1000 + "s】 [/封装大小：]" + list.size());

        return  list;

    }

    public static void printContainer(List<List<String>> container) {
        for(List<String> stringList:container)
        {
            for(String str:stringList)
            {
                System.out.printf("%15s",str+" | ");
            }
            System.out.println("");
        }
    }

    //读取流，查看文件内容
    public static void streamOut(InputStream in) throws Exception{
        byte[] buf = new byte[1024];
        int len;
        while ((len=in.read(buf))!=-1){
            System.out.write(buf,0,len);
        }
    }


}

class Myhandler extends DefaultHandler {
    private Logger log=Logger.getLogger(this.getClass());

    //取SST 的索引对应的值
    private SharedStringsTable sst;

    public void setSst(SharedStringsTable sst) {
        this.sst = sst;
    }

    //解析结果保存
    private List<List<String>> container;
   // SAX解析XML
        public  Myhandler(SharedStringsTable sst, List<List<String>> container) {
        this.sst = sst;
        this.container = container;//数据集合
        System.out.println(container.size());
    }

    private String lastContents;

    //有效数据矩形区域,A1:Y2
    private String dimension;

    //根据dimension得出每行的数据长度
    private int longest;

    //上个有内容的单元格id，判断空单元格
    private String lastRowid;


    //行数据保存
    private List<String> currentRow;

    //单元格内容是SST 的索引
    private boolean isSSTIndex=false;

    private boolean cellNull=false;// 这个就是添加来判断是否为空单元格的<c r="C1" s="10" />

    private String current_Rowid;//当前单元格坐标

    @Override
    */
/**
     * 节点解析开始调用
     * @param uri : 命名空间的uri
     * @param localName : 标签的名称
     * @param qName : 带命名空间的标签名称
     *//*

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("startElement:"+qName);

        if (qName.equals("dimension")){
            dimension = attributes.getValue("ref");
            longest = covertRowIdtoInt(dimension.substring(dimension.indexOf(":")+1) );
        }
        //行开始
        if (qName.equals("row")) {
            currentRow = new ArrayList<>();
        }
        if (qName.equals("c")) {
            String rowId = attributes.getValue("r");
            String style = attributes.getValue("s");//qname:s表示样式索引。s是样式的简称，是一个数字，即样式索引

            current_Rowid=rowId;//赋给当前单元格坐标


            String dataTypetts=attributes.getValue("t");//获取数据类型
           // log.info("【数据格式：】"+dataTypetts);
            //空单元判断，添加空字符到list
            if (lastRowid!=null)
            {
                int gap = covertRowIdtoInt(rowId)-covertRowIdtoInt(lastRowid);

                for(int i=0;i<gap-1;i++)
                {
                    cellNull=true;
                    currentRow.add("");
                }
            }else{
                //第一个单元格可能不是在第一列
                if (!"A1".equals(rowId))
                {
                    for(int i=0;i<covertRowIdtoInt(rowId)-1;i++)
                    {
                        currentRow.add("");
                    }
                }
            }
            lastRowid = rowId;
           // isSSTIndex=true;

            //判断单元格的值是SST 的索引，不能直接characters方法取值
            if (attributes.getValue("t")!=null && attributes.getValue("t").equals("s"))
            {
                isSSTIndex = true;
            }else{
                isSSTIndex = false;
            }
        }



    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("endElement:"+qName);

        //行结束,存储一行数据
        if (qName.equals("row")) {

            //判断最后一个单元格是否在最后，补齐列数
            if(covertRowIdtoInt(lastRowid)<longest){
                for(int i=0;i<longest- covertRowIdtoInt(lastRowid);i++)
                {
                    currentRow.add("");
                }
            }
            container.add(currentRow);
            lastRowid=null;
        }



        //单元格内容标签结束，characters方法会被调用处理内容
        if (qName.equals("c")) {
            //单元格的值是SST 的索引
            if (isSSTIndex){
                String sstIndex = lastContents.toString();
                try {
                    int idx = Integer.parseInt(sstIndex);
                    XSSFRichTextString rtss = new XSSFRichTextString(
                            sst.getEntryAt(idx));
                    lastContents = rtss.toString();//#【rtss是具体的单元格的值】
                    currentRow.add(lastContents);//将单元格的值放到当前行中。【】【】【】【】【此处要优化】【】【】【】【】
                } catch (NumberFormatException ex) {
                    System.out.println(lastContents);
                }
            }else {

                currentRow.add(lastContents);
            }

        } if ("C1".equals(current_Rowid)){
            System.out.println("asljflasgdlajsfhdakhfjdakfdsa");
        }



    }


    */
/**
     * 获取element的文本数据
     *//*

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        lastContents = new String(ch, start, length);
        log.info(lastContents);
    }

    */
/**
     * 列号转数字   AB7-->28 第28列
     * @param rowId
     * @return
     *//*

    public static int covertRowIdtoInt(String rowId){
        int firstDigit = -1;
        for (int c = 0; c < rowId.length(); ++c) {
            if (Character.isDigit(rowId.charAt(c))) {
                firstDigit = c;
                break;
            }
        }
        //AB7-->AB
        //AB是列号, 7是行号
        String newRowId = rowId.substring(0,firstDigit);
        int num = 0;
        int result = 0;
        int length = newRowId.length();
        for(int i = 0; i < length; i++) {
            //先取最低位，B
            char ch = newRowId.charAt(length - i - 1);
            //B表示的十进制2，ascii码相减，以A的ascii码为基准，A表示1，B表示2
            num = (int)(ch - 'A' + 1) ;
            //列号转换相当于26进制数转10进制
            num *= Math.pow(26, i);
            result += num;
        }
        return result;

    }


    public static void main(String[] args) throws Exception {

       List<String> list=new ArrayList<>();
        list.add("");
        list.add(null);
        list.add("");

        System.out.println(Myhandler.covertRowIdtoInt("AB7"));
        Test2 test2=new Test2();
        test2.processOneSheet();
        System.out.println(list+"");
        System.out.println("List的大小"+list.size());

    }


}*/
