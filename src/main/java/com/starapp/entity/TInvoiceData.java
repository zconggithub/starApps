package com.starapp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TInvoiceData {
    private String invoiceDataid;

    private String createman;

    private String invoiceDataname;

    private String updateman;

    private Date createdate;

    private Date updatedate;

    private String billDate;

    private String transId;

    private String fapiaoType;

    private String goodsName;

    private BigDecimal netAmt;

    private BigDecimal totalAmt;

    private BigDecimal taxAmt;

    private BigDecimal taxRate;

    private String pid;

    private String glcode;

    private String instcode;

    private String taxEntityid;

    private String taxpayerCode;

    private String declarObjCode;

    public String getInvoiceDataid() {
        return invoiceDataid;
    }

    public void setInvoiceDataid(String invoiceDataid) {
        this.invoiceDataid = invoiceDataid;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public String getInvoiceDataname() {
        return invoiceDataname;
    }

    public void setInvoiceDataname(String invoiceDataname) {
        this.invoiceDataname = invoiceDataname;
    }

    public String getUpdateman() {
        return updateman;
    }

    public void setUpdateman(String updateman) {
        this.updateman = updateman;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getFapiaoType() {
        return fapiaoType;
    }

    public void setFapiaoType(String fapiaoType) {
        this.fapiaoType = fapiaoType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getInstcode() {
        return instcode;
    }

    public void setInstcode(String instcode) {
        this.instcode = instcode;
    }

    public String getTaxEntityid() {
        return taxEntityid;
    }

    public void setTaxEntityid(String taxEntityid) {
        this.taxEntityid = taxEntityid;
    }

    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode;
    }

    public String getDeclarObjCode() {
        return declarObjCode;
    }

    public void setDeclarObjCode(String declarObjCode) {
        this.declarObjCode = declarObjCode;
    }

    public TInvoiceData(){};

    public TInvoiceData(String invoiceDataid, String createman, String invoiceDataname, String updateman, Date createdate, Date updatedate, String billDate, String transId, String fapiaoType, String goodsName, BigDecimal netAmt, BigDecimal totalAmt, BigDecimal taxAmt, BigDecimal taxRate, String pid, String glcode, String instcode, String taxEntityid, String taxpayerCode, String declarObjCode) {
        this.invoiceDataid = invoiceDataid;
        this.createman = createman;
        this.invoiceDataname = invoiceDataname;
        this.updateman = updateman;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.billDate = billDate;
        this.transId = transId;
        this.fapiaoType = fapiaoType;
        this.goodsName = goodsName;
        this.netAmt = netAmt;
        this.totalAmt = totalAmt;
        this.taxAmt = taxAmt;
        this.taxRate = taxRate;
        this.pid = pid;
        this.glcode = glcode;
        this.instcode = instcode;
        this.taxEntityid = taxEntityid;
        this.taxpayerCode = taxpayerCode;
        this.declarObjCode = declarObjCode;
    }

    @Override
    public String toString() {
        return "TInvoiceData{" +
                "invoiceDataid='" + invoiceDataid + '\'' +
                ", createman='" + createman + '\'' +
                ", invoiceDataname='" + invoiceDataname + '\'' +
                ", updateman='" + updateman + '\'' +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", billDate='" + billDate + '\'' +
                ", transId='" + transId + '\'' +
                ", fapiaoType='" + fapiaoType + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", netAmt=" + netAmt +
                ", totalAmt=" + totalAmt +
                ", taxAmt=" + taxAmt +
                ", taxRate=" + taxRate +
                ", pid='" + pid + '\'' +
                ", glcode='" + glcode + '\'' +
                ", instcode='" + instcode + '\'' +
                ", taxEntityid='" + taxEntityid + '\'' +
                ", taxpayerCode='" + taxpayerCode + '\'' +
                ", declarObjCode='" + declarObjCode + '\'' +
                '}';
    }
}