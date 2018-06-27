/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.SqlDb.Company;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getBOSS() {
        return BOSS;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHashCode(Integer hashCode) {
        this.hashCode = hashCode;
    }

    public Date getDepracatedDate() {
        return depracatedDate;
    }

    public void setDepracatedDate(Date depracatedDate) {
        this.depracatedDate = depracatedDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER(18)")
    private int id;
    @Column(columnDefinition = "VARCHAR(1024)")
    private String NAME;
    @Column(columnDefinition = "VARCHAR(1024)")
    private String SHORT_NAME;
    @Column(name ="EDRPOU", columnDefinition = "VARCHAR(8)")
    private String EDRPOU;
    @Column(columnDefinition = "VARCHAR(512)")
    private String ADDRESS;
    @Column(columnDefinition = "VARCHAR(128)")
    private String BOSS;
    @Column(columnDefinition = "VARCHAR(255)")
    private String KVED;
    @Column(columnDefinition = "VARCHAR(64)")
    private String STAN;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String FOUNDERS;
    @Column(name = "insertDate")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "hashCode", columnDefinition = "INTEGER(12)")
    private Integer hashCode;
    @Column(name = "depracatedDate")
    @Temporal(TemporalType.DATE)
    private Date depracatedDate;
    @Column(name = "archived")
    private boolean archived;

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }


    public Company() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return new EqualsBuilder()
                .append(id, company.id)
                .append(NAME, company.NAME)
                .append(SHORT_NAME, company.SHORT_NAME)
                .append(EDRPOU, company.EDRPOU)
                .append(ADDRESS, company.ADDRESS)
                .append(BOSS, company.BOSS)
                .append(KVED, company.KVED)
                .append(STAN, company.STAN)
                .append(FOUNDERS, company.FOUNDERS)
                .append(date, company.date)
                .isEquals();
    }

    @Override
    public int hashCode() {

        String all = new String();
        all = (SHORT_NAME + SHORT_NAME + EDRPOU + ADDRESS + BOSS + KVED + STAN + FOUNDERS);
        return new HashCodeBuilder(17, 37)
                .append(all)
                .toHashCode();
    }

    @Override
    public String toString() {

        return (SHORT_NAME + SHORT_NAME + EDRPOU + ADDRESS + BOSS + KVED + STAN + FOUNDERS);
    }

}
