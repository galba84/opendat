/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.Enumerations.ErrorCodes;

public enum EXIT_CODE {
    SUCESS(0),
    STRING_IS_EMPTY(1), STRING_IS_TOO_LONG(2), STRING_IS_CONTAINS_NOT_ALLOWED_CHARACTERS(3),
    NO_SUCH_COLUMN(11), KEY_NOT_VALID(12);

    private int numVal;

    EXIT_CODE(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static String getNameByCode(int code) {
        for (EXIT_CODE e : EXIT_CODE.values()) {
            if (code == e.getNumVal()) return e.name();
        }
        return null;
    }
}