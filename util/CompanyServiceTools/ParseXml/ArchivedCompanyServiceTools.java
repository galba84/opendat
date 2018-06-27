/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util.CompanyServiceTools.ParseXml;


import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Company.CompanyArchived;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component

public class ArchivedCompanyServiceTools {

    public CompanyArchived ArchivedCompanyUpdateRecords(CompanyArchived newArchivedCompany, CompanyArchived existArchivedCompany, int[] compareResult) {
        if (compareResult[0] == 1)
            existArchivedCompany.setNAME(newArchivedCompany.getNAME());
        if (compareResult[1] == 1)
            existArchivedCompany.setSHORT_NAME(newArchivedCompany.getSHORT_NAME());
        if (compareResult[2] == 1)
            existArchivedCompany.setEDRPOU(newArchivedCompany.getEDRPOU());
        if (compareResult[3] == 1)
            existArchivedCompany.setADDRESS(newArchivedCompany.getADDRESS());
        if (compareResult[4] == 1)
            existArchivedCompany.setBOSS(newArchivedCompany.getBOSS());
        if (compareResult[5] == 1)
            existArchivedCompany.setKVED(newArchivedCompany.getKVED());
        if (compareResult[6] == 1)
            existArchivedCompany.setSTAN(newArchivedCompany.getSTAN());
        if (compareResult[7] == 1)
            existArchivedCompany.setFOUNDERS(newArchivedCompany.getFOUNDERS());
        if (compareResult[8] == 1)
            existArchivedCompany.setDate(newArchivedCompany.getDate());
        if (compareResult[9] == 1)
            existArchivedCompany.setDepracatedDate(newArchivedCompany.getDepracatedDate());
        return existArchivedCompany;
    }


    public Company CompanyArch2Company(CompanyArchived ca) {
        Company company = new Company();
        company.setNAME(ca.getNAME());
        company.setSHORT_NAME(ca.getSHORT_NAME());
        company.setEDRPOU(ca.getEDRPOU());
        company.setADDRESS(ca.getADDRESS());
        company.setBOSS(ca.getBOSS());
        company.setKVED(ca.getKVED());
        company.setSTAN(ca.getSTAN());
        company.setFOUNDERS(ca.getFOUNDERS());
        company.setDate(ca.getDate());
        return company;
    }

    public int[] CompanyArchivedColumnsNotEqual(CompanyArchived newComp, CompanyArchived existComp) {
        int[] result = new int[10];
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
        if (!existComp.getDepracatedDate().equals(newComp.getDepracatedDate()))
            result[9] = 1;
        return result;
    }

    public boolean ifCompaniesArchivedEssentialInfoEqualRegardlessDate(CompanyArchived newComp, CompanyArchived existComp) {

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

    public boolean ifArchivedCompaniesDatesEqual(Date newCompDate, Date existCompDate) {

        if (!org.apache.commons.lang3.StringUtils.equals(existCompDate.toString(), newCompDate.toString()))
            return false;

        return true;
    }


    public boolean ArchiveListContainIdentiqueRecord(CompanyArchived examinedCandidCompany, List<CompanyArchived> requestedForComparationCompanyList) {

        int countOfIdentiqueRecords = 0;
        for (CompanyArchived ca : requestedForComparationCompanyList
                ) {
            if (ifArchivedCompaniesIdentique(ca, examinedCandidCompany))
                countOfIdentiqueRecords++;
        }

        if (countOfIdentiqueRecords > 0) return true;
        else return false;
    }

    public List<CompanyArchived> SelectIdentiqueRecordRegardlessDateList(List<CompanyArchived> requestedForComparationCompanyList, CompanyArchived examinedCandidCompany) {
        List<CompanyArchived> identiqueRecordRegardlessDateList = new LinkedList<CompanyArchived>();
        for (CompanyArchived ca : requestedForComparationCompanyList
                ) {
            if (ifCompaniesArchivedEssentialInfoEqualRegardlessDate(ca, examinedCandidCompany))
                identiqueRecordRegardlessDateList.add(ca);
        }
        return identiqueRecordRegardlessDateList;
    }

    public boolean ArchiveListContainIdentiqueRecordRegardlessDate(CompanyArchived examinedCandidCompany, List<CompanyArchived> requestedForComparationCompanyList) {

        int countOfIdentiqueRecords = 0;
        for (CompanyArchived ca : requestedForComparationCompanyList
                ) {
            if (ifCompaniesArchivedEssentialInfoEqualRegardlessDate(ca, examinedCandidCompany))
                countOfIdentiqueRecords++;
        }

        if (countOfIdentiqueRecords > 0) return true;
        else return false;
    }


    public boolean ifArchivedCompaniesIdentique(CompanyArchived newComp, CompanyArchived existComp) {
        if ((ifArchivedCompaniesDatesEqual(newComp.getDate(), existComp.getDate())) & (ifArchivedCompaniesDatesEqual(newComp.getDepracatedDate(), existComp.getDepracatedDate())) & (ifCompaniesArchivedEssentialInfoEqualRegardlessDate(newComp, existComp)))
            return true;
        else return false;
    }


}