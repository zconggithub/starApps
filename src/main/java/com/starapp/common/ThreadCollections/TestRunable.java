package com.starapp.common.ThreadCollections;

import java.util.List;

/**
 * @date:2018/9/1910:16
 * @author:Allen
 * @description:
 */
public class TestRunable implements  Runnable{


    private List<?> tick;
    Object obj=new Object();
    int i=0;
    public TestRunable(List tick) {
        this.tick = tick;
    }

    @Override
    public void run(){


        while (i<tick.size()) {
            synchronized (obj) {
              i++;
                 System.out.println(Thread.currentThread().getName() + "...code..." + i+"[size ]"+tick.size());
            }
        }
    }
}
