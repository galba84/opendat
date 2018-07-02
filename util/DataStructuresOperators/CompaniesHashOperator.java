package com.opendat.util.DataStructuresOperators;

import com.opendat.model.NoSql.CompanyHash;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class CompaniesHashOperator {
    List<CompanyHash> companiesHash;
    int companiesHashListsize;
    int[] companyHashArray;
    int[] companyIdArray;
    String[] companyEDRPOUArray;
    int[] sortedHashArray;

    public CompaniesHashOperator(List<CompanyHash> companyHashes) {
        this.companiesHash = companyHashes;
        companiesHashListsize = companiesHash.size();
        companyHashArray = new int[companiesHashListsize];
        companyIdArray = new int[companiesHashListsize];
        companyEDRPOUArray = new String[companiesHashListsize];
        sortedHashArray = new int[companiesHashListsize];
        listToArray();
    }

    private void listToArray() {
        int position = 0;
        for (CompanyHash c : companiesHash
                ) {
            int id = c.getId();
            int hashcode = c.getHashCode();
            String edrpou = c.getEDRPOU();
            companyIdArray[position] = id;
            companyHashArray[position] = hashcode;
            companyEDRPOUArray[position] = edrpou;
            position++;
        }
        Arrays.sort(sortedHashArray);
    }


    public boolean isCompanyInDb(int hashCode) {
        if (ArrayUtils.indexOf(sortedHashArray, hashCode) > -1)
            return true;
        else return false;
    }

    public String getEdrpouByHash(int hashCode) {
        int position = ArrayUtils.indexOf(companyHashArray, hashCode);
        return companyEDRPOUArray[position];
    }

}
