package com.starapp.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 凭证表
 */
public class Tvoucher {
    private Long id;

    private String ndDm;

    private Integer kjqj;

    private String pzz;

    private String pzh;

    private Integer flh;

    private Date pzRq;

    private String flzy;

    private String kmDm;

    private BigDecimal jfje;

    private BigDecimal dfje;

    private String wbbz;

    private BigDecimal wbjfje;

    private BigDecimal wbdfje;

    private String shr;

    private String zdr;

    private String jzr;

    private String cn;

    private Integer fjs;

    private String createman;

    private Date createdate;

    private String updateman;

    private Date updatedate;

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

    public Integer getKjqj() {
        return kjqj;
    }

    public void setKjqj(Integer kjqj) {
        this.kjqj = kjqj;
    }

    public String getPzz() {
        return pzz;
    }

    public void setPzz(String pzz) {
        this.pzz = pzz;
    }

    public String getPzh() {
        return pzh;
    }

    public void setPzh(String pzh) {
        this.pzh = pzh;
    }

    public Integer getFlh() {
        return flh;
    }

    public void setFlh(Integer flh) {
        this.flh = flh;
    }

    public Date getPzRq() {
        return pzRq;
    }

    public void setPzRq(Date pzRq) {
        this.pzRq = pzRq;
    }

    public String getFlzy() {
        return flzy;
    }

    public void setFlzy(String flzy) {
        this.flzy = flzy;
    }

    public String getKmDm() {
        return kmDm;
    }

    public void setKmDm(String kmDm) {
        this.kmDm = kmDm;
    }

    public BigDecimal getJfje() {
        return jfje;
    }

    public void setJfje(BigDecimal jfje) {
        this.jfje = jfje;
    }

    public BigDecimal getDfje() {
        return dfje;
    }

    public void setDfje(BigDecimal dfje) {
        this.dfje = dfje;
    }

    public String getWbbz() {
        return wbbz;
    }

    public void setWbbz(String wbbz) {
        this.wbbz = wbbz;
    }

    public BigDecimal getWbjfje() {
        return wbjfje;
    }

    public void setWbjfje(BigDecimal wbjfje) {
        this.wbjfje = wbjfje;
    }

    public BigDecimal getWbdfje() {
        return wbdfje;
    }

    public void setWbdfje(BigDecimal wbdfje) {
        this.wbdfje = wbdfje;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getZdr() {
        return zdr;
    }

    public void setZdr(String zdr) {
        this.zdr = zdr;
    }

    public String getJzr() {
        return jzr;
    }

    public void setJzr(String jzr) {
        this.jzr = jzr;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public Integer getFjs() {
        return fjs;
    }

    public void setFjs(Integer fjs) {
        this.fjs = fjs;
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

    public Tvoucher() {
    }

    public Tvoucher(Long id, String ndDm, Integer kjqj, String pzz, String pzh, Integer flh, Date pzRq, String flzy, String kmDm, BigDecimal jfje, BigDecimal dfje, String wbbz, BigDecimal wbjfje, BigDecimal wbdfje, String shr, String zdr, String jzr, String cn, Integer fjs, String createman, Date createdate, String updateman, Date updatedate) {
        this.id = id;
        this.ndDm = ndDm;
        this.kjqj = kjqj;
        this.pzz = pzz;
        this.pzh = pzh;
        this.flh = flh;
        this.pzRq = pzRq;
        this.flzy = flzy;
        this.kmDm = kmDm;
        this.jfje = jfje;
        this.dfje = dfje;
        this.wbbz = wbbz;
        this.wbjfje = wbjfje;
        this.wbdfje = wbdfje;
        this.shr = shr;
        this.zdr = zdr;
        this.jzr = jzr;
        this.cn = cn;
        this.fjs = fjs;
        this.createman = createman;
        this.createdate = createdate;
        this.updateman = updateman;
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "Tvoucher{" +
                "id=" + id +
                ", ndDm='" + ndDm + '\'' +
                ", kjqj=" + kjqj +
                ", pzz='" + pzz + '\'' +
                ", pzh='" + pzh + '\'' +
                ", flh=" + flh +
                ", pzRq=" + pzRq +
                ", flzy='" + flzy + '\'' +
                ", kmDm='" + kmDm + '\'' +
                ", jfje=" + jfje +
                ", dfje=" + dfje +
                ", wbbz='" + wbbz + '\'' +
                ", wbjfje=" + wbjfje +
                ", wbdfje=" + wbdfje +
                ", shr='" + shr + '\'' +
                ", zdr='" + zdr + '\'' +
                ", jzr='" + jzr + '\'' +
                ", cn='" + cn + '\'' +
                ", fjs=" + fjs +
                ", createman='" + createman + '\'' +
                ", createdate=" + createdate +
                ", updateman='" + updateman + '\'' +
                ", updatedate=" + updatedate +
                '}';
    }
}