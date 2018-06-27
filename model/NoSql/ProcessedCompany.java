/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "ProcessedCompany")
public class ProcessedCompany implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER(18)")
    private int id;
    @Column(columnDefinition = "VARCHAR(1024)")
    private String NAME;
    @Column(columnDefinition = "VARCHAR(1024)")
    private String LEGAL_FORM;
    @Column(columnDefinition = "VARCHAR(128)")
    private String SHORT_NAME;
    @Column(columnDefinition = "VARCHAR(64)")
    private String SHORT_LEGAL_FORM;
    @Column(name = "EDRPOU", columnDefinition = "INTEGER(8)")
    private Integer EDRPOU;
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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "hashCode", columnDefinition = "INTEGER(12)")
    private Integer hashCode;

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }


    public ProcessedCompany() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProcessedCompany processedCompany = (ProcessedCompany) o;

        return new EqualsBuilder()
                .append(id, processedCompany.id)
                .append(NAME, processedCompany.NAME)
                .append(SHORT_NAME, processedCompany.SHORT_NAME)
                .append(EDRPOU, processedCompany.EDRPOU)
                .append(ADDRESS, processedCompany.ADDRESS)
                .append(BOSS, processedCompany.BOSS)
                .append(KVED, processedCompany.KVED)
                .append(STAN, processedCompany.STAN)
                .append(FOUNDERS, processedCompany.FOUNDERS)
                .append(date, processedCompany.date)
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

