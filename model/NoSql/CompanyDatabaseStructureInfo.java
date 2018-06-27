/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Entity
public class CompanyDatabaseStructureInfo {


    public CompanyDatabaseStructureInfo() {
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private long notEmptyNames;
    private long notEmptyShortNames;
    private long notEmptyEDRPOU;
    private long notEmptyADDRESS;
    private long notEmptyBOSS;
    private long notEmptyKVED;
    private long notEmptySTAN;
    private long notEmptyFOUNDERS;
    private long notEmptyeachFOUNDERS;

    private long names_Max_Size;
    private long shortNames_Max_Size;
    private long eDRPOU_Max_Size;
    private long aDDRESS_Max_Size;
    private long bOSS_Max_Size;
    private long kVED_Max_Size;
    private long sTAN_Max_Size;
    private long fOUNDERS_Max_Size;
    private long oneFOUNDER_Max_Size;

    private long NAMEsize;
    private long shortNAMEsize;
    private long EDRPOUsize;
    private long ADDRESSsize;
    private long BOSSsize;
    private long KVEDsize;
    private long STANsize;
    private long FOUNDERSsize;
    private long oneFOUNDERsize;

    private long notEmptydate;
    private LocalDateTime dateTime;
    private String source;
    private long longestString;


    public CompanyDatabaseStructureInfo(LocalDateTime dateTime, String source) {
        this.notEmptyNames = 0;
        this.notEmptyShortNames = 0;
        this.notEmptyEDRPOU = 0;
        this.notEmptyADDRESS = 0;
        this.notEmptyBOSS = 0;
        this.notEmptyKVED = 0;
        this.notEmptySTAN = 0;
        this.notEmptyFOUNDERS = 0;
        this.notEmptyeachFOUNDERS = 0;
        this.names_Max_Size = 0;
        this.shortNames_Max_Size = 0;
        this.eDRPOU_Max_Size = 0;
        this.aDDRESS_Max_Size = 0;
        this.bOSS_Max_Size = 0;
        this.kVED_Max_Size = 0;
        this.sTAN_Max_Size = 0;
        this.fOUNDERS_Max_Size = 0;
        this.oneFOUNDER_Max_Size = 0;
        this.NAMEsize = 0;
        this.shortNAMEsize = 0;
        this.EDRPOUsize = 0;
        this.ADDRESSsize = 0;
        this.BOSSsize = 0;
        this.KVEDsize = 0;
        this.STANsize = 0;
        this.FOUNDERSsize = 0;
        this.oneFOUNDERsize = 0;
        this.notEmptydate = 0;
        this.dateTime = dateTime;
        this.source = source;
        this.longestString = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNotEmptyNames() {
        return notEmptyNames;
    }

    public void setNotEmptyNames(long notEmptyNames) {
        this.notEmptyNames = notEmptyNames;
    }

    public long getNotEmptyShortNames() {
        return notEmptyShortNames;
    }

    public void setNotEmptyShortNames(long notEmptyShortNames) {
        this.notEmptyShortNames = notEmptyShortNames;
    }

    public long getNotEmptyEDRPOU() {
        return notEmptyEDRPOU;
    }

    public void setNotEmptyEDRPOU(long notEmptyEDRPOU) {
        this.notEmptyEDRPOU = notEmptyEDRPOU;
    }

    public long getNotEmptyADDRESS() {
        return notEmptyADDRESS;
    }

    public void setNotEmptyADDRESS(long notEmptyADDRESS) {
        this.notEmptyADDRESS = notEmptyADDRESS;
    }

    public long getNotEmptyBOSS() {
        return notEmptyBOSS;
    }

    public void setNotEmptyBOSS(long notEmptyBOSS) {
        this.notEmptyBOSS = notEmptyBOSS;
    }

    public long getNotEmptyKVED() {
        return notEmptyKVED;
    }

    public void setNotEmptyKVED(long notEmptyKVED) {
        this.notEmptyKVED = notEmptyKVED;
    }

    public long getNotEmptySTAN() {
        return notEmptySTAN;
    }

    public void setNotEmptySTAN(long notEmptySTAN) {
        this.notEmptySTAN = notEmptySTAN;
    }

    public long getNotEmptyFOUNDERS() {
        return notEmptyFOUNDERS;
    }

    public void setNotEmptyFOUNDERS(long notEmptyFOUNDERS) {
        this.notEmptyFOUNDERS = notEmptyFOUNDERS;
    }

    public long getNotEmptyeachFOUNDERS() {
        return notEmptyeachFOUNDERS;
    }

    public void setNotEmptyeachFOUNDERS(long notEmptyeachFOUNDERS) {
        this.notEmptyeachFOUNDERS = notEmptyeachFOUNDERS;
    }

    public long getNames_Max_Size() {
        return names_Max_Size;
    }

    public void setNames_Max_Size(long names_Max_Size) {
        this.names_Max_Size = names_Max_Size;
    }

    public long getShortNames_Max_Size() {
        return shortNames_Max_Size;
    }

    public void setShortNames_Max_Size(long shortNames_Max_Size) {
        this.shortNames_Max_Size = shortNames_Max_Size;
    }

    public long geteDRPOU_Max_Size() {
        return eDRPOU_Max_Size;
    }

    public void seteDRPOU_Max_Size(long eDRPOU_Max_Size) {
        this.eDRPOU_Max_Size = eDRPOU_Max_Size;
    }

    public long getaDDRESS_Max_Size() {
        return aDDRESS_Max_Size;
    }

    public void setaDDRESS_Max_Size(long aDDRESS_Max_Size) {
        this.aDDRESS_Max_Size = aDDRESS_Max_Size;
    }

    public long getbOSS_Max_Size() {
        return bOSS_Max_Size;
    }

    public void setbOSS_Max_Size(long bOSS_Max_Size) {
        this.bOSS_Max_Size = bOSS_Max_Size;
    }

    public long getkVED_Max_Size() {
        return kVED_Max_Size;
    }

    public void setkVED_Max_Size(long kVED_Max_Size) {
        this.kVED_Max_Size = kVED_Max_Size;
    }

    public long getsTAN_Max_Size() {
        return sTAN_Max_Size;
    }

    public void setsTAN_Max_Size(long sTAN_Max_Size) {
        this.sTAN_Max_Size = sTAN_Max_Size;
    }

    public long getfOUNDERS_Max_Size() {
        return fOUNDERS_Max_Size;
    }

    public void setfOUNDERS_Max_Size(long fOUNDERS_Max_Size) {
        this.fOUNDERS_Max_Size = fOUNDERS_Max_Size;
    }

    public long getOneFOUNDER_Max_Size() {
        return oneFOUNDER_Max_Size;
    }

    public void setOneFOUNDER_Max_Size(long oneFOUNDER_Max_Size) {
        this.oneFOUNDER_Max_Size = oneFOUNDER_Max_Size;
    }

    public long getNAMEsize() {
        return NAMEsize;
    }

    public void setNAMEsize(long NAMEsize) {
        this.NAMEsize = NAMEsize;
    }

    public long getShortNAMEsize() {
        return shortNAMEsize;
    }

    public void setShortNAMEsize(long shortNAMEsize) {
        this.shortNAMEsize = shortNAMEsize;
    }

    public long getEDRPOUsize() {
        return EDRPOUsize;
    }

    public void setEDRPOUsize(long EDRPOUsize) {
        this.EDRPOUsize = EDRPOUsize;
    }

    public long getADDRESSsize() {
        return ADDRESSsize;
    }

    public void setADDRESSsize(long ADDRESSsize) {
        this.ADDRESSsize = ADDRESSsize;
    }

    public long getBOSSsize() {
        return BOSSsize;
    }

    public void setBOSSsize(long BOSSsize) {
        this.BOSSsize = BOSSsize;
    }

    public long getKVEDsize() {
        return KVEDsize;
    }

    public void setKVEDsize(long KVEDsize) {
        this.KVEDsize = KVEDsize;
    }

    public long getSTANsize() {
        return STANsize;
    }

    public void setSTANsize(long STANsize) {
        this.STANsize = STANsize;
    }

    public long getFOUNDERSsize() {
        return FOUNDERSsize;
    }

    public void setFOUNDERSsize(long FOUNDERSsize) {
        this.FOUNDERSsize = FOUNDERSsize;
    }

    public long getOneFOUNDERsize() {
        return oneFOUNDERsize;
    }

    public void setOneFOUNDERsize(long oneFOUNDERsize) {
        this.oneFOUNDERsize = oneFOUNDERsize;
    }

    public long getNotEmptydate() {
        return notEmptydate;
    }

    public void setNotEmptydate(long notEmptydate) {
        this.notEmptydate = notEmptydate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getLongestString() {
        return longestString;
    }

    public void setLongestString(long longestString) {
        this.longestString = longestString;
    }
}
