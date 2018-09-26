package com.starapp.controller;

import com.starapp.entity.Tfile;
import com.starapp.service.TfileService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @date:2018/9/1915:10
 * @author:Allen
 * @description:
 */
@Controller
@RequestMapping("/starApp")
public class AdminController {


    @RequestMapping("/")//返回到指定登陆界面
    public String main(Model model){
        return "login";
    }


    @RequestMapping("/login")//返回到指定登陆界面
    public String lognGet(Model model){
        return "login";
    }



    /* 注册
     * @param model
	 * @return
      */
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    /**
     * 仪表板页面
     *
     * @param model
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }


    /**
     * 数据上传界面返回
     *
     * @param model
     * @return
     */
    @Autowired
    private TfileService tfileService;
    @GetMapping("/data_upload")
    public String data_upload(Model model) {
        model.addAttribute("ssss","我是中过方式");
        List<Tfile> AllDataSource=tfileService.getAllDataSource();
        model.addAttribute("AllDataSource",AllDataSource );

        return "/datahandler/dataSourceManage";
    }

    //Navigation_Process
    /**
     * 千户计划导航流程
     *
     * @param model
     * @return
     */

    @GetMapping("/Navigation_Process")
    public String Navigation_Process(Model model) {
            /*model.addAttribute("ssss","我是中过方式");
            List<Tfile> AllDataSource=tfileService.getAllDataSource();
            model.addAttribute("AllDataSource",AllDataSource );*/

        return "NavigationProcess";
    }
}
