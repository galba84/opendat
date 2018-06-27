/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util.CompanyServiceTools.ParseXml;


import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Company.CompanyArchived;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class CompanyServiceTools {

    public Company CompanyUpdateRecords(Company newCompany, Company existCompany, int[] compareResult) {
        if (compareResult[0] == 1)
            existCompany.setNAME(newCompany.getNAME());
        if (compareResult[1] == 1)
            existCompany.setSHORT_NAME(newCompany.getSHORT_NAME());
        if (compareResult[2] == 1)
            existCompany.setEDRPOU(newCompany.getEDRPOU());
        if (compareResult[3] == 1)
            existCompany.setADDRESS(newCompany.getADDRESS());
        if (compareResult[4] == 1)
            existCompany.setBOSS(newCompany.getBOSS());
        if (compareResult[5] == 1)
            existCompany.setKVED(newCompany.getKVED());
        if (compareResult[6] == 1)
            existCompany.setSTAN(newCompany.getSTAN());
        if (compareResult[7] == 1)
            existCompany.setFOUNDERS(newCompany.getFOUNDERS());
        if (compareResult[8] == 1)
            existCompany.setDate(newCompany.getDate());
        return existCompany;
    }

    public CompanyArchived CompanyToCompanyArchived(Company company, Date deprecDate) {
        CompanyArchived archivedCompany = new CompanyArchived();
        archivedCompany.setNAME(company.getNAME());
        archivedCompany.setSHORT_NAME(company.getSHORT_NAME());
        archivedCompany.setEDRPOU(company.getEDRPOU());
        archivedCompany.setADDRESS(company.getADDRESS());
        archivedCompany.setBOSS(company.getBOSS());
        archivedCompany.setKVED(company.getKVED());
        archivedCompany.setSTAN(company.getSTAN());
        archivedCompany.setFOUNDERS(company.getFOUNDERS());
        archivedCompany.setDate(company.getDate());
        archivedCompany.setDepracatedDate(deprecDate);
        return archivedCompany;
    }


    public int[] companiesColumnsNotEqual(Company newComp, Company existComp) {
        int[] result = new int[9];
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getNAME(), newComp.getNAME()))
            result[0] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getSHORT_NAME(), newComp.getSHORT_NAME()))
            result[1] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getEDRPOU().toString(), newComp.getEDRPOU().toString()))
            result[2] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getADDRESS(), newComp.getADDRESS()))
            result[3] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getBOSS(), newComp.getBOSS()))
            result[4] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getKVED(), newComp.getKVED()))
            result[5] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getSTAN(), newComp.getSTAN()))
            result[6] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getFOUNDERS(), newComp.getFOUNDERS()))
            result[7] = 1;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getDate().toString(), newComp.getDate().toString()))
            result[8] = 1;
        return result;
    }

    public boolean ifCompaniesEssentialInfoEqualRegardlessDate(Company newComp, Company existComp) {

        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getNAME(), newComp.getNAME()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getSHORT_NAME(), newComp.getSHORT_NAME()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getEDRPOU().toString(), newComp.getEDRPOU().toString()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getADDRESS(), newComp.getADDRESS()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getBOSS(), newComp.getBOSS()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getKVED(), newComp.getKVED()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getSTAN(), newComp.getSTAN()))
            return false;
        if (!org.apache.commons.lang3.StringUtils.equals(existComp.getFOUNDERS(), newComp.getFOUNDERS()))
            return false;

        return true;
    }

    public boolean ifCompaniesDatesEqual(Date newCompDate, Date existCompDate) {

        if (!org.apache.commons.lang3.StringUtils.equals(existCompDate.toString(), newCompDate.toString()))
            return false;

        return true;
    }


    public boolean ifCompaniesIdentique(Company newComp, Company existComp) {
        if ((ifCompaniesDatesEqual(newComp.getDate(), existComp.getDate())) & (ifCompaniesEssentialInfoEqualRegardlessDate(newComp, existComp)))
            return true;
        else return false;
    }


}