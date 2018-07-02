/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.SqlDb.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "LogEvent")
public class LogEvent implements Serializable {
    public LogEvent() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Id
    @Column(name = "idL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //    @JoinColumn(name = "id")

    @Column(name = "EVENT",columnDefinition = "VARCHAR(512)")
    private String EVENT;
    @Column(name = "dateTime")

    private LocalDateTime dateTime;
    @Column(name = "ip")

    private String ip;





    public LogEvent(String EVENT, LocalDateTime dateTime, String ip) {
        this.EVENT = EVENT;
        this.ip = ip;
        this.dateTime = dateTime;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    //

}
