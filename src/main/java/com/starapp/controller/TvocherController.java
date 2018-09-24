package com.starapp.controller;

import com.starapp.common.BackMessage;
import com.starapp.service.TvocherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @date:2018/9/2322:05
 * @author:Allen
 * @description:凭证表
 */
@Controller
@RequestMapping("/starApp")
public class TvocherController {
    private Logger log=Logger.getLogger(this.getClass());
    @Autowired
    private TvocherService tvocherService;

    /**
     * @description:检查借贷总额是否平衡【备注：调用存储过程，无入参和反参。详情查看对应XML文件】
     * @return
     */
    @RequestMapping("/check_Total_loan")
    public String check_Total_loan(Model model){

       BackMessage backMessage=null;
       HashMap hashMap=tvocherService.check_Total_loan();
        log.info("[{/check_Total_loan}: 检查借贷总额是否平衡]"+hashMap.get("backMsg"));
        model.addAttribute("check_Total_loan",hashMap.toString());
       return  "NavigationProcess";
    }

}
