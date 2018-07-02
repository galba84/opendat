/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.XML.CompanyDb;

import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.SqlDb.Company.Company;

import java.util.Date;

public interface CompanyXmlReader {
    void Open(String fileName, Date date);

    CompanyRecordReadResult readNextRecord();

    long countRecords();

    void CloseReader ();


}
