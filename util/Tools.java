/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util;


import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component

public class Tools {


    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;





    public Date parseDatefromString(String dateInString) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            date = formatter.parse(dateInString);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;
    }


    public boolean ValidateFileNotEmpty(String filename) {
        File file = new File(filename);
        if (file.length() == 0)
            return false;
        else
            return true;
    }

    public long fileSize(String filename) {
        File file = new File(filename);
        return file.length();
    }

    public Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    public long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);
        long seconds = duration.getSeconds();
        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);
        return new long[]{hours, minutes, secs};
    }



    public String getSha256hex(String originalString) {
        return DigestUtils.md5Digest(originalString.getBytes(StandardCharsets.UTF_8)).toString();
    }

    public   HashMap<Integer, String>  stringToHash(List<String> stringList) {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        for (String s : stringList
                ) {

            int hcode = new HashCodeBuilder(17, 37)
                    .append(s)
                    .toHashCode();
            hashMap.put(hcode, s);
        }
        return hashMap;
    }



}