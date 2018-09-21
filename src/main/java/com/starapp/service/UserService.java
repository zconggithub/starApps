package com.starapp.service;

import com.starapp.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @date:2018/9/323:36
 * @author:Allen
 * @description:
 */

public interface UserService {

    int regiserAccount(User user);


    List<User> getListUser();

    User login_check(HashMap<String,String> paramMap);

}
