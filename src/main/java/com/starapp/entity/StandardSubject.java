package com.starapp.entity;

import java.util.Date;

/**
 * 企业科目与标准科目
 */
public class StandardSubject {
    private Long id;

    private String standardDm;

    private String standardDmName;

    private String companyDm;

    private String companyDmName;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandardDm() {
        return standardDm;
    }

    public void setStandardDm(String standardDm) {
        this.standardDm = standardDm;
    }

    public String getStandardDmName() {
        return standardDmName;
    }

    public void setStandardDmName(String standardDmName) {
        this.standardDmName = standardDmName;
    }

    public String getCompanyDm() {
        return companyDm;
    }

    public void setCompanyDm(String companyDm) {
        this.companyDm = companyDm;
    }

    public String getCompanyDmName() {
        return companyDmName;
    }

    public void setCompanyDmName(String companyDmName) {
        this.companyDmName = companyDmName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public StandardSubject(Long id, String standardDm, String standardDmName, String companyDm, String companyDmName, Date createTime, Date updateTime) {
        this.id = id;
        this.standardDm = standardDm;
        this.standardDmName = standardDmName;
        this.companyDm = companyDm;
        this.companyDmName = companyDmName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public StandardSubject() {
    }
}