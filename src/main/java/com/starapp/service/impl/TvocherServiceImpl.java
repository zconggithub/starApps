package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.dao.TvoucherMapper;
import com.starapp.entity.Tvoucher;
import com.starapp.service.TvocherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @date:2018/9/2322:02
 * @author:Allen
 * @description:
 */
@Service
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

}
