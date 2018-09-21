package com.starapp.service;

import com.starapp.common.BackMessage;
import com.starapp.entity.TInvoiceData;

import java.util.List;

/**
 * @date:2018/9/917:55
 * @author:Allen
 * @description:
 */

public interface TInvoiceDataService {

    int insertBatch(List<TInvoiceData> listTinvoiceData);
}
