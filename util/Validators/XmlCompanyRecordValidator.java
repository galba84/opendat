package com.opendat.util.Validators;

import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.NoSql.SettingsRecords.CompanyRecordRestrictions;
import com.opendat.model.SqlDb.Company.Company;

import java.util.List;

public class XmlCompanyRecordValidator {
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

    private CompanyRecordRestrictions restrictions;

    public XmlCompanyRecordValidator() {
    }

    public boolean isValid(CompanyRecordReadResult companyRecordReadResult, CompanyRecordRestrictions companyRecordRestrictions) {
        company = companyRecordReadResult.getCompany();
        foundersList = companyRecordReadResult.getFounders();
        restrictions = companyRecordRestrictions;
        name=company.getNAME();
        shortName=company.getSHORT_NAME();
        edrpou=company.getEDRPOU();
        address=company.getADDRESS();
        boss=company.getBOSS();
        kved=company.getKVED();
        stan=company.getSTAN();
        founders=company.getFOUNDERS();
        if (!chechMaxConstrains())
            return false;
        return true;
    }

    private boolean chechMaxConstrains() {
        if (name!=null)
        if (name.length() > restrictions.maxNameSize)
            return false;
        if (shortName!=null)
        if (shortName.length() > restrictions.maxShortNameSize)
            return false;
        if (edrpou!=null)
        if (edrpou.length() > restrictions.maxEdrpouSize)
            return false;
        if (address!=null)
        if (address.length() > restrictions.maxAddressSize)
            return false;
        if (boss!=null)
        if (boss.length() > restrictions.maxBossSize)
            return false;
        if (kved!=null)
        if (kved.length() > restrictions.maxKvedSize)
            return false;
        if (stan!=null)
        if (stan.length() > restrictions.maxStanSize)
            return false;
        if (founders!=null)
        if (founders.length() > restrictions.maxFoundersSize)
            return false;
        if (founders!=null)
        if (!foundersMaxConstrains())
            return false;
        return true;
    }

    private boolean foundersMaxConstrains() {
        for (String f:foundersList
             ) {
            if (f.length()>restrictions.maxOneFounderSize)
                return false;

        }
        return true;
    }

}
