package com.starapp.controller;

import com.starapp.common.BackMessage;
import com.starapp.entity.Tfile;
import com.starapp.service.TfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @date:2018/9/2118:43
 * @author:Allen
 * @description:数据元添加
 */
@Controller
@RequestMapping("/starApp")
public class TfileController {
    private Logger log=Logger.getLogger(this.getClass());

@Autowired
    private TfileService tfileService;

@RequestMapping("/addDataSource")
public String addTfile(Model model, Tfile tfile){
    log.info("[{/addDataSource}] 数据元添加：");

    BackMessage backMessage=null;
    if(tfile!=null){
        backMessage=tfileService.addTfile(tfile);
        model.addAttribute("dataSourceAddSuccessFlag",backMessage.getObj());
    }
    log.info("[{/addDataSource}] 数据元添加结束："+backMessage.getObj());
    return "/datahandler/dataSourceManage";
}

}
