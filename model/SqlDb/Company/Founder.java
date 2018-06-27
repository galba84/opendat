/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.SqlDb.Company;

import javax.persistence.*;


public class Founder {

    private int id;


    private Company company;
    private String NAME;


    public Founder() {
    }



    public Founder(String NAME, Company company ) {
        this.NAME = NAME;
        this.company=company;

    }




}
