package com.starapp.dao;

import com.starapp.entity.TInvoiceData;

import java.util.List;

public interface TInvoiceDataMapper {
    int deleteByPrimaryKey(String invoiceDataid);

    int insert(TInvoiceData record);

    int insertSelective(TInvoiceData record);

    TInvoiceData selectByPrimaryKey(String invoiceDataid);

    int updateByPrimaryKeySelective(TInvoiceData record);

    int updateByPrimaryKey(TInvoiceData record);
    int insertBatch(List<TInvoiceData> listTinvoiceData);
}