/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.NaturanLanguageProcessing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;


public class Word {

    String name;
    Integer value;


    public Word() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
