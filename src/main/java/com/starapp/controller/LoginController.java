package com.starapp.controller;

import com.alibaba.fastjson.JSON;
import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.entity.User;
import com.starapp.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @date:2018/9/218:29
 * @author:Allen
 * @description:*/


@Controller
@RequestMapping("/starApp")
public class LoginController {
    private Logger log=Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("/login_login")
    protected String login_check(HttpServletRequest request,Model model) {
        BackMessage backMessage=null;
        String _account=request.getParameter("userName");
        String _pwd=request.getParameter("password");
        HashMap<String,String> hashMap=new HashMap<String,String>();
        hashMap.put("userAccount",_account);
        hashMap.put("userPassword",this.encryption(_pwd));
        User user= userService.login_check(hashMap);
        if(StringUtils.isEmpty(user)){
            backMessage=new BackMessage(EnumCodeMsg.Code000.getEnumName(),EnumCodeMsg.Code000.getEnumCode(),null,"账号或密码错误");
            model.addAttribute("BACK_MSG",JSON.toJSONString(backMessage.getObj()));
            return "login";
        }else{
            backMessage=new BackMessage(EnumCodeMsg.Code200.getEnumName(),EnumCodeMsg.Code200.getEnumCode(),null,user);
            log.info(JSON.toJSONString(backMessage));
            HttpSession session = request.getSession();
            session.setAttribute("user_name",user.getUserAccount());
           /* model.addAttribute("user_name",session);*/
           // return JSON.toJSONString(backMessage);
            return "dashboard";
        }
    }

    @RequestMapping("/login_register")
    public String register(HttpServletRequest request){
        String _account=request.getParameter("user");
        String _pwd=request.getParameter("pwd");
        User user=new User();
        user.setUserAccount(_account);
            user.setUserPassword(this.encryption(_pwd));
            log.info("加密失败");
        int affect=userService.regiserAccount(user);
        return "main";
    }

/**
     * @descritpion:md5加密
     * @param original pwd
     * @return
     * @throws UnsupportedEncodingException*/


    public String encryption(String original) {
        StringBuffer sb=new StringBuffer(original);
        String newStr=null;
        sb.append("allen");
        try {
            MessageDigest md5= MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder=new BASE64Encoder();
            //加密后的字符串
            try {
                newStr=base64Encoder.encode(md5.digest(sb.toString().getBytes("utf-8")));
            } catch (UnsupportedEncodingException e) {
               log.info("encryption : throw UnsupportedEncodingException");
            }
        } catch (NoSuchAlgorithmException e) {
            log.info("encryption : throw NoSuchAlgorithmException");
        }
        return  newStr;
    }


}



