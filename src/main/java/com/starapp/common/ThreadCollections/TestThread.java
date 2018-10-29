package com.starapp.common.ThreadCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/9/1823:57
 * @author:Allen
 * @description:
 */
public class TestThread {

    public static void main(String[] args) {
        List list=new ArrayList();
                for(int i=0;i<=2;i++){
                    list.add(i);
                }
                TestRunable threadDemo=new TestRunable(list);
                new Thread(threadDemo,"A").start();
                new Thread(threadDemo,"B").start();

    }
}




