package com.starapp.common.ThreadCollections;

import com.starapp.entity.Tvoucher;
import com.starapp.service.TvocherService;
import com.starapp.service.impl.TvocherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/10/2323:52
 * @author:Allen
 * @description:
 */
@Component
@Scope("prototype")
public class HandlerCsvByRunnable  implements  Runnable{
    //i:表示数量标志，commit_No:表示提交次数,batchInsertNumber:表示提交数量
    int i=0;
    int commit_No=0;
    int batchInsertNumber=50000;

    Object obj=new Object();


    private List<Tvoucher> listBean;
    //线程中这种注入是失败的
    @Autowired
    TvocherService tvocherServiceImpl;





    List<Tvoucher> list_demo=new ArrayList<>();
    @Override
    public void run() {

        while(list_demo.size()<batchInsertNumber){
            synchronized (obj){
                for (Tvoucher tvocher:listBean) {
                    list_demo.add(tvocher);
                    tvocherServiceImpl.insertBatchTvocher(list_demo);
                        list_demo.clear();
                        i=i+1;
                        System.out.println("第"+i+"次提交,当前线程："+Thread.currentThread().getName());
                    }

                }
            }
        }




    }//表示最后剩下的数据
   /*     if(!listBean.isEmpty()){
            tvocherService.insertBatchTvocher(listBean);
            System.out.println("第"+(commit_No+1)+"次提交");
        }*/





