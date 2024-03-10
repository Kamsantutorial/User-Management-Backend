package com.backend.internal.usermanagement.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.util.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeFormatUtil {

    public String datetimeFormatToString(Date date, String patern) {
        SimpleDateFormat sdf = new SimpleDateFormat(patern);
        return sdf.format(date);
    }

    public String localDatetimeFormatToString(LocalDate date, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date localDateToUtilDate(LocalDate date, String pattern) {
        String dateString = localDatetimeFormatToString(date, pattern);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String datetimeFormatToString(String dateString, String patern) {
        try {
            return datetimeFormatToString(new SimpleDateFormat(patern).parse(dateString), patern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date getDateFromString(String dateString, String patern) {
        try {
            return new SimpleDateFormat(patern).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LocalDate getLocalDateFromString(String dateString, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String parseTransacDateToDateString(String yearMonth, String pattern) {
        // Parse the year and month string
        LocalDate localDate = LocalDate.parse(yearMonth + "-02");

        // Format the date to the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    public Date getNextMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public Date getNextMonthDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate nextMonth = localDate.plusMonths(1);
        return Date.from(nextMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getPreviousMonthDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate nextMonth = localDate.plusMonths(-1);
        return Date.from(nextMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getNextDatePlusMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public Date getNextDateMinusMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }

    public Date getCurrentMonthDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public Date getPreviousMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public String getCronStartAt(String cronExpression) {
        if (!CronExpression.isValidExpression(cronExpression) || Objects.isNull(cronExpression))
            return cronExpression;
        String[] fields = StringUtils.tokenizeToStringArray(cronExpression, " ");
        if (fields.length != 6) {
            throw new IllegalArgumentException(String.format(
                    "Cron expression must consist of 6 fields (found %d in \"%s\")", fields.length,
                    cronExpression));
        }
        String[] months = StringUtils.tokenizeToStringArray(fields[3], "-");

        return String.format("%s/%s", DateTimeFormatUtil.datetimeFormatToString(new Date(), "yyyy/MM"), months[0]);
    }

    public String getCronEndAt(String cronExpression) {
        if (!CronExpression.isValidExpression(cronExpression) || Objects.isNull(cronExpression))
            return cronExpression;

        String[] fields = StringUtils.tokenizeToStringArray(cronExpression, " ");
        if (fields.length != 6) {
            throw new IllegalArgumentException(String.format(
                    "Cron expression must consist of 6 fields (found %d in \"%s\")", fields.length,
                    cronExpression));
        }

        String[] months = StringUtils.tokenizeToStringArray(fields[3], "-");

        return String.format("%s/%s", DateTimeFormatUtil.datetimeFormatToString(new Date(), "yyyy/MM"), months[1]);
    }

}
