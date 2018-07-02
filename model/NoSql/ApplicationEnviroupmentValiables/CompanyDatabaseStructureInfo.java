/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.ApplicationEnviroupmentValiables;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CompanyDatabaseStructureInfo {


    public CompanyDatabaseStructureInfo() {
    }

    public long notEmptyNames;
    public long notEmptyShortNames;
    public long notEmptyEDRPOU;
    public long notEmptyADDRESS;
    public long notEmptyBOSS;
    public long notEmptyKVED;
    public long notEmptySTAN;
    public long notEmptyFOUNDERS;
    public long notEmptyeachFOUNDERS;
    public long notEmptydate;
    public long notEmptyDepracatedDate;

    public long names_Max_Size;
    public long shortNames_Max_Size;
    public long eDRPOU_Max_Size;
    public long aDDRESS_Max_Size;
    public long bOSS_Max_Size;
    public long kVED_Max_Size;
    public long sTAN_Max_Size;
    public long fOUNDERS_Max_Size;
    public long oneFOUNDER_Max_Size;

    public long NAMEsize;
    public long shortNAMEsize;
    public long EDRPOUsize;
    public long ADDRESSsize;
    public long BOSSsize;
    public long KVEDsize;
    public long STANsize;
    public long FOUNDERSsize;
    public long oneFOUNDERsize;

    String listOfSourceFileDates;
    Date current_database_date;
    public String source;
    public long longestString;


    public CompanyDatabaseStructureInfo(Date date, String source) {

        notEmptyNames = 0;
        notEmptyShortNames = 0;
        notEmptyEDRPOU = 0;
        notEmptyADDRESS = 0;
        notEmptyBOSS = 0;
        notEmptyKVED = 0;
        notEmptySTAN = 0;
        notEmptyFOUNDERS = 0;
        notEmptyeachFOUNDERS = 0;
        notEmptydate = 0;
        notEmptyDepracatedDate = 0;

        names_Max_Size = 0;
        shortNames_Max_Size = 0;
        eDRPOU_Max_Size = 0;
        aDDRESS_Max_Size = 0;
        bOSS_Max_Size = 0;
        kVED_Max_Size = 0;
        sTAN_Max_Size = 0;
        fOUNDERS_Max_Size = 0;
        oneFOUNDER_Max_Size = 0;

        NAMEsize = 0;
        shortNAMEsize = 0;
        EDRPOUsize = 0;
        ADDRESSsize = 0;
        BOSSsize = 0;
        KVEDsize = 0;
        STANsize = 0;
        FOUNDERSsize = 0;
        oneFOUNDERsize = 0;

        listOfSourceFileDates = null;
        current_database_date = date;
        source = source;
        longestString = 0;
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

    public long getNotEmptyDepracatedDate() {
        return notEmptyDepracatedDate;
    }

    public void setNotEmptyDepracatedDate(long notEmptyDepracatedDate) {
        this.notEmptyDepracatedDate = notEmptyDepracatedDate;
    }


    public Date getCurrent_database_date() {
        return current_database_date;
    }

    public void setCurrent_database_date(Date current_database_date) {
        this.current_database_date = current_database_date;
    }

    public String getListOfSourceFileDates() {
        return listOfSourceFileDates;
    }

    public void setListOfSourceFileDates(String listOfSourceFileDates) {
        this.listOfSourceFileDates = listOfSourceFileDates;
    }


}
