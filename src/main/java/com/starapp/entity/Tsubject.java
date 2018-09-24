package com.starapp.entity;

import java.util.Date;

public class Tsubject {
    private Long id;

    private String ndDm;

    private String kmDm;

    private String kmmc;

    private String kmlx;

    private String kmfx;

    private String fjkmDm;

    private Integer level;

    private String createman;

    private Date createdate;

    private String updateman;

    private Date updatedate;

    private String standardSubjectsCode;

    private String standardSubjectsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNdDm() {
        return ndDm;
    }

    public void setNdDm(String ndDm) {
        this.ndDm = ndDm;
    }

    public String getKmDm() {
        return kmDm;
    }

    public void setKmDm(String kmDm) {
        this.kmDm = kmDm;
    }

    public String getKmmc() {
        return kmmc;
    }

    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    public String getKmlx() {
        return kmlx;
    }

    public void setKmlx(String kmlx) {
        this.kmlx = kmlx;
    }

    public String getKmfx() {
        return kmfx;
    }

    public void setKmfx(String kmfx) {
        this.kmfx = kmfx;
    }

    public String getFjkmDm() {
        return fjkmDm;
    }

    public void setFjkmDm(String fjkmDm) {
        this.fjkmDm = fjkmDm;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUpdateman() {
        return updateman;
    }

    public void setUpdateman(String updateman) {
        this.updateman = updateman;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
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
}