/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import javax.persistence.Table;


@Table(name = "UserStatistic")

public class UserStatistic {

    public long count;

    public UserStatistic(long count) {
        this.count = count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return this.count;
    }
}
