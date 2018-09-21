package com.starapp.common;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @date:2018/9/1711:18
 * @author:Allen
 * @description:解析CSV文件
 */
public class ImportCsvUtils {

    public static void main(String[] args)
    {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\pw699qr\\Desktop\\a.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[item.length-1];//这就是你要的数据了
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.print(last+""+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }


