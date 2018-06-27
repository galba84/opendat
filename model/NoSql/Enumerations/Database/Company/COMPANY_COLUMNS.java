/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.Enumerations.Database.Company;

public enum COMPANY_COLUMNS {

    id(1),
    NAME(2),
    SHORT_NAME(3),
    EDRPOU(4),
    ADDRESS(5),
    BOSS(6),
    KVED(7),
    STAN(8),
    FOUNDERS(9),
    insertDate(10),
    hashCode(11),
    depracatedDate(12),
    archived(13);

    private int numVal;

    COMPANY_COLUMNS(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }


    COMPANY_COLUMNS() {
    }
}
