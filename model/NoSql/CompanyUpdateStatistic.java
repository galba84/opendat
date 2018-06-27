/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CompanyUpdateStatistic")

public class CompanyUpdateStatistic implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long added;

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getArchived() {
        return archived;
    }

    public void setArchived(long archived) {
        this.archived = archived;
    }

    public long updated;
    public long archived;


    public CompanyUpdateStatistic() {
        setAdded(0);
        setArchived(0);
        setUpdated(0);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
