/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.NaturanLanguageProcessing;

import java.util.HashMap;

public class Vocabulary {

    private String name;
    private int id;
    private HashMap<String,Integer> vocabulary;

    public void AddKey(String key) {
        vocabulary.put(key, 1);
    }

    public boolean IfHasValue(String key) {
        if (vocabulary.containsKey(key))
            return true;
        else return false;
    }

    public void incrementValueByKey(String key) {
        vocabulary.put(key, vocabulary.get(key) + 1);
    }

    public HashMap<String, Integer> getVocabulary() {
        return vocabulary;
    }

    public int sizeOfVocabulary() {
        return vocabulary.size();
    }
}
