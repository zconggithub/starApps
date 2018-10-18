package com.starapp.controller;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.service.TvocherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @date:2018/10/813:53
 * @author:Allen
 * @description:大数据量数据测试导入[/CSV]
 */
@Controller
public class BigDataImportUtilController {
    private Logger log=Logger.getLogger(this.getClass());

    @Autowired
    TvocherService tvocherService;

    @RequestMapping(value = "/import/csv", method = RequestMethod.POST)
    public String importTvoucherCSV(Model model, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        BackMessage backMessage=null;
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if(file.isEmpty()||file.getSize()==0){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"文件为空");
            return "/datahandler/dataSourceManage";
        }
        String fileName = file.getOriginalFilename();//文件名称
        if (!fileName.matches("^.+\\.(?i)(csv)$") && !fileName.matches("^.+\\.(?i)(CSV)$")) {
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"上传的文件格式不正确");
            return "/datahandler/dataSourceManage";
        }
        String file_size=String.valueOf((file.getSize()/1024/1024)) ;//文件大小
        log.info("【"+fileName+"】-->" + file_size+"M】");
        InputStream inputStream = file.getInputStream();
        return "";
    }

}
