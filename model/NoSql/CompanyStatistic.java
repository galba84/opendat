/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CompanyStatistic")

public class CompanyStatistic implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long compCount;
    private long unicNames;
    private long unicShortNames;
    private long unicEDRPOU;
    private long unicADDRESS;
    private long unicBOSS;
    private long unicKVED;
    private long unicSTAN;
    private long unicFOUNDERS;
    private long unicdate;
    private LocalDateTime dateTime;

    public CompanyStatistic() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getCompCount() {
        return compCount;
    }

    public void setCompCount(long compCount) {
        this.compCount = compCount;
    }

    public long getUnicNames() {
        return unicNames;
    }

    public void setUnicNames(long unicNames) {
        this.unicNames = unicNames;
    }

    public long getUnicShortNames() {
        return unicShortNames;
    }

    public void setUnicShortNames(long unicShortNames) {
        this.unicShortNames = unicShortNames;
    }

    public long getUnicEDRPOU() {
        return unicEDRPOU;
    }

    public void setUnicEDRPOU(long unicEDRPOU) {
        this.unicEDRPOU = unicEDRPOU;
    }

    public long getUnicADDRESS() {
        return unicADDRESS;
    }

    public void setUnicADDRESS(long unicADDRESS) {
        this.unicADDRESS = unicADDRESS;
    }

    public long getUnicBOSS() {
        return unicBOSS;
    }

    public void setUnicBOSS(long unicBOSS) {
        this.unicBOSS = unicBOSS;
    }

    public long getUnicKVED() {
        return unicKVED;
    }

    public void setUnicKVED(long unicKVED) {
        this.unicKVED = unicKVED;
    }

    public long getUnicSTAN() {
        return unicSTAN;
    }

    public void setUnicSTAN(long unicSTAN) {
        this.unicSTAN = unicSTAN;
    }

    public long getUnicFOUNDERS() {
        return unicFOUNDERS;
    }

    public void setUnicFOUNDERS(long unicFOUNDERS) {
        this.unicFOUNDERS = unicFOUNDERS;
    }

    public long getUnicdate() {
        return unicdate;
    }

    public void setUnicdate(long unicdate) {
        this.unicdate = unicdate;
    }


    public CompanyStatistic(long count) {
        this.compCount = count;
    }

    public void setCount(long count) {
        this.compCount = count;
    }

    public long getCount() {
        return this.compCount;
    }
}
