package com.starapp.entity;
import java.util.Date;
/**
 * @date:2018/9/1612:26
 * @author:Allen
 * @description:标准科目和财务报表
 */
public class StandardSubjectFinance {
    private Long id;

    private String standardSubjectsCode;

    private String standardSubjectsName;

    private String financeNo;

    private String financeType;

    private String financeNoName;

    private Date createdate;

    private Date updatedate;

    private String financeTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandardSubjectsCode() {
        return standardSubjectsCode;
    }

    public void setStandardSubjectsCode(String standardSubjectsCode) {
        this.standardSubjectsCode = standardSubjectsCode;
    }

    public String getStandardSubjectsName() {
        return standardSubjectsName;
    }

    public void setStandardSubjectsName(String standardSubjectsName) {
        this.standardSubjectsName = standardSubjectsName;
    }

    public String getFinanceNo() {
        return financeNo;
    }

    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public String getFinanceNoName() {
        return financeNoName;
    }

    public void setFinanceNoName(String financeNoName) {
        this.financeNoName = financeNoName;
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

    public String getFinanceTypeName() {
        return financeTypeName;
    }

    public void setFinanceTypeName(String financeTypeName) {
        this.financeTypeName = financeTypeName;
    }
}
