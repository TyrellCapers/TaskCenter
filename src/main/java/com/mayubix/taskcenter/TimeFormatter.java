package com.mayubix.taskcenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

    public static final Long NUM_OF_MS_IN_DAY    = 86400000L;
    public static final Long NUM_OF_MS_IN_HOUR   = 3600000L;
    public static final Long NUM_OF_MS_IN_MINUTE = 60000L;
    public static final Long NUM_OF_MS_IN_SECOND = 1000L;

    public static String dateFormat(Long time){
        if(time != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.format(new Date(time));
        }
        else{
            return null;
        }
    }

    public static String timerFormat(Long time){
        if(time != null) {

            Long daysValue = time/NUM_OF_MS_IN_DAY;
            time = time%NUM_OF_MS_IN_DAY;

            Long hoursValue = time/NUM_OF_MS_IN_HOUR;
            time = time%NUM_OF_MS_IN_HOUR;

            Long minutesValue = time/NUM_OF_MS_IN_MINUTE;
            time = time%NUM_OF_MS_IN_MINUTE;

            Long secondsValue = time/ NUM_OF_MS_IN_SECOND;

            return String.format("%05d:%02d:%02d:%02d", daysValue, hoursValue, minutesValue, secondsValue);

        }
        else{
            return null;
        }
    }
}
