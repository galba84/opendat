/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import javax.persistence.Column;

public class CompanyHash {

    public CompanyHash() {
    }

    @Column(name = "id")
    public int id;
    @Column(name = "hashCode")
    public int hashCode;

    @Column(name = "EDRPOU")
    public String EDRPOU;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public String getEDRPOU() {
        return EDRPOU;
    }

    public void setEDRPOU(String EDRPOU) {
        this.EDRPOU = EDRPOU;
    }
}
