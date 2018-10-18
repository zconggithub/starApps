package com.starapp.common;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

/**
 * @date:2018/10/1016:39
 * @author:Allen     Demo
 * @description:解析pdf
 */
public class ParsePDF {


    public static void main(String[] args) throws IOException {

        try {
            PDDocument pdfDocument = PDDocument.load(new File("C:\\Users\\pw699qr\\Desktop\\黄老师新课题\\需求解决方案征集_20181010001\\客户A2015VAT\\2015年1月VAT申报.pdf"));// //加载一个pdf对象
           System.out.println("此PDF页数："+pdfDocument.getNumberOfPages());
            if (!pdfDocument.isEncrypted()) {//判断pdf文件是否加密
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(pdfDocument);
                String[] lines = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
