package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.dao.TvoucherMapper;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tvoucher;
import com.starapp.service.TvocherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @date:2018/9/2322:02
 * @author:Allen
 * @description:
 */
@Service
@Transactional
public class TvocherServiceImpl implements TvocherService {
    @Autowired
    private TvoucherMapper tvoucherMapper;

    /**
     * @description:校验凭证表借贷总额是否平衡【调用存储过程】
     * @return
     */
    @Override
    public HashMap check_Total_loan() {

        return tvoucherMapper.check_Total_loan();
    }

    @Override
    public BackMessage insertBatchTvocher(List<Tvoucher> listBean) {
        BackMessage backMessage;
        int successFlag=tvoucherMapper.batchInsertTvoucher(listBean);
        if(successFlag>0){
            backMessage= new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"提交OK");
        }else{
            backMessage= new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"提交失败");
        }
        return backMessage;
    }

    @Override
    public BackMessage insertBatchTvocherByExcel(List<List<String>> rowList) {
        BackMessage backMessage=null;
        List<Tvoucher> listTvoucher = new ArrayList<Tvoucher>();
        int commit_No=0;
        Tvoucher tvoucher=null;
        for (int i = 0; i <rowList.size(); i++) {
            tvoucher=new Tvoucher();
            List<String> str=rowList.get(i);
            tvoucher.setNdDm(str.get(0));
            tvoucher.setKjqj(String.valueOf(str.get(1))==null?0:Integer.valueOf(str.get(1).trim()));
            tvoucher.setPzz(str.get(2));
            tvoucher.setPzh(str.get(3));
            tvoucher.setFlh(String.valueOf(str.get(4))==null?0:Integer.valueOf(str.get(4).trim()));
            try {
                tvoucher.setPzRq(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str.get(5)));
            } catch (ParseException e) {
                System.out.println("TvocherServiceImpl.insertBatchTvocherByExcel:===>日期解析失败");
                e.printStackTrace();
            }
            tvoucher.setFlzy(str.get(6));
            tvoucher.setKmDm(str.get(7));
            tvoucher.setJfje(new BigDecimal(str.get(8).toString().trim()));
            tvoucher.setDfje(new BigDecimal(str.get(9).toString().trim()));
            listTvoucher.add(tvoucher);
            if(listTvoucher.size()%10000==0){

                tvoucherMapper.batchInsertTvoucher(listTvoucher);
                System.out.println("TvocherServiceImpl.insertBatchTvocherByExcel[/第]"+i+"次提交");
            }
        }
            if(!listTvoucher.isEmpty()){
                int successFlag=tvoucherMapper.batchInsertTvoucher(listTvoucher);
                if(successFlag>0){
                    backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,"导入OK");
                }else{
                    backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"导入失败");
                }
            }


        return backMessage;
    }

    @Override
    public List<Tvoucher> getAllTvoucher() {
        return tvoucherMapper.getAllTvoucher();
    }

}



