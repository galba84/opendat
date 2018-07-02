package com.opendat.util.DataStructuresOperators;

import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.SqlDb.Company.Company;


import java.util.List;



public class CompanyDatabaseStructureInfoOperator {
    private CompanyDatabaseStructureInfo sourceDbInfo;
    private Company company;
    private List<String> foundersList;
    private String name;
    private String shortName;
    private String edrpou;
    private String address;
    private String boss;
    private String kved;
    private String stan;
    private String founders;


    public CompanyDatabaseStructureInfoOperator(CompanyDatabaseStructureInfo cDbSI) {
        sourceDbInfo = cDbSI;
    }

    public void update(CompanyRecordReadResult cRRR) {
        company = cRRR.getCompany();
        foundersList = cRRR.getFounders();
        name = company.getNAME();
        shortName = company.getSHORT_NAME();
        edrpou = company.getEDRPOU();
        address = company.getADDRESS();
        boss = company.getBOSS();
        kved = company.getKVED();
        stan = company.getSTAN();
        founders = company.getFOUNDERS();
        updateFiledsSize();
        updateCompanyNotEmptyCounter();
        updateMaxSize();
    }

    public CompanyDatabaseStructureInfo get() {
        return sourceDbInfo;
    }


    private void updateFiledsSize() {
        if (name!=null)
        sourceDbInfo.setNAMEsize(sourceDbInfo.getNAMEsize() + name.length());
        if (shortName!=null)
        sourceDbInfo.setShortNAMEsize(sourceDbInfo.getShortNAMEsize() + shortName.length());
        if (edrpou!=null)
        sourceDbInfo.setEDRPOUsize(sourceDbInfo.getEDRPOUsize() + edrpou.length());
        if (address!=null)
        sourceDbInfo.setADDRESSsize(sourceDbInfo.getADDRESSsize() + address.length());
        if (boss!=null)
        sourceDbInfo.setBOSSsize(sourceDbInfo.getBOSSsize() + boss.length());
        if (kved!=null)
        sourceDbInfo.setKVEDsize(sourceDbInfo.getKVEDsize() + kved.length());
        if (stan!=null)
        sourceDbInfo.setSTANsize(sourceDbInfo.getSTANsize() + stan.length());
        if (founders!=null)
        sourceDbInfo.setFOUNDERSsize(sourceDbInfo.getFOUNDERSsize() + founders.length());
    }

    private void updateCompanyNotEmptyCounter() {
        if ((name!=null)&&(name.length() > 0))
            incrementNotEmptyName(1);
        if ((shortName!=null)&&(shortName.length() > 0))
            incrementNonEmptyShortName(1);
        if ((edrpou!=null)&&(edrpou.length() > 0))
            incrementNotEmptyEdrpou(1);
        if ((address!=null)&&(address.length() > 0))
            incrementNotEmptyAddress(1);
        if ((boss!=null)&&(boss.length() > 0))
            incrementNotEmptyBoss(1);
        if ((kved!=null)&&(kved.length() > 0))
            incrementNotEmptyKved(1);
        if ((stan!=null)&&(stan.length() > 0))
            incrementNotEmptyStan(1);
        if ((founders!=null)&&(founders.length() > 0)){
            incrementNotEmptyFounders(1);
            incrementNotEmptyEachFounder(foundersList.size());

        }
    }

    private void updateMaxSize() {

        if ((name!=null)&&(name.length() > sourceDbInfo.getNames_Max_Size()))
            sourceDbInfo.setNames_Max_Size(name.length());

        if ((shortName!=null)&&(shortName.length() > sourceDbInfo.getShortNames_Max_Size()))
            sourceDbInfo.setShortNames_Max_Size(shortName.length());
        if ((edrpou!=null)&&(edrpou.length() > sourceDbInfo.geteDRPOU_Max_Size()))
            sourceDbInfo.seteDRPOU_Max_Size(edrpou.length());
        if ((address!=null)&&(address.length() > sourceDbInfo.getaDDRESS_Max_Size()))
            sourceDbInfo.setaDDRESS_Max_Size(address.length());
        if ((boss!=null)&&(boss.length() > sourceDbInfo.getbOSS_Max_Size()))
            sourceDbInfo.setbOSS_Max_Size(boss.length());
        if ((kved!=null)&&(kved.length() > sourceDbInfo.getkVED_Max_Size()))
            sourceDbInfo.setkVED_Max_Size(kved.length());
        if ((stan!=null)&&(stan.length() > sourceDbInfo.getsTAN_Max_Size()))
            sourceDbInfo.setsTAN_Max_Size(stan.length());
        if ((founders!=null)&&(founders.length() > sourceDbInfo.getfOUNDERS_Max_Size()))
            sourceDbInfo.setfOUNDERS_Max_Size(founders.length());

        if ((foundersList!=null)&&(foundersList.size() > 1))
            updateEachFounderMaxSize();
    }

    private void incrementNotEmptyName(int increment) {
        sourceDbInfo.setNotEmptyNames(sourceDbInfo.getNotEmptyNames() + increment);
    }

    private void incrementNonEmptyShortName(int increment) {
        sourceDbInfo.setNotEmptyShortNames(sourceDbInfo.getNotEmptyShortNames() + increment);
    }

    private void incrementNotEmptyEdrpou(int increment) {
        sourceDbInfo.setNotEmptyEDRPOU(sourceDbInfo.getNotEmptyEDRPOU() + increment);
    }

    private void incrementNotEmptyAddress(int increment) {
        sourceDbInfo.setNotEmptyADDRESS(sourceDbInfo.getNotEmptyADDRESS() + increment);
    }

    private void incrementNotEmptyBoss(int increment) {
        sourceDbInfo.setNotEmptyBOSS(sourceDbInfo.getNotEmptyBOSS() + increment);
    }

    private void incrementNotEmptyKved(int increment) {
        sourceDbInfo.setNotEmptyKVED(sourceDbInfo.getNotEmptyKVED() + increment);
    }

    private void incrementNotEmptyStan(int increment) {
        sourceDbInfo.setNotEmptySTAN(sourceDbInfo.getNotEmptySTAN() + increment);
    }

    private void incrementNotEmptyFounders(int increment) {
        sourceDbInfo.setNotEmptyFOUNDERS(sourceDbInfo.getNotEmptyFOUNDERS() + increment);
    }

    private void incrementNotEmptyEachFounder(int increment) {
        sourceDbInfo.setNotEmptyeachFOUNDERS(sourceDbInfo.getNotEmptyeachFOUNDERS() + increment);
    }

    private void updateEachFounderMaxSize() {
        for (String f : foundersList
                ) {
            if (f.length() > sourceDbInfo.getOneFOUNDER_Max_Size())
                sourceDbInfo.setOneFOUNDER_Max_Size(f.length());

        }
    }
}
