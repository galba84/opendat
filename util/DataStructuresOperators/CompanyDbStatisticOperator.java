package com.opendat.util.DataStructuresOperators;

import com.opendat.dao.SQL.CompanyDAO;
import com.opendat.model.NoSql.CompanyStatistic;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


public class CompanyDbStatisticOperator {

    @Autowired
    public CompanyDAO companyDAO;

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

    public CompanyStatistic getStatistic (){
        LocalDateTime now1 = LocalDateTime.now();
        CompanyStatistic cs = new CompanyStatistic(companyDAO.count());
        cs.setUnicNames(companyDAO.notEmptyColumn("NAME"));
        cs.setUnicShortNames(companyDAO.notEmptyColumn("SHORT_NAME"));
        cs.setUnicEDRPOU(companyDAO.notEmptyColumn("EDRPOU"));
        cs.setUnicADDRESS(companyDAO.notEmptyColumn("ADDRESS"));
        cs.setUnicBOSS(companyDAO.notEmptyColumn("BOSS"));
        cs.setUnicKVED(companyDAO.notEmptyColumn("KVED"));
        cs.setUnicSTAN(companyDAO.notEmptyColumn("STAN"));
        cs.setUnicFOUNDERS(companyDAO.notEmptyColumn("FOUNDERS"));
        cs.setUnicNames(companyDAO.notEmptyColumn("date"));
        cs.setDateTime(now1);
        return cs;
    }

}
