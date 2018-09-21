package com.starapp.common;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
/**
 * @date:2018/9/216:25
 * @author:Allen
 * @description:CommandLineRunner接口的运行顺序是依据@Order注解的value由小到大执行，即value值越小优先级越高。
 */
@Component
@Order(value=1)
public class MyRunnurBeforeStart implements CommandLineRunner {
    private Logger log=Logger.getLogger(this.getClass());
    @Override
    public void run(String... args) throws Exception {
        System.out.println("《《《《《《This is MyStartupRunnerTest Order=1. Only testing CommandLineRunner...》》》");
        log.info("{[Allen]} 项目启动之前运行测试");
    }
}

