package com.intuit.driverService.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDateUtils {

    public static Date getDateFromStringWithFormat(String date, String dateFormat) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.parse(date);
    }


}
