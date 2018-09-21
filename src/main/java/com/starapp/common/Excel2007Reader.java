package com.starapp.common;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.stereotype.Component;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Excel2007Reader {

    private int headCount = 1;//表头数据
    private List<String> head = new ArrayList<String>();//将表头封装为list
    public List<List<String>> rowLists = new ArrayList<List<String>>();//保存Excel数据

  /*  public String excelPath;

    public Excel2007Reader(String excelPath) {
        this.excelPath = excelPath;
    }*/

    // 返回表头信息
    public List<String> getHead() {
        return head;
    }

    // 返回数据
  /*  public List<List<String>> getrowList() {
        return rowLists;
    }*/

    public List<List<String>> processSAXReadSheet(InputStream fileName) throws IOException, OpenXML4JException, SAXException {
        long star_Time=System.currentTimeMillis();
        OPCPackage pkg = OPCPackage.open(fileName);
        XSSFReader xssfReader = new XSSFReader(pkg);
        SharedStringsTable sst = xssfReader.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);

        Iterator<InputStream> sheets = xssfReader.getSheetsData();
        //循环读取sheets
        while (sheets.hasNext()) {
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
        long end_Time=System.currentTimeMillis();
        System.out.println("【Excel解析时间】"+(end_Time-star_Time)/1000);
        return  rowLists;
    }

    private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        // 利用XMLReaderFactory工厂类，创建XMLReader对象。
        // System.setProperty("org.xml.sax.driver", "org.apache.xerces.parsers.SAXParser");
        XMLReader parser = XMLReaderFactory.createXMLReader();
        // XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        ContentHandler handler = new SheetHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * SAX 解析excel
     */
    private class SheetHandler extends DefaultHandler {
        private SharedStringsTable sst;
        private String cellContent;//当前单元格的值
        private boolean isNewRow;//是否是新的一行
        private boolean isString;//是否是字符串
        private int rowIndex = 0;//行数
        private String cellType;//数据类型
        private boolean isDataType;//日期类型标志
        private List<String> rowContent = new ArrayList<String>();
        // cell位置，如A8
        private String preRef = null;//上一个单元格坐标
        private String ref = null;//单元格坐标
        private String maxRef = null;//最大列次单元格坐标
        //有效数据矩形区域,譬如：A1:Y2
        private String dimension;

        //根据dimension得出每行的数据长度
        private int longest;

        private SheetHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        /*
         * cell为空的两种情况： 1. cell中原来有数据，把数据清空后，cell为空，xml为：<c r="B2" />
         *
         * 2.cell原本就为空，xml为：不存在此节点 ， 如下不存在<c r="B1"></c>节点 <c r="A1" s="1"
         * t="s"> <v>0</v> </c> <c r="C1" s="1" t="s"> <v>2</v> </c>
         */
        public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
            if (name.equals("dimension")){
                dimension = attributes.getValue("ref");
                longest = covertRowIdtoInt(dimension.substring(dimension.indexOf(":")+1) );
            }
            if (name.equals("row")) {
                rowIndex++;
                isNewRow = true;
            }
            // c : cell，即表示开始标签元素为c
            else if (name.equals("c")) {
                if (isNewRow == true) {
                    preRef = ""+(char)('A' - 1) + (rowLists.size() + 1);
                    //preRef = attributes.getValue("r");
                } else {
                    preRef = ref;
                }
                ref = attributes.getValue("r");//获取单元格的坐标
                cellType = attributes.getValue("t");//获取数据类型
              String  cellType_Data = attributes.getValue("s");//获取样式

            /*   if("4".equals(cellType_Data)){//日期格式
                   isDataType = true;
               }*/

                if (cellType == null) {
                    isString = false;
                } else {
                    isString = true;
                }
                // 清空cellContent
                cellContent = "";
            }

        }

        public void characters(char[] ch, int start, int length) throws SAXException {

            cellContent += new String(ch, start, length);

        }

        public void endElement(String uri, String localName, String name) throws SAXException {
            if (name.equals("row")) {
                if (rowIndex == headCount) {
                    head = rowContent;
                    maxRef = ref;
                } else if (rowIndex > headCount) {
                    if (rowContent != null) {
                        // 处理空单元格
                        while ((maxRef.charAt(0) - ref.charAt(0)) > 0) {
                            rowContent.add(null);
                            ref = (char) (ref.charAt(0) + 1) + ref.substring(1, ref.length());
                        }
                        rowLists.add(rowContent);
                    }
                }
                rowContent = null;
            } else if (name.equals("c")) {
                //新的row
                if (isNewRow == true) {
                    rowContent = new ArrayList<String>();
                    isNewRow = false;
                }

                // 处理空单元格
                while ((ref.charAt(0) - preRef.charAt(0)) > 1) {
                    rowContent.add(null);
                    preRef = (char) (preRef.charAt(0) + 1) + preRef.substring(1, preRef.length());
                }
                        if("e".equals(cellType)){//e表示数据有误
                                System.out.print("数据类型有错误:"+ref+" ");
                                if("#N/A".equals(cellContent)){
                                    isString=true;
                                    cellContent="000";
                                }
                        }
                // 日期格式处理
            /*  if (isDataType) {
                    System.out.println("日期格式数据测试"+cellContent);
                    Date date = HSSFDateUtil.getJavaDate(Double.valueOf(cellContent));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                   cellContent = dateFormat.format(date);
                }*/
                //cellContent为String
                if (isString) {
                    int idx = Integer.parseInt(cellContent);
                    cellContent = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                } else {   ///cellContent为int
                    // cell为空的第一种情况
                    if (cellContent.equals("")) {
                        cellContent = null;
                    } else {

                    }
                }

                rowContent.add(cellContent);
            }

        }


        /**
         * 列号转数字   AB7-->28 第28列
         * @param rowId
         * @return
         */
        public  int covertRowIdtoInt(String rowId){
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
    }
    public static void main(String[] args) throws Exception {
    long star_Time=System.currentTimeMillis();
/*Excel2007Reader s2007=new Excel2007Reader("C:\\Users\\pw699qr\\Desktop\\bbb.xlsx");
     List<List<String>> rowList=s2007.processSAXReadSheet();*/
      //若无表头，即第一行就是数据
        String filePath="C:\\Users\\pw699qr\\Desktop\\bb.xls";
        File file=new File(filePath);
        InputStream inputStream=new FileInputStream(file);
        Excel2007Reader excel2007Reader = new Excel2007Reader();
     excel2007Reader.processSAXReadSheet(inputStream);//【记住Excel的表头处理】
        long end_Time=System.currentTimeMillis();
        System.out.println("【封装时间】"+(end_Time-star_Time)/1000);



    }
}