/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util;

import com.opendat.model.NoSql.Enumerations.Database.Company.COMPANY_COLUMNS;
import com.opendat.model.NoSql.Enumerations.ErrorCodes.EXIT_CODE;
import org.apache.commons.lang3.EnumUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanySearchRequestValidator {

    public boolean onlyContainsAllowedChar(String text) {

        Pattern p = Pattern.compile("[\\p{IsAlphabetic}\\p{IsDigit}\\p{IsPunctuation}\\p{IsWhite_Space}]");
        Matcher m = p.matcher(text);
        if (m.find())
            return true;
        else return false;
    }

    private boolean onlyContainsNumbers(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean IsEmpty(String key) {
        if (key.length() == 0) return true;
        else return false;
    }

    private boolean IsTooLong(String key, int MaxLength) {
        if (key.length() > MaxLength) return true;
        else return false;
    }

    private int check_TextKey(String key) {
        if (IsEmpty(key))
            return EXIT_CODE.STRING_IS_EMPTY.getNumVal();
        else if (IsTooLong(key, 100))
            return EXIT_CODE.STRING_IS_TOO_LONG.getNumVal();
        else if (!onlyContainsAllowedChar(key))
            return EXIT_CODE.STRING_IS_CONTAINS_NOT_ALLOWED_CHARACTERS.getNumVal();
        return 0;
    }

    private int check_EdrpouKey(String key) {
        if (IsEmpty(key))
            return EXIT_CODE.STRING_IS_EMPTY.getNumVal();
        else if (IsTooLong(key, 8))
            return EXIT_CODE.STRING_IS_TOO_LONG.getNumVal();
        else if (!onlyContainsNumbers(key))
            return EXIT_CODE.STRING_IS_CONTAINS_NOT_ALLOWED_CHARACTERS.getNumVal();
        return 0;
    }

    private boolean isColumnExist(String column) {
        if (EnumUtils.isValidEnum(COMPANY_COLUMNS.class, column))
            return true;
        else return false;
    }

    private boolean isColumnEdrpou(String column){
        if (column.equalsIgnoreCase(COMPANY_COLUMNS.EDRPOU.name()))
            return true;
        else return false;
    }

    private int checkKey(String key, String column) {

        if (isColumnEdrpou(column))
        return check_EdrpouKey(key);
        else return check_TextKey(key);

    }

    public int validateRequest(String key, String column) {
        if (!isColumnExist(column))
            return EXIT_CODE.NO_SUCH_COLUMN.getNumVal();
        if (checkKey(key, column) > 0)
            return EXIT_CODE.KEY_NOT_VALID.getNumVal();
        return 0;

    }

}






