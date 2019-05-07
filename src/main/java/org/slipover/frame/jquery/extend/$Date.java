package org.slipover.frame.jquery.extend;

import org.slipover.frame.share.exception.ServerException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 扩展 java.util.Date
 */
public class $Date implements Serializable {

    private final Calendar calendar = Calendar.getInstance();

    public $Date() {

    }

    public $Date(Date date) {
        calendar.setTime(date);
    }

    public $Date(long millis) {
        calendar.setTimeInMillis(millis);
    }

    public $Date(int year, int month, int date) {
        calendar.set(year, month - 1, date);
    }

    public $Date(int year, int month, int date, int hourOfDay, int minute) {
        calendar.set(year, month - 1, date, hourOfDay, minute);
    }

    public $Date(int year, int month, int date, int hourOfDay, int minute, int second) {
        calendar.set(year, month - 1, date, hourOfDay, minute, second);
    }

    public $Date(String stringDate, String pattern) {
        try {
            calendar.setTime(new SimpleDateFormat(pattern).parse(stringDate));
        } catch (ParseException e) {
            throw new ServerException(e);
        }
    }

    /************************************* 分割线 ************************************/

    public Date getDate() {
        return calendar.getTime();
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getDayOfYear() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public int getDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfWeekInMonth() {
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    public int getAmPm() {
        return calendar.get(Calendar.AM_PM);
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR);
    }

    public int getHourOrDay() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public int getMillisecond() {
        return calendar.get(Calendar.MILLISECOND);
    }

    public long getMillis() {
        return calendar.getTimeInMillis();
    }

    public $Date setYear(int time) {
        calendar.set(Calendar.YEAR, time);
        return this;
    }

    public $Date setMonth(int time) {
        calendar.set(Calendar.MONTH, time - 1);
        return this;
    }

    public $Date setDay(int time) {
        calendar.set(Calendar.DAY_OF_MONTH, time);
        return this;
    }

    public $Date setHour(int time) {
        calendar.set(Calendar.HOUR_OF_DAY, time);
        return this;
    }

    public $Date setMinute(int time) {
        calendar.set(Calendar.MINUTE, time);
        return this;
    }

    public $Date setSecond(int time) {
        calendar.set(Calendar.SECOND, time);
        return this;
    }

    public $Date setMillisecond(int time) {
        calendar.set(Calendar.MILLISECOND, time);
        return this;
    }

    public $Date setDate(int year, int month, int day) {
        return setYear(year).setMonth(month).setDay(day);
    }

    public $Date setTime(int hour, int minute, int second) {
        return setHour(hour).setMinute(minute).setSecond(second);
    }

    public $Date addYear(int time) {
        calendar.add(Calendar.YEAR, time);
        return this;
    }

    public $Date addMonth(int time) {
        calendar.add(Calendar.MONTH, time);
        return this;
    }

    public $Date addDay(int time) {
        calendar.add(Calendar.DAY_OF_MONTH, time);
        return this;
    }

    public $Date addHour(int time) {
        calendar.add(Calendar.HOUR_OF_DAY, time);
        return this;
    }

    public $Date addMinute(int time) {
        calendar.add(Calendar.MINUTE, time);
        return this;
    }

    public $Date addSecond(int time) {
        calendar.add(Calendar.SECOND, time);
        return this;
    }

    public $Date addMillisecond(int time) {
        calendar.add(Calendar.MILLISECOND, time);
        return this;
    }

    public String format(String pattern) {
        return new SimpleDateFormat(pattern).format(getDate());
    }

    public boolean before(Date date) {
        return calendar.getTime().compareTo(date) < 0;
    }

    public boolean after(Date date) {
        return calendar.getTime().compareTo(date) >= 0;
    }
}
