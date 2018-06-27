/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.SqlDb.Company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CompanyArchived")
public class CompanyArchived implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long Companyid;

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private Group group;
    @Column(columnDefinition="TEXT")
    private String NAME;
    @Column(columnDefinition="TEXT")
    private String SHORT_NAME;
    private String EDRPOU;
    @Column(name="ADDRESS",columnDefinition="LONGTEXT")
    private String ADDRESS;
    private String BOSS;
    private String KVED;
    private String STAN;

    @Column(columnDefinition="LONGTEXT")
   private String FOUNDERS;


    @Column(name = "depracatedDate")
    @Temporal(TemporalType.DATE)
    private Date depracatedDate;

    public long getCompanyid() {
        return Companyid;
    }

    public void setCompanyid(long companyid) {
        Companyid = companyid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public CompanyArchived() {
    }


    public CompanyArchived(String NAME, String SHORT_NAME, String EDRPOU, String ADDRESS, String BOSS, String KVED, String STAN ) {
        this.NAME = NAME;
        this.SHORT_NAME = SHORT_NAME;
        this.EDRPOU = EDRPOU;
        this.ADDRESS = ADDRESS;
        this.BOSS = BOSS;
        this.KVED = KVED;
        this.STAN = STAN;
        this.FOUNDERS = FOUNDERS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompanyId() {
        return Companyid;
    }

    public void setCompanyId(long Companyid) {
        this.Companyid = Companyid;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSHORT_NAME() {
        return SHORT_NAME;
    }

    public void setSHORT_NAME(String SHORT_NAME) {
        this.SHORT_NAME = SHORT_NAME;
    }

    public String getEDRPOU() {
        return EDRPOU;
    }

    public void setEDRPOU(String EDRPOU) {
        this.EDRPOU = EDRPOU;
    }

    public String getBOSS() {
        return BOSS;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public void setBOSS(String BOSS) {
        this.BOSS = BOSS;
    }

    public String getKVED() {
        return KVED;
    }

    public void setKVED(String KVED) {
        this.KVED = KVED;
    }

    public String getSTAN() {
        return STAN;
    }

    public void setSTAN(String STAN) {
        this.STAN = STAN;
    }

    public String getFOUNDERS() {
        return FOUNDERS;
    }

    public void setFOUNDERS(String FOUNDERS) {
        this.FOUNDERS = FOUNDERS;
    }


    public Date getDepracatedDate() {
        return this.depracatedDate;
    }

    public void setDepracatedDate(Date depracatedDate) { this.depracatedDate = depracatedDate; }

}
