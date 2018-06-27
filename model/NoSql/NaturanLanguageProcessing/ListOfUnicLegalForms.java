/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.NaturanLanguageProcessing;

import java.util.*;

public class ListOfUnicLegalForms {

    public static boolean ASC = true;
    public static boolean DESC = false;


    TreeMap<String, Integer> uniqLegalForms = new TreeMap<String, Integer>();


    public void AddKey(String key) {
        uniqLegalForms.put(key, 1);
    }

    public boolean IfHasValue(String key) {
        if (uniqLegalForms.containsKey(key))
            return true;
        else return false;
    }

    public void incrementValueByKey(String key) {
        uniqLegalForms.put(key, uniqLegalForms.get(key) + 1);
    }

    public TreeMap<String, Integer> getUniqLegalForms() {
        return uniqLegalForms;
    }

    public int sizeOfMap() {
        return uniqLegalForms.size();
    }

    public ListOfUnicLegalForms() {
    }






}
