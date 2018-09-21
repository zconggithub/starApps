package com.starapp.service.impl;

import com.starapp.dao.UserMapper;
import com.starapp.entity.User;
import com.starapp.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * @date:2018/9/323:40
 * @author:Allen
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger log=Logger.getLogger(this.getClass());
        @Autowired
        private UserMapper userMapper;
    @Override
    public int regiserAccount(User user) {

        return userMapper.insert(user);
    }



    @Override
    public List<User> getListUser() {
        return null;
    }

    @Override
    public User login_check(HashMap<String,String> paramMap){
      return  userMapper.login_check(paramMap);

    }
}
