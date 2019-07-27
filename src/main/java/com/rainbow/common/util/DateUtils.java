package com.rainbow.common.util;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static List<String> getYearByStartAndEnd(Date startDate,Date endDate) {
        List<String> yearList = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int startYear = c.get(Calendar.YEAR);
        c.setTime(endDate);
        int endYear = c.get(Calendar.YEAR);
        while (startYear <= endYear) {
            yearList.add(String.valueOf(startYear));
            startYear++;
        }
        return yearList;
    }
}
