package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.common.EnumCodeMsg;
import com.starapp.common.Excel2007Reader;
import com.starapp.dao.TInvoiceDataMapper;
import com.starapp.entity.TInvoiceData;
import com.starapp.service.TInvoiceDataService;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @date:2018/9/917:56
 * @author:Allen
 * @description:
 */

@Service
@Transactional
public class TInvoiceDataServiceImpl implements TInvoiceDataService {
    @Autowired
    private TInvoiceDataMapper tInvoiceDataMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int insertBatch(List<TInvoiceData> listTinvoiceData) {

        return tInvoiceDataMapper.insertBatch(listTinvoiceData);
    }


}
