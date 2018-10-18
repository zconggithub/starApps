package com.starapp.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @date:2018/9/2711:18
 * @author:Allen
 * @description:大数据量导出工具不同于JDBC测试【待测试至少20万测试】
 */
@Service
public class BigDataExportExcelUtil {

    @Resource
    private JdbcTemplate jdbcTemplate;

public void exportExcel(){




}

}

