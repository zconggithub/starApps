package com.starapp.controller;

import com.starapp.common.BackMessage;
/*import com.starapp.common.BigDataParseTvoucherCsvUtil;*/
import com.starapp.common.BigDataParseTvoucherCsvUtil;
import com.starapp.common.EnumCodeMsg;
import com.starapp.entity.Tvoucher;
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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @date:2018/10/813:53
 * @author:Allen
 * @description:大数据量数据测试导入[/CSV]
 */
@Controller
@RequestMapping("/starApp")
public class BigDataImportUtilController {
    private Logger log=Logger.getLogger(this.getClass());




    @Autowired
    TvocherService tvocherService;

    @RequestMapping(value = "/importCSV", method = RequestMethod.POST)
        public String importTvoucherCSV(Model model, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException, InstantiationException, IllegalAccessException {
        BackMessage backMessage=null;
        String dataSourceType=request.getParameter("dataSource_type");

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if(file.isEmpty()||file.getSize()==0){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"文件为空");
            log.info(backMessage.toString());
            return "/datahandler/dataSourceManage";
        }
        //文件名称
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(csv)$") && !fileName.matches("^.+\\.(?i)(CSV)$")) {
            backMessage=new BackMessage(EnumCodeMsg.Code001.getEnumName(),EnumCodeMsg.Code001.getEnumCode(),null,"上传的文件格式不正确");
            log.info(backMessage.toString());
            return "/datahandler/dataSourceManage";
        }
        //文件大小
        String fileSize=String.valueOf((file.getSize()/1024/1024)) ;
        log.info("【"+fileName+"】-->" + fileSize+"M】");
        InputStream inputStream = file.getInputStream();

       BigDataParseTvoucherCsvUtil.praseCsvToListBean(inputStream,tvocherService);

   //     System.out.println("[/BigDataImportUtilController.importTvoucherCSV]行数："+listBean.size());
       // System.out.println("【/listBean:】"+listBean);
    
        //tvocherService.insertBatchTvocher(listBean);



        return "/datahandler/dataSourceManage";
    }

    /**
     * 文件分流
     * @param inputStream
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
 /*   final int MAX_BYTE = 10000000;
    //接受流的容量
    long streamTotal = 0;
    //流需要分开的数量
    int streamNum = 0;
    //文件剩下的字符数
    int leave = 0;
    //byte数组接受文件的数据
    byte[] inOutb;
    public void cutInputStream(InputStream inputStream) throws IOException, InstantiationException, IllegalAccessException {
        streamTotal=inputStream.available();
        //取得流文件需要分开的数量
        streamNum = (int)Math.floor(streamTotal/MAX_BYTE);
        //分开文件之后,剩余的数量
        leave = (int)streamTotal % MAX_BYTE;
        System.out.println("接受流的容量:["+streamTotal+"]"+"取得流文件需要分开的数量: ["+streamNum+"]"+"分开文件之后,剩余的数量" +
                "["+leave+"]");
        //文件的容量大于60Mb时进入循环
        if (streamNum > 0) {
            for(int i = 0; i < streamNum; ++i){
                inOutb = new byte[MAX_BYTE];
                InputStream sbs = new ByteArrayInputStream(inOutb);
                List<Tvoucher> listBean= BigDataParseCsv.praseCsvToListBean(sbs,Tvoucher.class);
                tvocherService.insertBatchTvocher(listBean);
//读入流,保存在byte数组
                inputStream.read(inOutb, 0, MAX_BYTE);
            }
        }



        }
*/
    }

